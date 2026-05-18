package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPrincipal extends JPanel {
	private JButton botonNueva, botonContinuar, botonExit;
	private JPanel menuBotones;
	private BufferedImage fondo;
	
	public MenuPrincipal(BufferedImage imagen) {
		this.fondo = imagen;
		
		setLayout(new BorderLayout());
		
		menuBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 220)) {
			protected void paintComponent(Graphics g) {
				
			}
		};
		
		botonNueva = new JButton("NUEVA PARTIDA");
		botonNueva.setPreferredSize(new Dimension(130, 45));
		
		botonContinuar = new JButton("CONTINUAR");
		botonContinuar.setPreferredSize(new Dimension(130, 45));
		
		botonExit = new JButton("SALIR");
		botonExit.setPreferredSize(new Dimension(130, 45));
		
		menuBotones.add(botonNueva);
		menuBotones.add(botonContinuar);
		menuBotones.add(botonExit);
		
		add(menuBotones, BorderLayout.SOUTH);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
	}

	public JButton getBotonNueva() {
		return botonNueva;
	}

	public JButton getBotonContinuar() {
		return botonContinuar;
	}

	public JButton getBotonExit() {
		return botonExit;
	}
}
