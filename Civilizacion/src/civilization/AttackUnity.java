package civilization;

public abstract class AttackUnity implements MilitaryUnit, Variables {
	private int armor;
	private int initialArmor;
	private int baseDamage;
	private int experience;
	private boolean sanctified;
	
	public AttackUnity(int armor, int initialArmor, int baseDamage) {
		super();
		this.armor = armor;
        this.initialArmor = initialArmor;
        this.baseDamage = baseDamage;
        this.experience = 0;       
        this.sanctified = false;
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
}
