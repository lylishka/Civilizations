package game;

import java.util.ArrayList;

import logic.BuildingException;
import logic.ResourceException;

public class Civilization implements Variables{
	// Tecnologías y Edificios
    private int technologyDefense, technologyAtack;
    private int farm, smithy, carpentry, magicTower, church;
    
    // Recursos
    private int wood, iron, food, mana;
    
    // Estadísticas
    private int battles;

    // Array
    private ArrayList<MilitaryUnit>[] army;

	public Civilization(int technologyDefense, int technologyAtack, int farm, int smithy, int carpentry, int magicTower,
			int church, int wood, int iron, int food, int mana, int battles) {
		super();
		this.technologyDefense = technologyDefense;
		this.technologyAtack = technologyAtack;
		this.farm = farm;
		this.smithy = smithy;
		this.carpentry = carpentry;
		this.magicTower = magicTower;
		this.church = church;
		this.wood = wood;
		this.iron = iron;
		this.food = food;
		this.mana = mana;
		this.battles = battles;
		this.army = (ArrayList<MilitaryUnit>[]) new ArrayList[9];
		for (int i = 0; i < 9; i++) {
			this.army[i] = new ArrayList<MilitaryUnit>();
		}
	}

	public int getTechnologyDefense() {
		return technologyDefense;
	}

	public void setTechnologyDefense(int technologyDefense) {
		this.technologyDefense = technologyDefense;
	}

	public int getTechnologyAtack() {
		return technologyAtack;
	}

	public void setTechnologyAtack(int technologyAtack) {
		this.technologyAtack = technologyAtack;
	}

	public int getFarm() {
		return farm;
	}

	public void setFarm(int farm) {
		this.farm = farm;
	}

	public int getSmithy() {
		return smithy;
	}

	public void setSmithy(int smithy) {
		this.smithy = smithy;
	}

	public int getCarpentry() {
		return carpentry;
	}

	public void setCarpentry(int carpentry) {
		this.carpentry = carpentry;
	}

	public int getMagicTower() {
		return magicTower;
	}

	public void setMagicTower(int magicTower) {
		this.magicTower = magicTower;
	}

	public int getChurch() {
		return church;
	}

	public void setChurch(int church) {
		this.church = church;
	}

	public int getWood() {
		return wood;
	}

	public void setWood(int wood) {
		this.wood = wood;
	}

	public int getIron() {
		return iron;
	}

	public void setIron(int iron) {
		this.iron = iron;
	}

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getBattles() {
		return battles;
	}

	public void setBattles(int battles) {
		this.battles = battles;
	}

	public ArrayList<MilitaryUnit>[] getArmy() {
		return army;
	}

	public void setArmy(ArrayList<MilitaryUnit>[] army) {
		this.army = army;
	}
    
	
	// Metodos Estructuras
	void newChurch () throws ResourceException {
		if (food < FOOD_COST_CHURCH || wood < WOOD_COST_CHURCH || iron < IRON_COST_CHURCH)  {
			throw new ResourceException("No hay recursos suficientes para construir la iglesia");
		}
		
		food -= FOOD_COST_CHURCH;
        wood -= WOOD_COST_CHURCH;
        iron -= IRON_COST_CHURCH;
        
        church += 1;
	}
	
	void newMagicTower () throws ResourceException {
		if (food < FOOD_COST_MAGICTOWER || wood < WOOD_COST_MAGICTOWER || iron < IRON_COST_MAGICTOWER) {
            throw new ResourceException("No hay recursos suficientes para construir la Torre Mágica.");
        }
		
		food -= FOOD_COST_MAGICTOWER;
        wood -= WOOD_COST_MAGICTOWER;
        iron -= IRON_COST_MAGICTOWER;
        
        magicTower += 1;
	}
	
