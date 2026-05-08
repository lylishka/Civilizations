package civilization;

public class Catapult extends DefenseUnit implements MilitaryUnit {
	public Catapult(int armor, int baseDamage) {
		super(armor, 5, baseDamage);
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
