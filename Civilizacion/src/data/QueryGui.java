package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryGui {
	private Connection conn;
	private DBConection connection;
		
	public QueryGui() {
		connection = new DBConection();
	}
	
	public void crearCivilizacion(String nombreCivilizacion) {
		
		String queryCheck = "SELECT name FROM civilizacion_stats WHERE name = ?";
		String queryInsert = "INSERT INTO civilizacion_stats (name) VALUES (?)";
		
		try {
			connection.conectar();
			this.conn = connection.getConn();
			
			PreparedStatement psCheck = conn.prepareStatement(queryCheck);
			psCheck.setString(1, nombreCivilizacion);
			
			ResultSet rs = psCheck.executeQuery();
			
			if (rs.next()) {
				System.out.println("La civilización '" + nombreCivilizacion + "' ya existe en la base de datos");
			} else {
				PreparedStatement psInsert = conn.prepareStatement(queryInsert);
				psInsert.setString(1, nombreCivilizacion);
				psInsert.executeUpdate();
				
				System.out.println("Civilización '" + nombreCivilizacion + "' creada correctamente");
			}
			
		} catch (SQLException e) {
			System.out.println("Error al insertar la civilización: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Error al conectar a la base de datos: " + e.getMessage());
		}
	}
	
	public void encontrarCivilizacion(String nombreCivilizacion) {
		String queryCheck = "SELECT name FROM civilizacion_stats WHERE name = ?";
		
		try {
			connection.conectar();
			this.conn = connection.getConn();
			
			PreparedStatement psCheck = conn.prepareStatement(queryCheck);
			psCheck.setString(1, nombreCivilizacion);
			
			ResultSet rs = psCheck.executeQuery();
			
			if (rs.next()) {
				System.out.println("Civilización encontrada: '" + nombreCivilizacion);
			} else {
				System.out.println("La civilización '" + nombreCivilizacion + "' no encontrada");
			}
			
		} catch (SQLException e) {
			System.out.println("Error al insertar la civilización: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Error al conectar a la base de datos: " + e.getMessage());
		} 
	}
}
