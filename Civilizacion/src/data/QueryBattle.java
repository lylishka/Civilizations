package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.Battle;

public class QueryBattle {
	private Connection conn;
	private DBConection connection;

    public QueryBattle() {
    	try {
        	connection = new DBConection();
			connection.conectar();
			this.conn = connection.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
    }
    
    public void saveBattle(Battle battle, int civilizationId, String winner) {
        try {
            int numBattle = saveBattleStats(battle, civilizationId);
            saveBattleLog(battle, civilizationId, numBattle, winner);
            saveCivilizationAttackStats(battle, civilizationId, numBattle);
            saveCivilizationDefenseStats(battle, civilizationId, numBattle);
            saveCivilizationSpecialStats(battle, civilizationId, numBattle);
            saveEnemyAttackStats(battle, civilizationId, numBattle);
            saveBuldings(battle, civilizationId);
            
        } catch (SQLException e) {
            System.out.println("Error al guardar batalla: " + e.getMessage());
        }
    }
    
    public int saveBattleStats(Battle battle, int civilizationId) throws SQLException {
        String sql = "INSERT INTO battle_stats " +
                     "(civilization_id, wood_acquired, iron_acquired) " +
                     "VALUES (?,?,?)";

        // RETURN_GENERATED_KEYS para obtener el num_battle que genera AUTO_INCREMENT
        // Lo necesitamos para insertarlo en battle_log y las stats
        PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        ps.setInt(1,    civilizationId);
        ps.setInt(2,    battle.getWasteWoodIron()[0]); // madera generada en batalla
        ps.setInt(3,    battle.getWasteWoodIron()[1]); // hierro generado en batalla

        ps.executeUpdate();

        // Obtenemos el num_battle que MySQL generó automáticamente
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            int numBattle = rs.getInt(1);
            System.out.println("Battle stats guardada, num_battle = " + numBattle);
            return numBattle;
        }

        return -1; // si algo falla devolvemos -1
    }
    
    public void saveBattleLog(Battle battle, int civilizationId, int numBattle, String winner) throws SQLException {
        String sql = "INSERT INTO battle_log " +
                     "(civilization_id, num_battle, winner, log_entry) " +
                     "VALUES (?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql);

        // Partimos el String del desarrollo por líneas
        // Cada línea es una fila en battle_log
        String[] lines = battle.getBattleDevelopment().split("\n");

        for (String line : lines) {
            ps.setInt(1,    civilizationId);
            ps.setInt(2,    numBattle);
            ps.setString(3, winner);           // "Civilization" o "Enemy"
            ps.setString(4, line);
            ps.executeUpdate();
        }
        System.out.println("Battle log guardado");
    }
    
    public void saveCivilizationAttackStats(Battle battle, int civilizationId, int numBattle) throws SQLException {
        String sql = "INSERT INTO civilization_attack_stats " +
                     "(type, civilization_id, num_battle, initial, drops) " +
                     "VALUES (?,?,?,?,?)";

        // Mismo orden que el ENUM de la BD
        String[] types = {"Swordsman", "Spearman", "Crossbow", "Cannon"};

        PreparedStatement ps = conn.prepareStatement(sql);

        // initialArmies[0][0..3] = unidades iniciales de nuestra civilización
        // civilizationDrops[0..3] = unidades perdidas de nuestra civilización
        for (int i = 0; i <= 3; i++) {
            ps.setString(1, types[i]);
            ps.setInt(2,   	civilizationId);
            ps.setInt(3,    numBattle);
            ps.setInt(4,    battle.getInitialArmies()[0][i]);     // cuántas había al inicio
            ps.setInt(5,    0);   // cuántas murieron
            ps.executeUpdate();
        }
        System.out.println("Civilization attack stats guardadas");
    }
    
    public void saveCivilizationDefenseStats(Battle battle, int civilizationId, int numBattle) throws SQLException {
        String sql = "INSERT INTO civilization_defense_stats " +
                     "(type, civilization_id, num_battle, initial, drops) " +
                     "VALUES (?,?,?,?,?)";

        // Mismo orden que el ENUM de la BD
        String[] types = {"ArrowTower", "Catapult", "RocketLauncherTower"};

        PreparedStatement ps = conn.prepareStatement(sql);

        // initialArmies[0][4..6] = unidades defensivas iniciales de nuestra civilización
        // civilizationDrops[4..6] = unidades defensivas perdidas
        for (int i = 4; i <= 6; i++) {
            ps.setString(1, types[i - 4]); // i-4 porque types[0]=ArrowTower
            ps.setInt(2,    civilizationId);
            ps.setInt(3,    numBattle);
            ps.setInt(4,    battle.getInitialArmies()[0][i]);
            ps.setInt(5,    0);
            ps.executeUpdate();
        }
        System.out.println("Civilization defense stats guardadas");
    }
    
