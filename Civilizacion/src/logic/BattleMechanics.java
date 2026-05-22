package logic;

import java.util.ArrayList;

import game.Cannon;
import game.Crossbow;
import game.MilitaryUnit;
import game.Spearman;
import game.Swordsman;
import game.Variables;

public class BattleMechanics extends Battle {
	public BattleMechanics(ArrayList<MilitaryUnit> civilizationArmy, ArrayList<MilitaryUnit> enemyArmy) {
		super(civilizationArmy, enemyArmy);
	}
	
	public void generarEnemigo(int numBatalla) {
		// multiplicador de dificultad
		int multiplicadorPorcentaje = 100 +(numBatalla * ENEMY_FLEET_INCREASE);
		
		int hierroDisponible = IRON_BASE_ENEMY_ARMY * multiplicadorPorcentaje / 100;
		int maderaDisponible = WOOD_BASE_ENEMY_ARMY * multiplicadorPorcentaje / 100;
		int comidaDisponible = FOOD_BASE_ENEMY_ARMY * multiplicadorPorcentaje / 100;
		
		// Limpiar ejercito anterior
		for (int i = 0; i < 4; i++) {
			getArmies()[1].set(i, new ArrayList<>());
		}
		
		// Ver si hay recursos para las unidades
		boolean sigueGenerando = true;
		while (sigueGenerando == true) {
			sigueGenerando = false;
			
			// añadir tropas enemigas
			if (maderaDisponible >= WOOD_BASE_ENEMY_ARMY && hierroDisponible >= IRON_BASE_ENEMY_ARMY) {
				((ArrayList<MilitaryUnit>) getArmies()[1].get(3)).add(new Cannon());
				maderaDisponible -= WOOD_BASE_ENEMY_ARMY;
				hierroDisponible -= IRON_BASE_ENEMY_ARMY;
				sigueGenerando = true;
			}
			
			if (maderaDisponible >= WOOD_COST_CROSSBOW && hierroDisponible >= IRON_COST_CROSSBOW) {
				((ArrayList<MilitaryUnit>) getArmies()[1].get(2)).add(new Crossbow());
				maderaDisponible = maderaDisponible - WOOD_COST_CROSSBOW;
				hierroDisponible = hierroDisponible - IRON_COST_CROSSBOW;
				sigueGenerando = true;
			}
			
			if (comidaDisponible >= FOOD_COST_SPEARMAN && maderaDisponible >= WOOD_COST_SPEARMAN
					&& hierroDisponible >= IRON_COST_SPEARMAN) {
				((ArrayList<MilitaryUnit>) getArmies()[1].get(1)).add(new Spearman());
				comidaDisponible = comidaDisponible - FOOD_COST_SPEARMAN;
				maderaDisponible = maderaDisponible - WOOD_COST_SPEARMAN;
				hierroDisponible = hierroDisponible - IRON_COST_SPEARMAN;
				sigueGenerando = true;
			}
			
			if (comidaDisponible >= FOOD_COST_SWORDSMAN && maderaDisponible >= WOOD_COST_SWORDSMAN
					&& hierroDisponible >= IRON_COST_SWORDSMAN) {
				((ArrayList<MilitaryUnit>) getArmies()[1].get(0)).add(new Swordsman());
				comidaDisponible = comidaDisponible - FOOD_COST_SWORDSMAN;
				maderaDisponible = maderaDisponible - WOOD_COST_SWORDSMAN;
				hierroDisponible = hierroDisponible - IRON_COST_SWORDSMAN;
				sigueGenerando = true;
			}
		}
	}
	
