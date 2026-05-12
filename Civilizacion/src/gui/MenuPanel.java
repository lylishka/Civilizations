package gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
	private JButton botonPlay, botonExit;
	private JPanel menuBotones;
	
	public MenuPanel() {
		setLayout(new BorderLayout());
		
		menuBotones = new JPanel();
		
		botonPlay = new JButton("JUGAR");
		botonExit = new JButton("SALIR");
		
		menuBotones.add(botonPlay);
		menuBotones.add(botonExit);
		
		add(menuBotones, BorderLayout.CENTER);
	}
	
}
