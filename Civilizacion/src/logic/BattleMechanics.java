package logic;

import java.util.ArrayList;

import game.MilitaryUnit;
import game.Variables;

public class BattleMechanics extends Battle implements Variables {
	public BattleMechanics(ArrayList<MilitaryUnit> civilizationArmy, ArrayList<MilitaryUnit> enemyArmy) {
		super(civilizationArmy, enemyArmy);
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
