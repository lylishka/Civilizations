package civilization;

public class RocketLauncherTower extends DefenseUnit implements MilitaryUnit {
	public RocketLauncherTower(int armor, int baseDamage) {
		super(armor, 6, baseDamage);
	}
	
	public int attack() {
		return this.getBaseDamage();
	}
	
	public void takeDamage(int damage) {
		this.setArmor(this.getActualArmor() - damage);
	}
	
	public int getActualArmor() {
		return this.getActualArmor();
	}
}