	void newFarm () throws ResourceException {
		if (food < FOOD_COST_FARM || wood < WOOD_COST_FARM || iron < IRON_COST_FARM) {
			throw new ResourceException("No hay recursos suficientes para construir la granja");
		}
		
		food -= FOOD_COST_FARM;
		wood -= WOOD_COST_FARM;
		iron -= IRON_COST_FARM;
		
		farm += 1;;
	}
	
	void newCarpentry () throws ResourceException {
		if (food < FOOD_COST_CARPENTRY || wood < WOOD_COST_CARPENTRY || iron < IRON_COST_CARPENTRY) {
			throw new ResourceException("No hay recursos suficientes para construir una carpinteria");
		}
		
		food -= FOOD_COST_CARPENTRY;
		wood -= WOOD_COST_CARPENTRY;
		iron -= IRON_COST_CARPENTRY;
		
		carpentry += 1;
	}
	
	void newSmithy () throws ResourceException {
		if (food < FOOD_COST_SMITHY || wood < WOOD_COST_SMITHY || iron < IRON_COST_SMITHY) {
            throw new ResourceException("No hay recursos suficientes para construir la Herrería.");
        }
		
        food   -= FOOD_COST_SMITHY;
        wood   -= WOOD_COST_SMITHY;
        iron   -= IRON_COST_SMITHY;
        
        smithy += 1;
	}
	
	// tecnologia
	
	public void upgradeTechnologyDefense() throws ResourceException {
		// Coste = base + (nivel actual * incremento por nivel)
		int cost = UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST
		           + (technologyDefense * UPGRADE_PLUS_DEFENSE_TECHNOLOGY_IRON_COST);

		if (iron < cost) {
		    throw new ResourceException("No hay hierro suficiente para mejorar la tecnología de defensa."
		            + " Necesitas: " + cost + "  Tienes: " + iron);
		}
		iron -= cost;
		
		technologyDefense += 1;
	}
	
	void upgradeTechnologyAttack () throws ResourceException{
		int cost = UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST
				+ (technologyAtack * UPGRADE_PLUS_ATTACK_TECHNOLOGY_IRON_COST);
		
		if (iron < cost) {
			throw new ResourceException("No hay hierro suficiente para mejorar la tecnologia de ataque. "
					+ "\nNecesitas: " + cost + " Tienes: " + iron);
		}
		iron -= cost;
		technologyAtack += 1;
	}
	
	// Unidades
	
	void newSwordsman (int n) throws ResourceException {
		int count = 0;

	    for (int i = 0; i < n; i += 1) {
	        // ¿Tenemos recursos para una más?
	        if (food >= FOOD_COST_SWORDSMAN && wood >= WOOD_COST_SWORDSMAN && iron >= IRON_COST_SWORDSMAN) {
	            army[0].add(new Swordsman(technologyDefense, technologyAtack));
	            food -= FOOD_COST_SWORDSMAN;
	            wood -= WOOD_COST_SWORDSMAN;
	            iron -= IRON_COST_SWORDSMAN;
	            count += 1;
	        } else {
	            // Ya no podemos crear más, salimos del bucle
	            break;
	        }
	    }

	    // Si no se pudieron crear todas las pedidas, lanzamos excepción
	    if (count < n) {
	        throw new ResourceException("Se añadieron " + count 
	                + " Swordsman (pedidos: " + n + ").");
	    }		
	}
	
	void newSpearman (int n) throws ResourceException {
		int count = 0;
		for (int i =0; i < n; i +=1) {
			if (food >= FOOD_COST_SPEARMAN && wood >= WOOD_COST_SPEARMAN && iron >= IRON_COST_SPEARMAN) {
	            army[1].add(new Spearman(technologyDefense, technologyAtack));
	            food -= FOOD_COST_SPEARMAN;
	            wood -= WOOD_COST_SPEARMAN;
	            iron -= IRON_COST_SPEARMAN;
	            count += 1;
	        } else {
	            break;
	        }
		}
		if (count < n) {
	        throw new ResourceException("Solo se añadieron " + count + " Spearman (pedidos: " + n + ").");
	    }
	}
	