	// Bucle principal de la batalla True si ganamos False si perdemos
	public boolean executeBattle() {
		initInitialArmies();
		
		int[] costeCivilizacion = fleetResourceCost(getCivilizationArmy());
		int[] costeEnemigo		= fleetResourceCost(getEnemyArmy());
		
		getInitialCostFleet()[0][0] = costeCivilizacion[0];
		getInitialCostFleet()[0][1] = costeCivilizacion[1];
		getInitialCostFleet()[0][2] = costeCivilizacion[2];
		getInitialCostFleet()[1][0] = costeEnemigo[0];
		getInitialCostFleet()[1][1] = costeEnemigo[1];
		getInitialCostFleet()[1][2] = costeEnemigo[2];
		
		setInitialNumberUnitsCivilization(totalUnidadesCivilizacion());
		setInitialNumberUnitsEnemy(totalUnidadesEnemigo());
		
		boolean turnoEsCivilizacion = (Math.random() < 0.5 );
		
		while (quedanSuficientesUnidades()) {
			setBattleDevelopment(getBattleDevelopment());
			
			if (turnoEsCivilizacion) {
				turnoCivilizacion();
			} else {
				turnoEnemigo();
			}
			turnoEsCivilizacion = !turnoEsCivilizacion;
		}
		
		// calcular las perdidas
		calcularPerdidas();
		
		boolean civilizacionGana = getResourcesLooses()[0][3] <= getResourcesLooses()[1][3];
		
		if (!civilizacionGana) {
			getWasteWoodIron()[0] = 0;
			getWasteWoodIron()[1] = 0;
		}
		
		setBattleDevelopment(getBattleDevelopment());
		
		return civilizacionGana;
	}
	
	// Turno civilizacion
	private void turnoCivilizacion() {
		boolean puedeRepetir = true;
		
		while (puedeRepetir && quedanSuficientesUnidades()) {
			int grupoAtacante = getCivilizationGroupAttacker();
			if (grupoAtacante == -1) break;
			
			ArrayList<MilitaryUnit> grupoAtacanteList =
					(ArrayList<MilitaryUnit>) getArmies()[0].get(grupoAtacante);
			if (grupoAtacanteList.isEmpty()) {
				break;			
			}
			
			int idxAtacante = (int)(Math.random() * grupoAtacanteList.size());
			MilitaryUnit atacante = grupoAtacanteList.get(idxAtacante);
			
			int grupoDefensor = getGroupDefender(4, false);
			if (grupoDefensor == -1) {
				break;
			}
			
			ArrayList<MilitaryUnit> grupoDefensorList =
					(ArrayList<MilitaryUnit>) getArmies()[1].get(grupoDefensor);
			if (grupoDefensorList.isEmpty()) {
				break;
			}
			
			int idxDefensor = (int)(Math.random() * grupoDefensorList.size());
			MilitaryUnit defensor = grupoDefensorList.get(idxDefensor);
			
			int daño = atacante.attack();
			defensor.takeDamage(daño);
			
			String nombreAtacante = getNombreUnidad(grupoAtacante, true);
			String nombreDefensor = getNombreUnidad(grupoDefensor, false);
			
			setBattleDevelopment(getBattleDevelopment()
					+ "Attacks Civilization: " + nombreAtacante + " attacks " + nombreDefensor + "\n"
					+ nombreAtacante + " generates the damage = " + daño + "\n"
					+ nombreDefensor + " stays with armor = " + defensor.getActualArmor() + "\n");

			if (defensor.getActualArmor() <= 0) {
				setBattleDevelopment(getBattleDevelopment() + "we eliminate " + nombreDefensor + "\n");
				
				int chanceResiduo = (int)(Math.random() * 100) + 1;
				if (chanceResiduo <= defensor.getChanceGeneratinWaste()) {
					getWasteWoodIron()[0] = getWasteWoodIron()[0] + (defensor.getWoodCost() * PERCENTATGE_WASTE / 100);
					getWasteWoodIron()[1] = getWasteWoodIron()[1] + (defensor.getIronCost() * PERCENTATGE_WASTE / 100);
				}
				int[] costeUnidad = {defensor.getFoodCost(), defensor.getWoodCost(), defensor.getIronCost()};
				updateResourcesLooses(costeUnidad, false);
				
				grupoDefensorList.remove(idxDefensor);
				getActualNumberUnitsEnemy()[grupoDefensor] = getActualNumberUnitsEnemy()[grupoDefensor] -1;
				setEnemyDrops(getEnemyDrops() + 1);
			}
			
			int chanceRepetir = (int)(Math.random() * 100) + 1;
			puedeRepetir = (chanceRepetir <= atacante.getChanceAttackAgain());
		}
	}
	
