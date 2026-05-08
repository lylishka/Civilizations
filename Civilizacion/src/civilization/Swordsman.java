package civilization;

public class Swordsman extends AttackUnity {

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

	public int getFoodCost() {
		return FOOD_COST_SWORDSMAN;
	}
	
	public int getWoodCost() {
		return WOOD_COST_SWORDSMAN; 
	}
	
    public int getIronCost() {
    	return IRON_COST_SWORDSMAN; 
    }
    
    public int getManaCost() { 
    	return MANA_COST_SWORDSMAN; 
    }
    
    public int getChanceGeneratinWaste() {
    	return CHANCE_GENERATNG_WASTE_SWORDSMAN; 
    }
    
    public int getChanceAttackAgain() { 
    	return CHANCE_ATTACK_AGAIN_SWORDSMAN; 
    }

	public int attack() {
		int daño = this.getBaseDamage();
		
		daño += (this.getBaseDamage() * this.getExperience() / 100);
		
		if (this.isSanctified()) {
			daño += (this.getBaseDamage() * PLUS_ATTACK_UNIT_SANCTIFIED / 100);
		}
		
		return daño;
	}
	
	public void takeDamage(int receivedDamage) {
		this.setArmor(this.getArmor() - receivedDamage);
	}

	public int getActualArmor() {
		return this.getArmor();
	}

	public void resetArmor() {
		this.setArmor(this.getInitialArmor());
	}
}