	void newCrossbow(int n) throws ResourceException {
		int count = 0;
	    for (int i = 0; i < n; i += 1) {
	        if (wood >= WOOD_COST_CROSSBOW && iron >= IRON_COST_CROSSBOW) {
	            army[2].add(new Crossbow(technologyDefense, technologyAtack));
	            wood -= WOOD_COST_CROSSBOW;
	            iron -= IRON_COST_CROSSBOW;
	            count += 1;
	        } else {
	            break;
	        }
	    }
	    if (count < n) {
	        throw new ResourceException("Solo se añadieron " + count + " Crossbow (pedidos: " + n + ").");
	    }
	}
	
	void newCannon(int n) throws ResourceException {
		int count = 0;
	    for (int i = 0; i < n; i += 1) {
	        if (wood >= WOOD_COST_CANNON && iron >= IRON_COST_CANNON) {
	            army[3].add(new Cannon(technologyDefense, technologyAtack));
	            wood -= WOOD_COST_CANNON;
	            iron -= IRON_COST_CANNON;
	            count += 1;
	        } else {
	            break;
	        }
	    }
	    if (count < n) {
	        throw new ResourceException("Solo se añadieron " + count + " Cannon (pedidos: " + n + ").");
	    }
	}
	
	void newArrowTower(int n) throws ResourceException {
		int count = 0;
	    for (int i = 0; i < n; i += 1) {
	        if (wood >= WOOD_COST_ARROWTOWER && iron >= IRON_COST_ARROWTOWER) {
	            army[4].add(new ArrowTower(technologyDefense, technologyAtack));
	            wood -= WOOD_COST_ARROWTOWER;
	            iron -= IRON_COST_ARROWTOWER;
	            count += 1;
	        } else {
	            break;
	        }
	    }
	    if (count < n) {
	        throw new ResourceException("Solo se añadieron " + count + " Arrow Tower (pedidas: " + n + ").");
	    }
	}
	
	void newCatapult(int n) throws ResourceException {
		int count = 0;
	    for (int i = 0; i < n; i += 1) {
	        if (wood >= WOOD_COST_CATAPULT && iron >= IRON_COST_CATAPULT) {
	            army[5].add(new Catapult(technologyDefense, technologyAtack));
	            wood -= WOOD_COST_CATAPULT;
	            iron -= IRON_COST_CATAPULT;
	            count += 1;
	        } else {
	            break;
	        }
	    }
	    if (count < n) {
	        throw new ResourceException("Solo se añadieron " + count + " Catapult (pedidas: " + n + ").");
	    }
	}
	
	void newRocketLauncher(int n) throws ResourceException {
		int count = 0;
	    for (int i = 0; i < n; i += 1) {
	        if (wood >= WOOD_COST_ROCKETLAUNCHERTOWER && iron >= IRON_COST_ROCKETLAUNCHERTOWER) {
	            army[6].add(new RocketLauncherTower(technologyDefense, technologyAtack));
	            wood -= WOOD_COST_ROCKETLAUNCHERTOWER;
	            iron -= IRON_COST_ROCKETLAUNCHERTOWER;
	            count += 1;
	        } else {
	            break;
	        }
	    }
	    if (count < n) {
	        throw new ResourceException("Solo se añadieron " + count + " Rocket Launcher (pedidas: " + n + ").");
	    }
	}
	
	void newMagician(int n) throws ResourceException, BuildingException {
		if (magicTower < 1) {
	        throw new BuildingException("Necesitas al menos una Torre Mágica para crear Magos.");
	    }
	    int count = 0;
	    for (int i = 0; i < n; i += 1) {
	        if (food >= FOOD_COST_MAGICIAN && wood >= WOOD_COST_MAGICIAN && iron >= IRON_COST_MAGICIAN && mana >= MANA_COST_MAGICIAN) {
	            army[7].add(new Magician(technologyDefense, technologyAtack));
	            food -= FOOD_COST_MAGICIAN;
	            wood -= WOOD_COST_MAGICIAN;
	            iron -= IRON_COST_MAGICIAN;
	            mana -= MANA_COST_MAGICIAN;
	            count += 1;
	        } else {
	            break;
	        }
	    }
	    if (count < n) {
	        throw new ResourceException("Solo se añadieron " + count + " Magician (pedidos: " + n + ").");
	    }
	}
	
