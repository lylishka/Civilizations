package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private JLabel contador, cantidadMadera, cantidadHierro, cantidadComida, cantidadMana;
	private Battle batallaActual;
	private JButton	botonBattle, botonSave, botonSaveMenu, botonSaveExit;
	private boolean siguienteBatalla = false;
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
		
		iniciarSuperior();
		iniciarInferior();
	}
	
	public boolean isSiguienteBatalla() {
		return siguienteBatalla;
	}

	public void setSiguienteBatalla(boolean siguienteBatalla) {
		this.siguienteBatalla = siguienteBatalla;
	}

	public JButton getBotonBattle() {
		return botonBattle;
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

	public JLabel getCantidadMadera() {
		return cantidadMadera;
	}

	public void setCantidadMadera(String cantidadMadera) {
		this.cantidadMadera.setText(cantidadMadera);
	}

	public JLabel getCantidadHierro() {
		return cantidadHierro;
	}

	public void setCantidadHierro(String cantidadHierro) {
		this.cantidadHierro.setText(cantidadHierro);
	}

	public JLabel getCantidadComida() {
		return cantidadComida;
	}

	public void setCantidadComida(String cantidadComida) {
		this.cantidadComida.setText(cantidadComida);
	}

	public JLabel getCantidadMana() {
		return cantidadMana;
	}

	public void setCantidadMana(String cantidadMana) {
		this.cantidadMana.setText(cantidadMana);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
	}
	
	public void iniciarSuperior() {
		JPanel superior = new JPanel(new BorderLayout());
		superior.setBackground(Color.LIGHT_GRAY);
		
		JPanel izquierdo = new JPanel();
		izquierdo.setOpaque(false);
		
		// Materiales
		agregarRecurso(izquierdo, "madera", 0, false, true, false);
		agregarRecurso(izquierdo, "hierro", 1, false, true, false);
		agregarRecurso(izquierdo, "comida", 0, false, false, true);
		agregarRecurso(izquierdo, "mana", 1, false, false, true);
		
		// Edificios
		agregarRecurso(izquierdo, "granja", 0, false, false, false);
		agregarRecurso(izquierdo, "carpinteria", 1, false, false, false);
		agregarRecurso(izquierdo, "herreria", 2, false, false, false);
		agregarRecurso(izquierdo, "torremagica", 3, false, false, false);
		agregarRecurso(izquierdo, "iglesia", 4, true, false, false);
		
		// Unidades
		agregarRecurso(izquierdo, "espada",  0, true, false, false);
		agregarRecurso(izquierdo, "lanza", 1, true, false, false);
		agregarRecurso(izquierdo, "ballesta", 2, true, false, false);
		agregarRecurso(izquierdo, "canon", 3, true, false, false);
		agregarRecurso(izquierdo, "mago", 4, true, false, false);
		agregarRecurso(izquierdo, "sacerdote", 5, true, false, false);
		agregarRecurso(izquierdo, "torreflechas", 6, true, false, false);
		agregarRecurso(izquierdo, "catapulta", 7, true, false, false);
		agregarRecurso(izquierdo, "torrecohetes", 8, true, false, false);
		
		
		JPanel derecho = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		derecho.setOpaque(false);
		
		JLabel proxima = new JLabel("Próxima Batalla: ");
		proxima.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		contador = new JLabel("3:00");
		contador.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		derecho.add(proxima);
		derecho.add(contador);
		
		superior.add(izquierdo, BorderLayout.WEST);
		superior.add(derecho, BorderLayout.EAST);
		
		add(superior, BorderLayout.NORTH);
	}
	
	public void iniciarInferior() {
		JPanel inferior = new JPanel(new BorderLayout());
		inferior.setOpaque(false);
		
		JPanel botonesGuardar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		botonesGuardar.setOpaque(false);
		
		botonBattle = new JButton("Siguiente Batalla");
		botonSave = new JButton("Guardar");
		botonSaveMenu = new JButton("Guardar e Ir al Menú");
		botonSaveExit = new JButton("Guardar y Salir");
		
		botonBattle.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				siguienteBatalla = true;
			}
		});
		
		botonesGuardar.add(botonBattle);
		botonesGuardar.add(botonSave);
		botonesGuardar.add(botonSaveMenu);
		botonesGuardar.add(botonSaveExit);
		
		inferior.add(botonesGuardar, BorderLayout.NORTH);
		
		pestañas(inferior);
		
		add(inferior, BorderLayout.SOUTH);
	}
	
	public void actualizarContador(String tiempo) {
		contador.setText(tiempo);
	}
	
	public void agregarRecurso(JPanel recursos, String nombreImg, int indice, boolean esUnidad, boolean esResiduo, boolean esElemento) {
		ImageIcon icono = new ImageIcon("./src/gui/" + nombreImg + ".png");
		ImageIcon img = new ImageIcon(icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
		JLabel imagen = new JLabel(img);
		
		int valor = obtenerValor(indice, esUnidad, esResiduo, esElemento);
		JLabel cantidad = new JLabel(String.valueOf(valor));
		cantidad.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		if (nombreImg.equals("madera")) {
			cantidadMadera = cantidad;
		} else if (nombreImg.equals("hierro")) {
			cantidadHierro = cantidad;
		} else if (nombreImg.equals("comida")) {
			cantidadComida = cantidad;
		} else if (nombreImg.equals("mana")) {
			cantidadMana = cantidad;
		}
		
		JPanel recurso = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
		recurso.setOpaque(false);
		
		recurso.add(imagen);
		recurso.add(cantidad);
		
		recursos.add(recurso);
	}
	
	public int obtenerValor(int indice, boolean esUnidad, boolean esResiduo, boolean esElemento) {
		int valor = 0;
		
		if (esResiduo) {
			valor = batallaActual.getWasteWoodIron()[indice];
		} else if (esElemento) {
			valor = batallaActual.getWasteFoodMana()[indice];
		} else if (esUnidad) {
			valor = batallaActual.getActualNumberUnitsCivilization()[indice];
		} else {
			valor = batallaActual.getActualNumberBuldingCivilization()[indice];
		}
		
		return valor;
	}
	
	public void pestañas(JPanel pestañas) {
		tabs = new JTabbedPane();
		tabs.setBackground(Color.LIGHT_GRAY);
		
		JPanel tab1 = new JPanel();
		tab1.setBackground(Color.LIGHT_GRAY);
		agregarBotonesTropasO(tab1);
		
		JPanel tab2 = new JPanel();
		tab2.setBackground(Color.LIGHT_GRAY);
		agregarBotonesTropasE(tab2);
		
		JPanel tab3 = new JPanel();
		tab3.setBackground(Color.LIGHT_GRAY);
		agregarBotonesDefensas(tab3);
		
		JPanel tab4 = new JPanel();
		tab4.setBackground(Color.LIGHT_GRAY);
		agregarBotonesEdificios(tab4);
		
		tabs.addTab("TROPAS OFENSIVAS", tab1);
		tabs.addTab("TROPAS ESPECIALES", tab2);
		tabs.addTab("ESTRUCTURAS DEFENSIVAS", tab3);
		tabs.addTab("EDIFICIOS", tab4);
		
		pestañas.add(tabs, BorderLayout.CENTER);
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
