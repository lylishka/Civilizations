package game;

public class Spearman extends AttackUnity {

	public Spearman(int tecDef, int tecAtk) {
        super(
                ARMOR_SPEARMAN + (tecDef * PLUS_ARMOR_SPEARMAN_BY_TECHNOLOGY * ARMOR_SPEARMAN / 100),
                ARMOR_SPEARMAN + (tecDef * PLUS_ARMOR_SPEARMAN_BY_TECHNOLOGY * ARMOR_SPEARMAN / 100),
                BASE_DAMAGE_SPEARMAN + (tecAtk * PLUS_ATTACK_SPEARMAN_BY_TECHNOLOGY * BASE_DAMAGE_SPEARMAN / 100)
            );
        }

        public Spearman() {
            super(ARMOR_SPEARMAN, ARMOR_SPEARMAN, BASE_DAMAGE_SPEARMAN);
        }

        public int getFoodCost() {
        	return FOOD_COST_SPEARMAN;
        }
        public int getWoodCost() {
        	return WOOD_COST_SPEARMAN; 
        }
        public int getIronCost() { 
        	return IRON_COST_SPEARMAN;
        }
        public int getManaCost() { 
        	return MANA_COST_SPEARMAN; 
        }
        public int getChanceGeneratinWaste() {
        	return CHANCE_GENERATNG_WASTE_SPEARMAN; 
        }
        public int getChanceAttackAgain() { 
        	return CHANCE_ATTACK_AGAIN_SPEARMAN; 
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
