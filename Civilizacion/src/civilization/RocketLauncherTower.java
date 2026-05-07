package civilization;

public class RocketLauncherTower extends DefenseUnit implements MilitaryUnit {
	public RocketLauncherTower(int armor, int baseDamage) {
		super(armor, 6, baseDamage);
	}
	
	public int attack() {
		return this.getBaseDamage();
	}
	
	public void takeDamage(int damage) {
		this.setArmor(this.getArmor() - damage);
	}
	
	public int getActualArmor() {
		return this.getArmor();
	}
}
