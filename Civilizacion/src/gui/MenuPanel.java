package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
	private JButton botonPlay, botonExit;
	private JPanel menuBotones;
	
	public MenuPanel() {				
		botonPlay = new JButton("JUGAR");
		botonExit = new JButton("SALIR");
		
		botonPlay.setBounds(300, 350, 200, 50);
		botonExit.setBounds(300, 420, 200, 50);
		
		menuBotones.add(botonPlay);
		menuBotones.add(botonExit);
		
		add(menuBotones, BorderLayout.CENTER);
	}
	
}
