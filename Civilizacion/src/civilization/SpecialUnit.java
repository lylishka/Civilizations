package civilization;

public class SpecialUnit implements MilitaryUnit, Variables {
	private int armor;
    private int initialArmor;
    private int baseDamage;
    private int experience;
    private boolean sanctified;
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
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void takeDamage(int receivedDamage) {
		// TODO Auto-generated method stub
		
	}
	
	public int getActualArmor() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int getFoodCost() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int getWoodCost() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int getIronCost() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int getManaCost() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int getChanceGeneratinWaste() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int getChanceAttackAgain() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void resetArmor() {
		// TODO Auto-generated method stub
		
	}
    
    
}
