package logic;

import data.DBConection;
import gui.MainWindow;


public class Main {

	public static void main(String[] args) {
		DBConection data = new DBConection();
		
		try {
			data.conectar();
			new MainWindow();
			
			
		} catch (Exception e) {
			System.out.println("No se puedo conectar a la DB");
		}
	}
}