    public void saveCivilizationSpecialStats(Battle battle, int civilizationId, int numBattle) throws SQLException {
        String sql = "INSERT INTO civilization_special_stats " +
                     "(type, civilization_id, num_battle, initial, drops) " +
                     "VALUES (?,?,?,?,?)";

        // Mismo orden que el ENUM de la BD
        String[] types = {"Magician", "Priest"};

        PreparedStatement ps = conn.prepareStatement(sql);

        // initialArmies[0][7..8] = unidades especiales iniciales de nuestra civilización
        // civilizationDrops[7..8] = unidades especiales perdidas
        for (int i = 7; i <= 8; i++) {
            ps.setString(1, types[i - 7]); // i-7 porque types[0]=Magician
            ps.setInt(2,    civilizationId);
            ps.setInt(3,    numBattle);
            ps.setInt(4,    battle.getInitialArmies()[0][i]);
            ps.setInt(5,    0);
            ps.executeUpdate();
        }
        System.out.println("Civilization special stats guardadas");
    }
    
    public void saveEnemyAttackStats(Battle battle, int civilizationId, int numBattle) throws SQLException {
        String sql = "INSERT INTO enemy_attack_stats " +
                     "(type, civilization_id, num_battle, initial, drops) " +
                     "VALUES (?,?,?,?,?)";

        // El enemigo solo tiene unidades de ataque: Swordsman, Spearman, Crossbow, Cannon
        String[] types = {"Swordsman", "Spearman", "Crossbow", "Cannon"};

        PreparedStatement ps = conn.prepareStatement(sql);

        // initialArmies[1][0..3] = unidades iniciales del enemigo
        // enemyDrops[0..3] = unidades perdidas del enemigo
        for (int i = 0; i <= 3; i++) {
            ps.setString(1, types[i]);
            ps.setInt(2,    civilizationId);
            ps.setInt(3,    numBattle);
            ps.setInt(4,    battle.getInitialArmies()[1][i]);  // cuántas había al inicio
            ps.setInt(5,    battle.getEnemyDrops());        // cuántas murieron
            ps.executeUpdate();
        }
        System.out.println("Enemy stats guardadas");
    }
    
    public void saveBuldings(Battle battle, int civilizationId) throws SQLException {
    	String sqlDelete = "DELETE FROM building WHERE civilization_id = ?";
    	PreparedStatement psDelete = conn.prepareStatement(sqlDelete);
    	psDelete.setInt(1, civilizationId);
    	psDelete.executeUpdate();
    	
    	String sqlInsert = "INSERT INTO building (civilization_id, type, posX, posY) VALUES (?, ?, ?, ?)";
    	PreparedStatement psInsert = conn.prepareStatement(sqlInsert);
    	
    	String[] types = {"Farm", "Carpentry", "Smithy", "MagicTower", "Church"};
		QueryGui queryGui = new QueryGui();
    	
    	for (int i = 0; i < 5; i++) {
    		int cantidad = battle.getActualNumberBuldingCivilization()[i];
    		int index = queryGui.getIndice(types[i]);
    		
    		if (cantidad > 0 && index != -1) {
    			psInsert.setInt(1, civilizationId);
    			psInsert.setString(2, types[i]);
    			psInsert.setInt(3, battle.getPositions()[index][0]);
    			psInsert.setInt(4, battle.getPositions()[index][1]);
    			psInsert.executeUpdate();
    		}
        }
    	
        System.out.println("Edificios guardadas");
    }
    
    public void updateCivilizationsStats(int idCivilizacion, Battle battle) throws SQLException {
    	String sql = "UPDATE civilizacion_stats SET "
    			+ "wood_amount = ?, iron_amount = ?, food_amount = ?, mana_amount = ?, "
    			+ "magicTower_counter = ?, church_counter = ?, smithy_counter = ?, farm_counter = ?, carpentry_counter = ? "
    			+ "WHERE civilization_id = ?";
    	
    	PreparedStatement ps = conn.prepareStatement(sql);
    	
    	ps.setInt(1, battle.getWasteWoodIron()[0]);
    	ps.setInt(2, battle.getWasteWoodIron()[1]);
    	ps.setInt(3, battle.getWasteFoodMana()[0]);
    	ps.setInt(4, battle.getWasteFoodMana()[1]);
    	ps.setInt(5, battle.getActualNumberBuldingCivilization()[3]);
    	ps.setInt(6, battle.getActualNumberBuldingCivilization()[4]);
    	ps.setInt(7, battle.getActualNumberBuldingCivilization()[2]);
    	ps.setInt(8, battle.getActualNumberBuldingCivilization()[0]);
    	ps.setInt(9, battle.getActualNumberBuldingCivilization()[1]);
    	ps.setInt(10, idCivilizacion);
    	
    	ps.executeUpdate();
    	
    }
}