	void newPriest(int n) throws ResourceException, BuildingException {
		if (church < 1) {
	        throw new BuildingException("Necesitas al menos una Iglesia para crear Sacerdotes.");
	    }
	    int count = 0;
	    for (int i = 0; i < n; i += 1) {
	        // Comprobamos iglesias disponibles Y recursos
	        if (army[8].size() < church && food >= FOOD_COST_PRIEST && mana >= MANA_COST_PRIEST) {
	            army[8].add(new Priest(technologyDefense, technologyAtack));
	            food -= FOOD_COST_PRIEST;
	            mana -= MANA_COST_PRIEST;
	            count += 1;
	        } else {
	            break;
	        }
	    }
	    if (count < n) {
	        throw new ResourceException("Solo se añadieron " + count + " Priest (pedidos: " + n + ").");
	    }
	}
	
	// Mostrar estado
	
	void printStats () {
		System.out.println("***************************CIVILIZATION STATS***************************");

	    System.out.println("--------------------------------------------------TECHNOLOGY----------------------------------------");
	    System.out.printf("  %-20s %-20s%n", "Attack", "Defense");
	    System.out.printf("  %-20d %-20d%n", technologyAtack, technologyDefense);

	    System.out.println("---------------------------------------------------BUILDINGS----------------------------------------");
	    System.out.printf("  %-10s %-10s %-12s %-13s %-10s%n", "Farm", "Smithy", "Carpentry", "Magic Tower", "Church");
	    System.out.printf("  %-10d %-10d %-12d %-13d %-10d%n", farm, smithy, carpentry, magicTower, church);

	    System.out.println("----------------------------------------------------DEFENSES----------------------------------------");
	    System.out.printf("  %-14s %-14s %-20s%n", "Arrow Tower", "Catapult", "Rocket Launcher");
	    System.out.printf("  %-14d %-14d %-20d%n", army[4].size(), army[5].size(), army[6].size());

	    System.out.println("------------------------------------------------ATTACK UNITS----------------------------------------");
	    System.out.printf("  %-12s %-12s %-12s %-12s%n", "Swordsman", "Spearman", "Crossbow", "Cannon");
	    System.out.printf("  %-12d %-12d %-12d %-12d%n", army[0].size(), army[1].size(), army[2].size(), army[3].size());

	    System.out.println("----------------------------------------------SPECIAL UNITS----------------------------------------");
	    System.out.printf("  %-14s %-14s%n", "Magician", "Priest");
	    System.out.printf("  %-14d %-14d%n", army[7].size(), army[8].size());

	    System.out.println("---------------------------------------------------RESOURCES----------------------------------------");
	    System.out.printf("  %-10s %-10s %-10s %-10s%n", "Food", "Wood", "Iron", "Mana");
	    System.out.printf("  %-10d %-10d %-10d %-10d%n", food, wood, iron, mana);

	    System.out.println("----------------------------------------GENERATION RESOURCES----------------------------------------");
	    System.out.printf("  %-10s %-10s %-10s %-10s%n", "Food", "Wood", "Iron", "Mana");
	    int genFood = CIVILIZATION_FOOD_GENERATED + (farm      * CIVILIZATION_FOOD_GENERATED_PER_FARM);
	    int genWood = CIVILIZATION_WOOD_GENERATED + (carpentry * CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY);
	    int genIron = CIVILIZATION_IRON_GENERATED + (smithy    * CIVILIZATION_IRON_GENERATED_PER_SMITHY);
	    int genMana = magicTower * CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER;
	    System.out.printf("  %-10d %-10d %-10d %-10d%n", genFood, genWood, genIron, genMana);
	}
}


