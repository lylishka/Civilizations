package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Game extends JPanel {
	private JTabbedPane tabs;
	
	public Game(String nombreCivilizacion) {
		setLayout(new BorderLayout());
		
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
	
	public void agregarBotonesTropas(JPanel tropas) {
		ImageIcon iconEspadachin = new ImageIcon("imagen");
		JButton botonEspadachin = new JButton("Espadachin", iconEspadachin);
		tropas.add(botonEspadachin);
		
		ImageIcon iconLancero = new ImageIcon("imagen");
		JButton botonLancero = new JButton("Lancero", iconLancero);
		tropas.add(botonLancero);
		
		ImageIcon iconBallesta = new ImageIcon("imagen");
		JButton botonBallesta = new JButton("Ballesta", iconBallesta);
		tropas.add(botonBallesta);
		
		ImageIcon iconCanon = new ImageIcon("imagen");
		JButton botonCanon = new JButton("Canon", iconCanon);
		tropas.add(botonCanon);
		
		ImageIcon iconMago = new ImageIcon("imagen");
		JButton botonMago = new JButton("Mago", iconMago);
		tropas.add(botonMago);
		
		ImageIcon iconSacerdote = new ImageIcon("imagen");
		JButton botonSacerdote = new JButton("Sacerdote", iconSacerdote);
		tropas.add(botonSacerdote);
	}
	
	public void agregarBotonesDefensas(JPanel defensas) {
		ImageIcon iconTorreLanza = new ImageIcon("imagen");
		JButton botonTorreLanza = new JButton("Torre Lanza", iconTorreLanza);
		defensas.add(botonTorreLanza);
		
		ImageIcon iconCatapulta = new ImageIcon("imagen");
		JButton botonCatapulta = new JButton("Catapulta", iconCatapulta);
		defensas.add(botonCatapulta);
		
		ImageIcon iconTorreCohete = new ImageIcon("imagen");
		JButton botonTorreCohete = new JButton("Torre Cohete", iconTorreCohete);
		defensas.add(botonTorreCohete);
	}
	
	public void agregarBotonesEdificios(JPanel edificios) {
		ImageIcon iconGranja = new ImageIcon("imagen");
		JButton botonGranja = new JButton("Granja", iconGranja);
		edificios.add(botonGranja);
		
		ImageIcon iconCarpinteria = new ImageIcon("imagen");
		JButton botonCarpinteria = new JButton("Carpinteria", iconCarpinteria);
		edificios.add(botonCarpinteria);
		
		ImageIcon iconHerreria = new ImageIcon("imagen");
		JButton botonHerreria = new JButton("Herreria", iconHerreria);
		edificios.add(botonHerreria);
		
		ImageIcon iconTorreMagica = new ImageIcon("imagen");
		JButton botonTorreMagica = new JButton("Torre Mágica", iconTorreMagica);
		edificios.add(botonTorreMagica);
		
		ImageIcon iconIglesia = new ImageIcon("imagen");
		JButton botonIglesia = new JButton("Iglesia", iconIglesia);
		edificios.add(botonIglesia);
	}
}
