package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
	private JButton botonPlay, botonExit;
	private JPanel menuBotones;
	private BufferedImage fondo;
	
	public MenuPanel(BufferedImage imagen) {
		this.fondo = imagen;
		
		setLayout(new BorderLayout());
		
		menuBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 220)) {
			protected void paintComponent(Graphics g) {
				
			}
		};
		
		botonPlay = new JButton("JUGAR");
		botonPlay.setPreferredSize(new Dimension(130, 45));
		
		botonExit = new JButton("SALIR");
		botonExit.setPreferredSize(new Dimension(130, 45));
		
		menuBotones.add(botonPlay);
		menuBotones.add(botonExit);
		
		add(menuBotones, BorderLayout.SOUTH);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
	}

	public JButton getBotonExit() {
		return botonExit;
	}
}
