package logic;

import java.util.Timer;
import java.util.TimerTask;

import data.DBConection;
import gui.MainWindow;


public class Main {	
	

	public static void main(String[] args) {
		DBConection data = new DBConection();
		
		try {
			data.conectar();
			MainWindow window = new MainWindow();
			
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				
				private int segundosRestantes = 180;	

				public void run() {
					if (window.isJugando()) {
						--segundosRestantes;
						
						if (segundosRestantes <= 0) {
							segundosRestantes = 180;
						}
						
						int minutos = segundosRestantes / 60;
						int segundos = segundosRestantes % 60;
						String tiempo = String.format("%02d:%02d", minutos, segundos);
						
						window.getGame().actualizarContador(tiempo);
						window.getGame().repaint();						
					}
					
				}
			};
			
			timer.schedule(task, 0, 1000);
			
			
			
		} catch (Exception e) {
			System.out.println("No se puedo conectar a la DB");
		}
	}
}
