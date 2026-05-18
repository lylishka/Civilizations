package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JPanel {
	private JLabel mensaje;
	
	public Game(String nombreCivilizacion) {
		mensaje = new JLabel(nombreCivilizacion);
		add(mensaje);		
	}
}
