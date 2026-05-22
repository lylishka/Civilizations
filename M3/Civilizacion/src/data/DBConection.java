package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConection {
	// Cambiar el Servidor, Puerto y Esquema de la BD
	private String urlDatos = "jdbc:mysql://localhost:3307/civilizations?serverTimezone=UTC";
		
	private String user = "super"; // Usuario de la BD
	private String password = "1234"; // Contraseña

	private String driver = "com.mysql.cj.jdbc.Driver";
	
	private Connection conn;
	
	public DBConection() {
		
	}
	
	public void conectar() throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		
		conn = DriverManager.getConnection(urlDatos, user, password);
	}

	public Connection getConn() {
		return conn;
	}
}
