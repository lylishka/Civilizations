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
		tab1.add(new JLabel("PESTAÑA 1"));
		
		JPanel tab2 = new JPanel();
		tab2.setBackground(Color.LIGHT_GRAY);
		tab2.add(new JLabel("PESTAÑA 2"));
		
		JPanel tab3 = new JPanel();
		tab3.setBackground(Color.LIGHT_GRAY);
		tab3.add(new JLabel("PESTAÑA 3"));
		
		tabs.addTab("PESTAÑA 1", tab1);
		tabs.addTab("PESTAÑA 2", tab2);
		tabs.addTab("PESTAÑA 3", tab3);
		
		add(tabs, BorderLayout.SOUTH);

		
	}
}
