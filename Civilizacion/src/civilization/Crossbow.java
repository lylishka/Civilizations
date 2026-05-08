package civilization;

public class Crossbow extends AttackUnity {

	 public Crossbow(int tecDef, int tecAtk) {
	        super(
	            ARMOR_CROSSBOW + (tecDef * PLUS_ARMOR_CROSSBOW_BY_TECHNOLOGY * ARMOR_CROSSBOW / 100),
	            ARMOR_CROSSBOW + (tecDef * PLUS_ARMOR_CROSSBOW_BY_TECHNOLOGY * ARMOR_CROSSBOW / 100),
	            BASE_DAMAGE_CROSSBOW + (tecAtk * PLUS_ATTACK_CROSSBOW_BY_TECHNOLOGY * BASE_DAMAGE_CROSSBOW / 100)
	        );
	    }
	 
	 public Crossbow() {
	        super(ARMOR_CROSSBOW, ARMOR_CROSSBOW, BASE_DAMAGE_CROSSBOW);
	    }

	    public int getFoodCost() { 
	    	return FOOD_COST_CROSSBOW; 
	    }
	    public int getWoodCost() { 
	    	return WOOD_COST_CROSSBOW;
	    }
	    public int getIronCost() { 
	    	return IRON_COST_CROSSBOW; 
	    }
	    public int getManaCost() { 
	    	return MANA_COST_CROSSBOW; 
	    }
	    public int getChanceGeneratinWaste() { 
	    	return CHANCE_GENERATNG_WASTE_CROSSBOW; 
	    }
	    public int getChanceAttackAgain() { 
	    	return CHANCE_ATTACK_AGAIN_CROSSBOW; 
	    }

		public int attack() {
			return 0;
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
