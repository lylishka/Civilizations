package logic;

import java.util.Timer;
import java.util.TimerTask;

import data.DBConection;
import gui.Game;
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
				private int segundosRecursos = 0;

				public void run() {
					if (window.isJugando()) {
						if (window.getGame().isSiguienteBatalla()) {
							segundosRestantes = 0;
							window.getGame().setSiguienteBatalla(false);
						} 
						
						if (segundosRestantes <= 0) {
							window.getGame().ejecutarBatalla(1);
							segundosRestantes = 180;
						} else {
							--segundosRestantes;
						}
						
						
						int minutos = segundosRestantes / 60;
						int segundos = segundosRestantes % 60;
						String tiempo = String.format("%02d:%02d", minutos, segundos);
						
						window.getGame().actualizarContador(tiempo);
						
						++segundosRecursos;
						if (segundosRecursos >= 30) {
							aumentarRecursos(window);
							segundosRecursos = 0;
						}
						
						window.getGame().repaint();						
					}
					
				}
			};
			
			timer.schedule(task, 0, 1000);
			
			
			
		} catch (Exception e) {
			System.out.println("No se puedo conectar a la DB");
		}
	}
	
	public static void aumentarRecursos(MainWindow window) {
		Battle batalla = window.getGame().getBatallaActual();
		Game game = window.getGame();
		
		int[] residuos = batalla.getWasteWoodIron();
		int[] elementos = batalla.getWasteFoodMana();
		int[] edificios = batalla.getActualNumberBuldingCivilization();
		
		residuos[0] += batalla.CIVILIZATION_WOOD_GENERATED;
		residuos[1] += batalla.CIVILIZATION_IRON_GENERATED;
		elementos[0] += batalla.CIVILIZATION_FOOD_GENERATED;
		
		// Granjas
		if (edificios[0] > 0) {
			elementos[0] += (edificios[0] * batalla.CIVILIZATION_FOOD_GENERATED_PER_FARM);
		}
		
		// Carpinteria
		if (edificios[1] > 0) {
			residuos[0] += (edificios[1] * batalla.CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY);
		}
				
		// Herreria
		if (edificios[2] > 0) {
			residuos[1] += (edificios[2] * batalla.CIVILIZATION_IRON_GENERATED_PER_SMITHY);
		}
		
		// Torre Magica
		if (edificios[3] > 0 ) {
			elementos[1] += (edificios[3] * batalla.CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER);
		}
		
		window.getGame().getBatallaActual().setWasteWoodIron(residuos);
		window.getGame().getBatallaActual().setWasteFoodMana(elementos);
		
		game.setCantidadComida(String.valueOf(residuos[0]));
		game.setCantidadHierro(String.valueOf(residuos[1]));
		game.setCantidadMadera(String.valueOf(elementos[0]));
		game.setCantidadMana(String.valueOf(elementos[1]));
		game.repaint();
		
	}
}
