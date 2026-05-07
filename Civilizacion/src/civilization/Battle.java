package civilization;

import java.util.ArrayList;

public class Battle {
	// Almacenar Unidades
		private ArrayList<MilitaryUnit> civilizationArmy;
		private ArrayList<MilitaryUnit> enemyArmy;
		
		//
		private ArrayList[] armies;
		
		// Desarrollo de la Batalla
		private String battleDevelopment;
		
		// Costes iniciales [2][3]
		private int[][] initialCostFleet;
		
		// Unidades iniciales para control de fin de batalla (20%)
		private int initialNumberUnitsCivilization;
		private int initialNumberUnitsEnemy;
		
		// Residuos generados: [madera, hierro]
		private int[] wasteWoodIron;
		
		// Bajas de cada bando
		private int enemyDrops;
		private int civilizationDrops;
		
		// PÃ©rdidas de recursos: [2][4]
		private int[][] resourcesLooses;
		
		// Cantidad de cada tipo de unidad al inicio: [2][9]
		private int[][] initialArmies;
		
		// Cantidad de unidades actuales de cada tipo: [9] para cada uno
		private int[] actualNumberUnitsCivilization;
		private int[] actualNumberUnitsEnemy;
		
		
		public Battle(ArrayList<MilitaryUnit> civilizationArmy, ArrayList<MilitaryUnit> enemyArmy) {
			super();
			this.civilizationArmy = civilizationArmy;
			this.enemyArmy = enemyArmy;
			this.battleDevelopment = "";
			
			this.armies = new ArrayList[2];
			this.armies[0] = new ArrayList<>(9);
			this.armies[1] = new ArrayList<>(9);
			
			for (int i = 0; i < 10; i++ ) {
				this.armies[0].add(new ArrayList<MilitaryUnit>());
				this.armies[1].add(new ArrayList<MilitaryUnit>());
			}
			
			this.initialCostFleet = new int[2][3];
			this.resourcesLooses = new int[2][4];
			this.initialArmies = new int[2][9];
			this.wasteWoodIron = new int[2];
			this.actualNumberUnitsCivilization = new int[9];
			this.actualNumberUnitsEnemy = new int[9];
		}

		public ArrayList<MilitaryUnit> getCivilizationArmy() {
			return civilizationArmy;
		}

		public void setCivilizationArmy(ArrayList<MilitaryUnit> civilizationArmy) {
			this.civilizationArmy = civilizationArmy;
		}

		public ArrayList<MilitaryUnit> getEnemyArmy() {
			return enemyArmy;
		}

		public void setEnemyArmy(ArrayList<MilitaryUnit> enemyArmy) {
			this.enemyArmy = enemyArmy;
		}

		public ArrayList[] getArmies() {
			return armies;
		}

		public void setArmies(ArrayList[] armies) {
			this.armies = armies;
		}

		public String getBattleDevelopment() {
			return battleDevelopment;
		}

		public void setBattleDevelopment(String battleDevelopment) {
			this.battleDevelopment = battleDevelopment;
		}

		public int[][] getInitialCostFleet() {
			return initialCostFleet;
		}

		public void setInitialCostFleet(int[][] initialCostFleet) {
			this.initialCostFleet = initialCostFleet;
		}

		public int getInitialNumberUnitsCivilization() {
			return initialNumberUnitsCivilization;
		}

		public void setInitialNumberUnitsCivilization(int initialNumberUnitsCivilization) {
			this.initialNumberUnitsCivilization = initialNumberUnitsCivilization;
		}

		public int getInitialNumberUnitsEnemy() {
			return initialNumberUnitsEnemy;
		}

		public void setInitialNumberUnitsEnemy(int initialNumberUnitsEnemy) {
			this.initialNumberUnitsEnemy = initialNumberUnitsEnemy;
		}

		public int[] getWasteWoodIron() {
			return wasteWoodIron;
		}

		public void setWasteWoodIron(int[] wasteWoodIron) {
			this.wasteWoodIron = wasteWoodIron;
		}

		public int getEnemyDrops() {
			return enemyDrops;
		}

		public void setEnemyDrops(int enemyDrops) {
			this.enemyDrops = enemyDrops;
		}

		public int getCivilizationDrops() {
			return civilizationDrops;
		}

		public void setCivilizationDrops(int civilizationDrops) {
			this.civilizationDrops = civilizationDrops;
		}

		public int[][] getResourcesLooses() {
			return resourcesLooses;
		}

		public void setResourcesLooses(int[][] resourcesLooses) {
			this.resourcesLooses = resourcesLooses;
		}

		public int[][] getInitialArmies() {
			return initialArmies;
		}

		public void setInitialArmies(int[][] initialArmies) {
			this.initialArmies = initialArmies;
		}

		public int[] getActualNumberUnitsCivilization() {
			return actualNumberUnitsCivilization;
		}

		public void setActualNumberUnitsCivilization(int[] actualNumberUnitsCivilization) {
			this.actualNumberUnitsCivilization = actualNumberUnitsCivilization;
		}

		public int[] getActualNumberUnitsEnemy() {
			return actualNumberUnitsEnemy;
		}


		public void setActualNumberUnitsEnemy(int[] actualNumberUnitsEnemy) {
			this.actualNumberUnitsEnemy = actualNumberUnitsEnemy;
		}	
}
