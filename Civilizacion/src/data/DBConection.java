package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConection {
	private String urlDatos = "jdbc:mysql://localhost:3307/civilizations?serverTimezone=UTC";
	private String user = "super"; 
	private String password = "1234";
	private String driver = "com.mysql.cj.jdbc.Driver";
	private Connection conn;
	
	public DBConection() {
		
	}
	
	public void conectar() throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		System.out.println("Driver Cargado");
		
		Connection conn = DriverManager.getConnection(urlDatos, user, password);
		System.out.println("Conexión creada correctamente");
	}

	public Connection getConn() {
		return conn;
	}
}
