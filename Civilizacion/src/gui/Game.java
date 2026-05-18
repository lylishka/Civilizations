package gui;

import java.awt.BorderLayout;
import java.awt.Color;

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
		tab1.add(new JLabel("TROPAS"));
		
		JPanel tab2 = new JPanel();
		tab2.setBackground(Color.LIGHT_GRAY);
		tab2.add(new JLabel("ESTRUCTURAS DEFENSIVAS"));
		
		JPanel tab3 = new JPanel();
		tab3.setBackground(Color.LIGHT_GRAY);
		tab3.add(new JLabel("EDIFICIOS"));
		
		tabs.addTab("TROPAS", tab1);
		tabs.addTab("ESTRUCTURAS DEFENSIVAS", tab2);
		tabs.addTab("EDIFICIOS", tab3);
		
		add(tabs, BorderLayout.SOUTH);

		
	}
}
