package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import game.MilitaryUnit;
import logic.Battle;
import logic.BattleMechanics;

public class QueryGui {
	private Connection conn;
	private DBConection connection;
	private Battle batallaActual;
		
	public QueryGui() {
		connection = new DBConection();
	}
	
	public Battle getBatallaActual() {
		return batallaActual;
	}

	public boolean crearCivilizacion(String nombreCivilizacion) {
		
		String queryCheck = "SELECT name FROM civilizacion_stats WHERE name = ?";
		String queryInsert = "INSERT INTO civilizacion_stats (name) VALUES (?)";
		String queryGetId = "SELECT civilization_id FROM civilizacion_stats WHERE name = ?";
		
		try {
			connection.conectar();
			this.conn = connection.getConn();
			
			PreparedStatement psCheck = conn.prepareStatement(queryCheck);
			psCheck.setString(1, nombreCivilizacion);
			
			ResultSet rs = psCheck.executeQuery();
			
			if (rs.next()) {
				System.out.println("La civilización '" + nombreCivilizacion + "' ya existe en la base de datos");
				return false;
			} else {
				PreparedStatement psInsert = conn.prepareStatement(queryInsert);
				psInsert.setString(1, nombreCivilizacion);
				psInsert.executeUpdate();
				
				System.out.println("Civilización '" + nombreCivilizacion + "' creada correctamente");
				
				PreparedStatement psGetId = conn.prepareStatement(queryGetId);
				psGetId.setString(1, nombreCivilizacion);
				ResultSet rsId = psGetId.executeQuery();
				
				int idCivilizacion = -1;
				
				if (rsId.next()) {
					idCivilizacion = rsId.getInt("civilization_id");
				}
				
				this.batallaActual = new BattleMechanics(new ArrayList<MilitaryUnit>(), new ArrayList<MilitaryUnit>());
				QueryBattle queryBattle = new QueryBattle();
				queryBattle.saveBattleStats(batallaActual, idCivilizacion);
				
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("Error al insertar la civilización: " + e.getMessage());
			return false; 
		} catch (ClassNotFoundException e) {
			System.out.println("Error al conectar a la base de datos: " + e.getMessage());
			return false; 
		}
	}
	
	public boolean encontrarCivilizacion(String nombreCivilizacion) {
		String queryCheck = "SELECT * FROM civilizacion_stats WHERE name = ?";
		
		try {
			connection.conectar();
			this.conn = connection.getConn();
			
			PreparedStatement psCheck = conn.prepareStatement(queryCheck);
			psCheck.setString(1, nombreCivilizacion);
			ResultSet rsCivilizacion = psCheck.executeQuery();
			
			if (rsCivilizacion.next()) {
				int idCivilization = rsCivilizacion.getInt("civilization_id");
				
				batallaActual = new BattleMechanics(new ArrayList<MilitaryUnit>(), new ArrayList<MilitaryUnit>());
				
				batallaActual.getWasteWoodIron()[0] = rsCivilizacion.getInt("wood_amount");
				batallaActual.getWasteWoodIron()[1] = rsCivilizacion.getInt("iron_amount");
				batallaActual.getWasteFoodMana()[0] = rsCivilizacion.getInt("food_amount");
				batallaActual.getWasteFoodMana()[1] = rsCivilizacion.getInt("mana_amount");
				
				int[] edificios = new int[5];
				edificios[0] = rsCivilizacion.getInt("farm_counter");
				edificios[1] = rsCivilizacion.getInt("carpentry_counter");
				edificios[2] = rsCivilizacion.getInt("smithy_counter");
				edificios[3] = rsCivilizacion.getInt("magicTower_counter");
				edificios[4] = rsCivilizacion.getInt("church_counter");
				batallaActual.setActualNumberBuldingCivilization(edificios);
				
				int[]unidades = new int[9];
				batallaActual.setActualNumberUnitsCivilization(unidades);
				
				int[][] pos = new int[14][2];
				posiciones(idCivilization, pos, "building", "type");
				posiciones(idCivilization, pos, "civilization_attack_stats", "type");
				posiciones(idCivilization, pos, "civilization_defense_stats", "type");
				posiciones(idCivilization, pos, "civilization_special_stats", "type");
				this.batallaActual.setPositions(pos);				
				
				return true;
			} else {
				System.out.println("La civilización '" + nombreCivilizacion + "' no encontrada");
				return false;
			}
			
		} catch (SQLException e) {
			System.out.println("Error al encontrar la civilización: " + e.getMessage());
			return false; 
		} catch (ClassNotFoundException e) {
			System.out.println("Error al conectar a la base de datos: " + e.getMessage());
			return false; 
		}
	}
	
	public int getIdCivilizacion(String nombreCivlizacion) {
		String queryId = "SELECT civilization_id FROM civilizacion_stats WHERE name = ?";
		int id = -1;
		
		try {
			connection.conectar();
			this.conn = connection.getConn();
			
			PreparedStatement ps = conn.prepareStatement(queryId);
			ps.setString(1, nombreCivlizacion);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				id = rs.getInt("civilization_id");
			}
		} catch (Exception e) {
			System.out.println("Error al recuperar la ID civilización: " + e.getMessage());
		}
		
		return id;
	}
	
