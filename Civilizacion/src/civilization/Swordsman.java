package civilization;

public class Swordsman extends AttackUnity{

	public Swordsman(int tecDef, int tecAtk) {
		super(
				ARMOR_SWORDSMAN + (tecDef * PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY * ARMOR_SWORDSMAN / 100),    // Esto es el armor
				ARMOR_SWORDSMAN + (tecDef * PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY * ARMOR_SWORDSMAN / 100),    // Esto es el initialArmor
				BASE_DAMAGE_SWORDSMAN + (tecAtk * PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY * BASE_DAMAGE_SWORDSMAN / 100) // Esto es el baseDamage
			);
	}
	
	public Swordsman() {
		super(ARMOR_SWORDSMAN, ARMOR_SWORDSMAN, BASE_DAMAGE_SWORDSMAN);
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