	// Turno enemigo
	private void turnoEnemigo() {
		boolean puedeRepetir = true;
		
		while (puedeRepetir && quedanSuficientesUnidades()) {
			int grupoAtacante = getEnemyGroupAttacker();
			if (grupoAtacante == -1) break;
			
			ArrayList<MilitaryUnit> grupoAtacanteList =
					(ArrayList<MilitaryUnit>) getArmies()[1].get(grupoAtacante);
			if (grupoAtacanteList.isEmpty()) {
				break;			
			}
			
			int idxAtacante = (int)(Math.random() * grupoAtacanteList.size());
			MilitaryUnit atacante = grupoAtacanteList.get(idxAtacante);
			
			int grupoDefensor = getGroupDefender(9, true);
			if (grupoDefensor == -1) {
				break;
			}
			
			ArrayList<MilitaryUnit> grupoDefensorList =
					(ArrayList<MilitaryUnit>) getArmies()[0].get(grupoDefensor);
			if (grupoDefensorList.isEmpty()) {
				break;
			}
			
			int idxDefensor = (int)(Math.random() * grupoDefensorList.size());
			MilitaryUnit defensor = grupoDefensorList.get(idxDefensor);
			
			int daño = atacante.attack();
			defensor.takeDamage(daño);
			
			String nombreAtacante = getNombreUnidad(grupoAtacante, false);
			String nombreDefensor = getNombreUnidad(grupoDefensor, true);
			
			setBattleDevelopment(getBattleDevelopment()
					+ "Attacks Civilization: " + nombreAtacante + " attacks " + nombreDefensor + "\n"
					+ nombreAtacante + " generates the damage = " + daño + "\n"
					+ nombreDefensor + " stays with armor = " + defensor.getActualArmor() + "\n");

			if (defensor.getActualArmor() <= 0) {
				setBattleDevelopment(getBattleDevelopment() + "we eliminate " + nombreDefensor + "\n");
				
				int chanceResiduo = (int)(Math.random() * 100) + 1;
				if (chanceResiduo <= defensor.getChanceGeneratinWaste()) {
					getWasteWoodIron()[0] = getWasteWoodIron()[0] + (defensor.getWoodCost() * PERCENTATGE_WASTE / 100);
					getWasteWoodIron()[1] = getWasteWoodIron()[1] + (defensor.getIronCost() * PERCENTATGE_WASTE / 100);
				}
				int[] costeUnidad = {defensor.getFoodCost(), defensor.getWoodCost(), defensor.getIronCost()};
				updateResourcesLooses(costeUnidad, false);
				
				grupoDefensorList.remove(idxDefensor);
				getActualNumberUnitsEnemy()[grupoDefensor] = getActualNumberUnitsEnemy()[grupoDefensor] -1;
				setEnemyDrops(getEnemyDrops() + 1);
			}
			
			int chanceRepetir = (int)(Math.random() * 100) + 1;
			puedeRepetir = (chanceRepetir <= atacante.getChanceAttackAgain());
		}
	}
	
	// Auxiliares 
	
	private boolean quedanSuficientesUnidades() {
		int totalCiv     = totalUnidadesCivilizacion();
		int totalEnemigo = totalUnidadesEnemigo();
		int umbralCiv     = getInitialNumberUnitsCivilization() * 20 / 100;
		int umbralEnemigo = getInitialNumberUnitsEnemy() * 20 / 100;
		return totalCiv > umbralCiv && totalEnemigo > umbralEnemigo;
	}
	private int totalUnidadesCivilizacion() {
		int total = 0;
		for (int i = 0; i < 9; i++) {
			total = total + ((ArrayList<MilitaryUnit>) getArmies()[0].get(i)).size();
		}
		return total;
	}
	private int totalUnidadesEnemigo() {
		int total = 0;
		for (int i = 0; i < 4; i++) {
			total = total + ((ArrayList<MilitaryUnit>) getArmies()[1].get(i)).size();
		}
		return total;
	}
	private void calcularPerdidas() {
		getResourcesLooses()[0][3] = getResourcesLooses()[0][2]
				+ getResourcesLooses()[0][1] / 5
				+ getResourcesLooses()[0][0] / 10;
		getResourcesLooses()[1][3] = getResourcesLooses()[1][2]
				+ getResourcesLooses()[1][1] / 5
				+ getResourcesLooses()[1][0] / 10;
	}
	private String getNombreUnidad(int indice, boolean esCivilizacion) {
		if (esCivilizacion) {
			String[] nombres = {"Swordsman", "Spearman", "Crossbow", "Cannon",
					"Arrow Tower", "Catapult", "Rocket Launcher Tower", "Magician", "Priest"};
			if (indice >= 0 && indice < nombres.length) return nombres[indice];
		} else {
			String[] nombres = {"Swordsman", "Spearman", "Crossbow", "Cannon"};
			if (indice >= 0 && indice < nombres.length) return nombres[indice];
		}
		return "Unknown";
	}
	
