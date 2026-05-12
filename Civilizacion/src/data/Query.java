package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import game.AttackUnity;
import game.Civilization;
import game.DefenseUnit;
import game.MilitaryUnit;
import game.SpecialUnit;


public class Query {
	
	private DBConection dbConection;
	private Connection conn;
	
	public Query() {
		try {
			dbConection = new DBConection();
			dbConection.conectar();
			conn = dbConection.getConn();
		} catch (Exception e) {
			System.out.println("No se puedo conectar para guardar");
		}
	}
	
	// Metodo Principal
	public void saveGame(Civilization civ, int civilizationId) throws SQLException {
		try {
            saveCivilization(civ);
            saveAttackUnits(civ, civilizationId);
            saveDefenseUnits(civ, civilizationId);
            saveSpecialUnits(civ, civilizationId);
        } catch (SQLException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
	}
	
	// Guardar Civilizacion
	public void saveCivilization(Civilization civ) throws SQLException {
		String update = "INSERT INTO civilizacion_stats " +
                "(wood_amount, iron_amount, food_amount, mana_amount, " +
                "magicTower_counter, church_counter, farm_counter, " +
                "smithy_counter, carpentry_counter, " +
                "technology_defense_level, technology_attack_level, battles_counter) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement ps = conn.prepareStatement(update);
		
		ps.setInt(1,  civ.getWood());
        ps.setInt(2,  civ.getIron());
        ps.setInt(3,  civ.getFood());
        ps.setInt(4,  civ.getMana());
        ps.setInt(5,  civ.getMagicTower());
        ps.setInt(6,  civ.getChurch());
        ps.setInt(7,  civ.getFarm());
        ps.setInt(8,  civ.getSmithy());
        ps.setInt(9,  civ.getCarpentry());
        ps.setInt(10, civ.getTechnologyDefense());
        ps.setInt(11, civ.getTechnologyAtack());
        ps.setInt(12, civ.getBattles());

        ps.executeUpdate();
	}
	// Actualizar los datos
	public void updateCivilization(Civilization civ, int civilizationId) throws SQLException {
        
        String sql = "UPDATE civilizacion_stats SET " +
                "wood_amount=?, iron_amount=?, food_amount=?, mana_amount=?, " +
                "magicTower_counter=?, church_counter=?, farm_counter=?, " +
                "smithy_counter=?, carpentry_counter=?, " +
                "technology_defense_level=?, technology_attack_level=?, " +
                "battles_counter=? " +
                "WHERE civilization_id=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1,  civ.getWood());
        ps.setInt(2,  civ.getIron());
        ps.setInt(3,  civ.getFood());
        ps.setInt(4,  civ.getMana());
        ps.setInt(5,  civ.getMagicTower());
        ps.setInt(6,  civ.getChurch());
        ps.setInt(7,  civ.getFarm());
        ps.setInt(8,  civ.getSmithy());
        ps.setInt(9,  civ.getCarpentry());
        ps.setInt(10, civ.getTechnologyDefense());
        ps.setInt(11, civ.getTechnologyAtack());
        ps.setInt(12, civ.getBattles());
        ps.setInt(13, civilizationId);
        
        ps.executeUpdate();
        System.out.println("Civilización actualizada");
    }
	
	 public void saveAttackUnits(Civilization civ, int civilizationId) throws SQLException {
	        // Borramos las unidades antiguas de esta civilización
		 PreparedStatement del = conn.prepareStatement(
		            "DELETE FROM attack_units_stats WHERE civilization_id=?"
		        );
		        del.setInt(1, civilizationId);
		        del.executeUpdate();

		        String sql = "INSERT INTO attack_units_stats " +
		                     "(civilization_id, type, armor, base_damage, experience, sanctified) " +
		                     "VALUES (?,?,?,?,?,?)";

		        String[] types = {"Swordsman", "Spearman", "Crossbow", "Cannon"};

		        PreparedStatement ps = conn.prepareStatement(sql);

		        for (int i = 0; i <= 3; i++) {
		            for (MilitaryUnit unit : civ.getArmy()[i]) {
		                AttackUnity au = (AttackUnity) unit; // AttackUnity con Y
		                ps.setInt(1,    civilizationId);
		                ps.setString(2, types[i]);
		                ps.setInt(3,    au.getActualArmor());
		                ps.setInt(4,    au.getBaseDamage());
		                ps.setInt(5,    au.getExperience());
		                ps.setInt(6,    au.isSanctified() ? 1 : 0);
		                ps.executeUpdate();
		            }
		        }
		        System.out.println("Unidades de ataque guardadas");
	    }
	 public void saveDefenseUnits(Civilization civ, int civilizationId) throws SQLException {
		 PreparedStatement del = conn.prepareStatement(
		            "DELETE FROM defense_units_stats WHERE civilization_id=?"
		        );
		        del.setInt(1, civilizationId);
		        del.executeUpdate();

		        String sql = "INSERT INTO defense_units_stats " +
		                     "(civilization_id, type, armor, base_damage, experience, sanctified) " +
		                     "VALUES (?,?,?,?,?,?)";

		        String[] types = {"ArrowTower", "Catapult", "RocketLauncherTower"};

		        PreparedStatement ps = conn.prepareStatement(sql);

		        for (int i = 4; i <= 6; i++) {
		            for (MilitaryUnit unit : civ.getArmy()[i]) {
		                DefenseUnit du = (DefenseUnit) unit;
		                ps.setInt(1,    civilizationId);
		                ps.setString(2, types[i - 4]);
		                ps.setInt(3,    du.getActualArmor());
		                ps.setInt(4,    du.getBaseDamage());
		                ps.setInt(5,    du.getExperience());
		                ps.setInt(6,    du.isSanctified() ? 1 : 0);
		                ps.executeUpdate();
		            }
		        }
		        System.out.println("Unidades defensivas guardadas");
	    }
	 public void saveSpecialUnits(Civilization civ, int civilizationId) throws SQLException {
		 PreparedStatement del = conn.prepareStatement(
		            "DELETE FROM special_units_stats WHERE civilization_id=?"
		        );
		        del.setInt(1, civilizationId);
		        del.executeUpdate();

		        String sql = "INSERT INTO special_units_stats " +
		                     "(civilization_id, type, armor, base_damage, experience) " +
		                     "VALUES (?,?,?,?,?)";

		        String[] types = {"Magician", "Priest"};

		        PreparedStatement ps = conn.prepareStatement(sql);

		        for (int i = 7; i <= 8; i++) {
		            for (MilitaryUnit unit : civ.getArmy()[i]) {
		                SpecialUnit su = (SpecialUnit) unit;
		                ps.setInt(1,    civilizationId);
		                ps.setString(2, types[i - 7]);
		                ps.setInt(3,    su.getActualArmor());
		                ps.setInt(4,    su.getBaseDamage());
		                ps.setInt(5,    su.getExperience());
		                ps.executeUpdate();
		            }
		        }
		        System.out.println("Unidades especiales guardadas");
		    }
}
