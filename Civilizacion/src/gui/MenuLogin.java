package gui;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuLogin extends JPanel {
	private JTextField nombre;
	private JLabel comentario;
	private JButton botonAceptar, botonVolver;
	private JPanel menu;
	private String modo;
	private BufferedImage fondo;
	
	public MenuLogin(BufferedImage imagen) {
		this.fondo = imagen;
		setLayout(new FlowLayout(FlowLayout.CENTER, 20, 220));
		
		menu = new JPanel(new GridLayout(2, 1, 0, 20)) {
			protected void paintComponent(Graphics g) {
				
			}
		};
		
		JPanel fila1 = new JPanel() {
			protected void paintComponent(Graphics g) {
							
						}
		};
		
		comentario = new JLabel("Nombre de la civilizacion:");
		nombre = new JTextField(20);
		fila1.add(comentario);
		fila1.add(nombre);
		
		
		JPanel fila2 = new JPanel() {
			protected void paintComponent(Graphics g) {
							
						}
		};
		
		botonAceptar = new JButton("CONFIRMAR");
		botonVolver = new JButton("VOLVER");
		
		fila2.add(botonAceptar);
		fila2.add(botonVolver);
		
		menu.add(fila1);
		menu.add(fila2);

		add(menu);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
	}
}

