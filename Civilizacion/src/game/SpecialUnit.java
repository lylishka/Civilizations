package game;

public class SpecialUnit implements MilitaryUnit, Variables {
	protected int armor;
	protected int initialArmor;
	protected int baseDamage;
	protected int experience;
	protected boolean sanctified;
    
	public SpecialUnit(int armor, int initialArmor, int baseDamage) {
		super();
		this.armor = armor;
		this.initialArmor = initialArmor;
		this.baseDamage = baseDamage;
	}
	
	public int getArmor() {
		return armor;
	}
	public void setArmor(int armor) {
		this.armor = armor;
	}
	public int getInitialArmor() {
		return initialArmor;
	}
	public void setInitialArmor(int initialArmor) {
		this.initialArmor = initialArmor;
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
	
	public int attack() {
		return 0;
	}
	
	public void takeDamage(int receivedDamage) {
		
	}
	
	public int getActualArmor() {
		return 0;
	}
	
	public int getFoodCost() {
		return 0;
	}
	
	public int getWoodCost() {
		return 0;
	}
	
	public int getIronCost() {
		return 0;
	}
	
	public int getManaCost() {
		return 0;
	}
	
	public int getChanceGeneratinWaste() {
		return 0;
	}
	
	public int getChanceAttackAgain() {
		return 0;
	}
	
	public void resetArmor() {
		
	}
    
    
}
