package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Game extends JPanel {
	private BufferedImage fondo;
	private JTabbedPane tabs;
	
	public Game(String nombreCivilizacion) {
		setLayout(new BorderLayout());
		
		try {
			fondo = ImageIO.read(new File("./src/gui/fondo-juego.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		tabs = new JTabbedPane();
		tabs.setBackground(Color.LIGHT_GRAY);
		
		JPanel tab1 = new JPanel();
		tab1.setBackground(Color.LIGHT_GRAY);
		agregarBotonesTropas(tab1);
		
		JPanel tab2 = new JPanel();
		tab2.setBackground(Color.LIGHT_GRAY);
		agregarBotonesDefensas(tab2);
		
		JPanel tab3 = new JPanel();
		tab3.setBackground(Color.LIGHT_GRAY);
		agregarBotonesEdificios(tab3);
		
		tabs.addTab("TROPAS", tab1);
		tabs.addTab("ESTRUCTURAS DEFENSIVAS", tab2);
		tabs.addTab("EDIFICIOS", tab3);
		
		add(tabs, BorderLayout.SOUTH);

		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
	}
	
	public void agregarBotonesTropas(JPanel tropas) {
		ImageIcon iconEspadachin = new ImageIcon("./src/gui/espada.png");
		JButton botonEspadachin = new JButton("Espadachin", iconEspadachin);
		tropas.add(botonEspadachin);
		
		ImageIcon iconLancero = new ImageIcon("./src/gui/lanza.png");
		JButton botonLancero = new JButton("Lancero", iconLancero);
		tropas.add(botonLancero);
		
		ImageIcon iconBallesta = new ImageIcon("./src/gui/ballesta.png");
		JButton botonBallesta = new JButton("Ballesta", iconBallesta);
		tropas.add(botonBallesta);
		
		ImageIcon iconCanon = new ImageIcon("./src/gui/canon.png");
		JButton botonCanon = new JButton("Canon", iconCanon);
		tropas.add(botonCanon);
		
		ImageIcon iconMago = new ImageIcon("./src/gui/mago.png");
		JButton botonMago = new JButton("Mago", iconMago);
		tropas.add(botonMago);
		
		ImageIcon iconSacerdote = new ImageIcon("./src/gui/sacerdote.png");
		JButton botonSacerdote = new JButton("Sacerdote", iconSacerdote);
		tropas.add(botonSacerdote);
	}
	
	public void agregarBotonesDefensas(JPanel defensas) {
		ImageIcon iconTorreFlechas = new ImageIcon("./src/gui/torreflechas.png");
		JButton botonTorreFlechas = new JButton("Torre de Flechas", iconTorreFlechas);
		defensas.add(botonTorreFlechas);
		
		ImageIcon iconCatapulta = new ImageIcon("./src/gui/catapulta.png");
		JButton botonCatapulta = new JButton("Catapulta", iconCatapulta);
		defensas.add(botonCatapulta);
		
		ImageIcon iconTorreLanzaCohetes = new ImageIcon("./src/gui/torrecohetes.png");
		JButton botonTorreLanzaCohetes = new JButton("Torre Lanza Cohetes", iconTorreLanzaCohetes);
		defensas.add(botonTorreLanzaCohetes);
	}
	
	public void agregarBotonesEdificios(JPanel edificios) {
		ImageIcon iconGranja = new ImageIcon("./src/gui/granja.png");
		JButton botonGranja = new JButton("Granja", iconGranja);
		edificios.add(botonGranja);
		
		ImageIcon iconCarpinteria = new ImageIcon("./src/gui/carpinteria.png");
		JButton botonCarpinteria = new JButton("Carpinteria", iconCarpinteria);
		edificios.add(botonCarpinteria);
		
		ImageIcon iconHerreria = new ImageIcon("./src/gui/herreria.png");
		JButton botonHerreria = new JButton("Herreria", iconHerreria);
		edificios.add(botonHerreria);
		
		ImageIcon iconTorreMagica = new ImageIcon("./src/gui/torremagica.png");
		JButton botonTorreMagica = new JButton("Torre Mágica", iconTorreMagica);
		edificios.add(botonTorreMagica);
		
		ImageIcon iconIglesia = new ImageIcon("./src/gui/iglesia.png");
		JButton botonIglesia = new JButton("Iglesia", iconIglesia);
		edificios.add(botonIglesia);
	}
}
