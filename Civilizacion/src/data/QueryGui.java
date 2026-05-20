package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import game.MilitaryUnit;
import logic.Battle;

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
				
				this.batallaActual = new Battle(new ArrayList<MilitaryUnit>(), new ArrayList<MilitaryUnit>());
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
		String queryCheck = "SELECT civilization_id, name FROM civilizacion_stats WHERE name = ?";
		String queryBattle = "SELECT wood_acquired, iron_acquired FROM battle_stats WHERE civilization_id = ?";
		
		try {
			connection.conectar();
			this.conn = connection.getConn();
			
			PreparedStatement psCheck = conn.prepareStatement(queryCheck);
			psCheck.setString(1, nombreCivilizacion);
			
			ResultSet rsName = psCheck.executeQuery();
			
			if (rsName.next()) {
				int idCivilization = rsName.getInt("civilization_id");
				
				PreparedStatement psBatalla = conn.prepareStatement(queryBattle);
				psBatalla.setInt(1, idCivilization);
				ResultSet rsBatalla = psBatalla.executeQuery();
				
				ArrayList<MilitaryUnit> tropasCivilizacion = new ArrayList<MilitaryUnit>();
				ArrayList<MilitaryUnit> tropasEnemigos = new ArrayList<MilitaryUnit>();
				
				
				if (rsBatalla.next()) {
					int madera = rsBatalla.getInt("wood_acquired");
					int hierro = rsBatalla.getInt("iron_acquired");
					
					this.batallaActual = new Battle(tropasCivilizacion, tropasEnemigos);
					
					int[] recursos = {madera, hierro};
					this.batallaActual.getWasteWoodIron()[0] = madera;
					this.batallaActual.getWasteWoodIron()[1] = hierro;
				}
				
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
			query = "SELECT initial FROM " + tabla + "WHERE num_battle = ? AND civilization_id = ? AND type = ? AND num_battle = ?";
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
}
