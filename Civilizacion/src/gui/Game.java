package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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

import logic.Battle;

public class Game extends JPanel {
	private BufferedImage fondo;
	private JTabbedPane tabs;
	private JLabel contador;
	private Battle batallaActual;
	private JButton	botonSave, botonSaveMenu, botonSaveExit;
	private String nombreCivilizacion;
	
	public Game(String nombreCivilizacion, Battle batallaActual) {
		this.nombreCivilizacion = nombreCivilizacion;
		this.batallaActual = batallaActual;
		
		setLayout(new BorderLayout());
		
		try {
			fondo = ImageIO.read(new File("./src/gui/fondo-juego.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JPanel arribaCentro = new JPanel(new FlowLayout(1)) {
			protected void paintComponent(Graphics g) {
				
			}
		};
		
		contador = new JLabel("3:00");
		contador.setFont(new Font("Times New Roman", Font.BOLD, 50));
		
		arribaCentro.add(contador);
		add(arribaCentro, BorderLayout.NORTH);
		
		JPanel abajo = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				
			}
		};
		
		JPanel botonesGuardar = new JPanel(new FlowLayout(FlowLayout.RIGHT)) {
			protected void paintComponent(Graphics g) {
				
			}
		};
		
		botonSave = new JButton("Guardar");
		botonSaveMenu = new JButton("Guardar e Ir al Menú");
		botonSaveExit = new JButton("Guardar y Salir");
		
		botonesGuardar.add(botonSave);
		botonesGuardar.add(botonSaveMenu);
		botonesGuardar.add(botonSaveExit);
		
		abajo.add(botonesGuardar, BorderLayout.NORTH);
		
		tabs = new JTabbedPane();
		tabs.setBackground(Color.LIGHT_GRAY);
		
		JPanel tab1 = new JPanel();
		tab1.setBackground(Color.LIGHT_GRAY);
		tab1.setOpaque(false);
		agregarBotonesTropasO(tab1);
		
		JPanel tab2 = new JPanel();
		tab2.setBackground(Color.LIGHT_GRAY);
		tab2.setOpaque(false);
		agregarBotonesTropasE(tab2);
		
		JPanel tab3 = new JPanel();
		tab3.setBackground(Color.LIGHT_GRAY);
		tab3.setOpaque(false);
		agregarBotonesDefensas(tab3);
		
		JPanel tab4 = new JPanel();
		tab4.setBackground(Color.LIGHT_GRAY);
		tab4.setOpaque(false);
		agregarBotonesEdificios(tab4);
		
		tabs.addTab("TROPAS OFENSIVAS", tab1);
		tabs.addTab("TROPAS ESPECIALES", tab2);
		tabs.addTab("ESTRUCTURAS DEFENSIVAS", tab3);
		tabs.addTab("EDIFICIOS", tab4);
		
		abajo.add(tabs, BorderLayout.CENTER);
		add(abajo, BorderLayout.SOUTH);
	}
		
	public JButton getBotonSave() {
		return botonSave;
	}

	public JButton getBotonSaveMenu() {
		return botonSaveMenu;
	}

	public JButton getBotonSaveExit() {
		return botonSaveExit;
	}

	public Battle getBatallaActual() {
		return batallaActual;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
	}
	
	public void actualizarContador(String tiempo) {
		contador.setText(tiempo);
	}
	
	public void agregarBotonesTropasO(JPanel tropas) {
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
	}
	
	public void agregarBotonesTropasE(JPanel tropas) {		
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
