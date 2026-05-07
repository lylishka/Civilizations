package civilization;

import java.util.ArrayList;

public class Civilization {
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
	void newChurch () {
	}
	
	void newMagicTower () {
	}
	
	void newFarm () {
	}
	
	void newCarpentry () {
	}
	
	void newSmithy () {
	}
	
	// tecnologia
	
	void upgradeTechnologyDefense () {
		}
	
	void upgradeTechnologyAttack () {
	}
	
	// Unidades
	
	void newSwordsman (int n) {
	}
	
	void newSpearman (int n) {
	}
	
	void newCrossbow(int n) {
	}
	
	void newCannon(int n) {
	}
	
	void newArrowTower(int n) {
	}
	
	void newCatapult(int n) {
	}
	
	void newRocketLauncher(int n) {
	}
	
	void newMagician(int n) {
	}
	
	void newPriest(int n) {
	}
	
	// Mostrar estado
	
	void printStats () {
	}
}