	// unidades inicialesde cada vando
	public void initInitialArmies() {
		for (int i = 0; i < 9; i++) {
			ArrayList<MilitaryUnit> grupo = (ArrayList<MilitaryUnit>) getArmies()[0].get(i);
            getInitialArmies()[0][i] = grupo.size();
		}
		
		for (int i = 0; i < 4; i++) {
			ArrayList<MilitaryUnit> grupo = (ArrayList<MilitaryUnit>) getArmies()[1].get(i);
            getInitialArmies()[1][i] = grupo.size();
		}
	}
	
	// calcular coste total en recursos
	public int[] fleetResourceCost(ArrayList<MilitaryUnit> army) {
		int[] cost = new int[3];
		
		for (MilitaryUnit unit : army) {
			cost[0] += unit.getFoodCost();
			cost[1] += unit.getWoodCost();
			cost[2] += unit.getIronCost();
		}
		return cost;
	}
	
	// total de unidades de un ejercito
	public int initialFleerNumber(ArrayList<MilitaryUnit> army) {
		return army.size();
	}
	
	// Calcula el porsentaje restante de unidades 
	public int remainderPencentageFleet(ArrayList<MilitaryUnit> army, int initialNumber) {
		if (initialNumber == 0) {
			return 0;
		}
		
		return (army.size() * 100) / initialNumber;
	}
	
	// grupo que grupo ataca primero
	public int getCivilizationGroupAttacker() {
        int total = 0;
        for (int i = 0; i < 9; i++) {
            ArrayList<MilitaryUnit> grupo = (ArrayList<MilitaryUnit>) getArmies()[0].get(i);
            if (grupo.size() > 0) {
                total += CHANCE_ATTACK_CIVILIZATION_UNITS[i];
            }
        }

        if (total == 0) return -1;

        int numAleatorio = (int) (Math.random() * total) + 1;

        int acumulado = 0;
        for (int i = 0; i < 9; i++) {
            ArrayList<MilitaryUnit> grupo = (ArrayList<MilitaryUnit>) getArmies()[0].get(i);
            if (grupo.size() > 0) {
                acumulado += CHANCE_ATTACK_CIVILIZATION_UNITS[i];
                if (acumulado >= numAleatorio) {
                    return i;
                }
            }
        }
        return -1;
    }
	
	// Que jugador ataca primero
	public int getEnemyGroupAttacker() {
        int total = 0;
        for (int i = 0; i < 4; i++) {
            ArrayList<MilitaryUnit> grupo = (ArrayList<MilitaryUnit>) getArmies()[1].get(i);
            if (grupo.size() > 0) {
                total += CHANCE_ATTACK_ENEMY_UNITS[i];
            }
        }

        if (total == 0) return -1;

        int numAleatorio = (int) (Math.random() * total) + 1;

        int acumulado = 0;
        for (int i = 0; i < 4; i++) {
            ArrayList<MilitaryUnit> grupo = (ArrayList<MilitaryUnit>) getArmies()[1].get(i);
            if (grupo.size() > 0) {
                acumulado += CHANCE_ATTACK_ENEMY_UNITS[i];
                if (acumulado >= numAleatorio) {
                    return i;
                }
            }
        }
        return -1;
    }
	