	public int getIdBattle(int idCivilizacion) {
		String queryId = "SELECT num_battle FROM battle_stats WHERE civilization_id = ?";
		int id = -1;
		
		try {
			connection.conectar();
			this.conn = connection.getConn();
			
			PreparedStatement ps = conn.prepareStatement(queryId);
			ps.setInt(1, idCivilizacion);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				id = rs.getInt("num_battle");
			}
		} catch (Exception e) {
			System.out.println("Error al recuperar la ID de la batalla: " + e.getMessage());
		}
		
		return id;
	}
	
	public int getCantidadElemento(int idCivilizacion, int idBattle, String columna, String tabla, boolean esTipo, int numBattle) {	
		int cantidad = 0;
		
		String query;
		if (esTipo) {
			query = "SELECT initial FROM " + tabla + " WHERE num_battle = ? AND civilization_id = ? AND type = ? AND num_battle = ?";
		} else {
			query = "SELECT " + columna + " FROM " + tabla + " WHERE civilization_id = ?";
		}
		
		try {
			connection.conectar();
			this.conn = connection.getConn();
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			if (esTipo) {
				ps.setInt(1, idBattle);
				ps.setInt(2, idCivilizacion);
				ps.setString(3, columna);
				ps.setInt(4, numBattle);
			} else {
				ps.setInt(1, idCivilizacion);
			}
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				if (esTipo) {
					cantidad = rs.getInt(2);
				} else {
					cantidad = rs.getInt(columna);
				}
			}
		} catch (Exception e) {
			System.out.println("Error al obtener la cantidad: " + e.getMessage());
		}
		
		return cantidad;
	}
	
	public void posiciones(int idCivilizacion, int[][] pos, String tabla, String columna) throws SQLException {
		String query = "SELECT " + columna + ", posX, posY FROM " + tabla + " WHERE civilization_id = ?";
		
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, idCivilizacion);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			int indice = getIndice(rs.getString(columna));
			if (indice != -1) {
				pos[indice][0] = rs.getInt("posX");
				pos[indice][1] = rs.getInt("posY");
			}
		}
	}
	
	public int getIndice(String tipo) {
		int index;
		
		switch (tipo) {
		case "Swordsman": index = 0;
			break;
		case "Spearman": index = 1;
			break;
		case "Crossbow": index = 2;
			break;
		case "Cannon": index = 3;
			break;
		case "ArrowTower": index = 4;
			break;
		case "Catapult": index = 5;
			break;
		case "RocketLauncherTower": index = 6;
			break;
		case "Magician": index = 7;
			break;
		case "Priest": index = 8;
			break;
		case "Farm": index = 9;
			break;
		case "Carpentry": index = 10;
			break;
		case "Smithy": index = 11;
			break;
		case "MagicTower": index = 12;
			break;
		case "Church": index = 13;
			break;
		default: index = -1;
		}
		
		return index;
	}
}
