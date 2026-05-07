package civilization;

public class Priest extends SpecialUnit implements MilitaryUnit {
	public Priest(int armor, int baseDamage) {
		super(armor, 8, baseDamage);
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
