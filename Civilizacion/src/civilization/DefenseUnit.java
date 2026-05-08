package civilization;

public class DefenseUnit implements MilitaryUnit, Variables {
	private int armor;
	private int initialArmor;
	private int baseDamage;
	private int experience;
	private boolean sanctified;
	
	public DefenseUnit(int armor, int initialArmor, int baseDamage) {
		super();
		this.armor = armor;
		this.initialArmor = initialArmor;
		this.baseDamage = baseDamage;
		this.experience = 0;
        this.sanctified = false;
	}
	
	public void setArmor(int armor) {
		this.armor = armor;
	}
	
	public int getInitialArmor() {
		return initialArmor;
	}
	
	public int getBaseDamage() {
		return baseDamage;
	}
	
	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
	}
	
	public int getExperience() {
		return experience;
	}
	
	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	public boolean isSanctified() {
		return sanctified;
	}
	
	public void setSanctified(boolean sanctified) {
		this.sanctified = sanctified;
	}
	 
	public int attack() {
		return baseDamage;
	}
	 
	public void takeDamage(int receivedDamage) {
		this.setArmor(this.getActualArmor() - receivedDamage);
	}
	 
	public int getActualArmor() {
		return this.armor;
	}
	 
	public int getFoodCost() {
		return this.getFoodCost();
	}
	 
	public int getWoodCost() {
		return this.getWoodCost();
	}
	 
	public int getIronCost() {
		return this.getIronCost();
	}
	 
	public int getManaCost() {
		return this.getManaCost();
	}
	 
	public int getChanceGeneratinWaste() {
		return this.getChanceGeneratinWaste();
	}
	 
	public int getChanceAttackAgain() {
		return this.getChanceAttackAgain();
	}
	 
	public void resetArmor() {
		this.setArmor(this.getInitialArmor());
	}
}
