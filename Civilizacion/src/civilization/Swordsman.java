package civilization;

public class Swordsman extends AttackUnity{

	public Swordsman(int tecDef, int tecAtk) {
		super(
				ARMOR_SWORDSMAN + (tecDef * PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY * ARMOR_SWORDSMAN / 100),    // Esto es el armor
				ARMOR_SWORDSMAN + (tecDef * PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY * ARMOR_SWORDSMAN / 100),    // Esto es el initialArmor
				BASE_DAMAGE_SWORDSMAN + (tecAtk * PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY * BASE_DAMAGE_SWORDSMAN / 100) // Esto es el baseDamage
			);
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
