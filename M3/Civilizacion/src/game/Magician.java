package game;

public class Magician extends SpecialUnit implements MilitaryUnit {
	public Magician(int armor, int baseDamage) {
		super(armor, 7, baseDamage);
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
