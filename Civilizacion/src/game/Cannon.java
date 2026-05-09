package game;

public class Cannon extends AttackUnity {

	public Cannon(int tecDef, int tecAtk) {
        super(
            ARMOR_CANNON + (tecDef * PLUS_ARMOR_CANNON_BY_TECHNOLOGY * ARMOR_CANNON / 100),
            ARMOR_CANNON + (tecDef * PLUS_ARMOR_CANNON_BY_TECHNOLOGY * ARMOR_CANNON / 100),
            BASE_DAMAGE_CANNON + (tecAtk * PLUS_ATTACK_CANNON_BY_TECHNOLOGY * BASE_DAMAGE_CANNON / 100)
        );
    }

    public Cannon() {
        super(ARMOR_CANNON, ARMOR_CANNON, BASE_DAMAGE_CANNON);
    }

    public int getFoodCost() {
    	return FOOD_COST_CANNON; 
    }
    
    public int getWoodCost() { 
    	return WOOD_COST_CANNON; 
    }
    
    public int getIronCost() { 
    	return IRON_COST_CANNON; 
    }
    
    public int getManaCost() { 
    	return MANA_COST_CANNON; 
    }
    
    public int getChanceGeneratinWaste() { 
    	return CHANCE_GENERATNG_WASTE_CANNON; 
    }
    
    public int getChanceAttackAgain() {
    	return CHANCE_ATTACK_AGAIN_CANNON; 
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
