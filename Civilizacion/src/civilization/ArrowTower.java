package civilization;

public class ArrowTower extends DefenseUnit implements MilitaryUnit {
	public ArrowTower(int armor, int baseDamage) {
		super(armor, 4, baseDamage);
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