	// grupo que defiende 
	public int getGroupDefender(int numGrupos, boolean esCivilizacion) {
		int indiceArmies;
	    
	    if (esCivilizacion) {
	        indiceArmies = 0;
	    } else {
	        indiceArmies = 1;
	    }
	    
        int total = 0;
        for (int i = 0; i < numGrupos; i++) {
            ArrayList<MilitaryUnit> grupo = (ArrayList<MilitaryUnit>) getArmies()[indiceArmies].get(i);
            total += grupo.size();
        }

        if (total == 0) return -1;

        int numAleatorio = (int) (Math.random() * total) + 1;

        int acumulado = 0;
        for (int i = 0; i < numGrupos; i++) {
            ArrayList<MilitaryUnit> grupo = (ArrayList<MilitaryUnit>) getArmies()[indiceArmies].get(i);
            acumulado += grupo.size();
            if (acumulado >= numAleatorio) {
                return i;
            }
        }
        return -1;
    }
	
	// curar a los soldados
	public void resetArmyArmor() {
		for (int i = 0; i < 9; i++) {
			ArrayList<MilitaryUnit> grupo = (ArrayList<MilitaryUnit>) getArmies()[0].get(i);
			// A cada unidad del grupo le decimos que resetee su armadura
	        for (MilitaryUnit unidad : grupo) {
	            unidad.resetArmor();
	        }
		}
	}
	
	// guardar el resto de los recursos 
	public void updateResourcesLooses(int[] cost, boolean esCivilizacion) {
	    int fila; 
	    
	    if (esCivilizacion) { // Si es true (es tu civilización)
	        fila = 0;
	    } else {              // Si es false (es el enemigo)
	        fila = 1;
	    }
	    
	    // cost[0]=food, cost[1]=wood, cost[2]=iron
	    getResourcesLooses()[fila][0] += cost[0]; // Comida
	    getResourcesLooses()[fila][1] += cost[1]; // Madera
	    getResourcesLooses()[fila][2] += cost[2]; // Hierro
	}
	
	// Este devuelve el log paso a paso que se fue guardando en la clase madre
	public String getBattleDevelopment() {
	    return super.getBattleDevelopment();
	}

	// Este genera el resumen visual con las estadísticas
	public String getBattleReport(int battles) {
	    String reporte = "";
	    reporte += "BATTLE NUMBER: " + battles + "\n";
	    reporte += "BATTLE STATISTICS\n\n";
	    
	    reporte += "Army planet\t\tUnits\tDrops\tInitial Army Enemy\tUnits\tDrops\n";
	    
	    // Aquí iría un bucle o una lista de líneas por cada unidad
	    // Ejemplo para una unidad (esto se repite para todas):
	    // reporte += "Swordsman\t\t" + initialArmies[0][0] + "\t" + civilizationDrops + ...
	    
	    reporte += "\n**************************************************************************************\n";
	    reporte += "Cost Army Civilization \t\t\t\t Cost Army Enemy\n";
	    reporte += "Food: " + getInitialCostFleet()[0][0] + "\t\t\t\t Food: " + getInitialCostFleet()[1][0] + "\n";
	    reporte += "Wood: " + getInitialCostFleet()[0][1] + "\t\t\t\t Wood: " + getInitialCostFleet()[1][1] + "\n";
	    reporte += "Iron: " + getInitialCostFleet()[0][2] + "\t\t\t\t Iron: " + getInitialCostFleet()[1][2] + "\n";
	    
	    reporte += "\n**************************************************************************************\n";
	    reporte += "Losses Army Civilization \t\t\t Losses Army Enemy\n";
	    reporte += "Food: " + getResourcesLooses()[0][0] + "\t\t\t\t Food: " + getResourcesLooses()[1][0] + "\n";
	    reporte += "Wood: " + getResourcesLooses()[0][1] + "\t\t\t\t Wood: " + getResourcesLooses()[1][1] + "\n";
	    reporte += "Iron: " + getResourcesLooses()[0][2] + "\t\t\t\t Iron: " + getResourcesLooses()[1][2] + "\n";
	    
	    reporte += "\n**************************************************************************************\n";
	    reporte += "Waste Generated:\n";
	    reporte += "Wood: " + getWasteWoodIron()[0] + "\n";
	    reporte += "Iron: " + getWasteWoodIron()[1] + "\n";
	    
	    return reporte;
	}
}
