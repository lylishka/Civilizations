package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MenuLogin extends JPanel {
	private JTextArea nombre;
	private JButton botonAceptar, botonVolver;
	private JPanel menu;
	private BufferedImage fondo;
	private String modo;
	
	public MenuLogin(BufferedImage imagen) {
		this.fondo = imagen;
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 120, 360));
		
		menu = new JPanel(new GridLayout(2, 1, 0, 20)) {
			protected void paintComponent(Graphics g) {
				
			}
		};
		
		JPanel fila1 = new JPanel() {
			protected void paintComponent(Graphics g) {
			
			}
		};
		
		
		nombre = new JTextArea(1, 20);
		nombre.setText("Nombre de la civilizacion");
		nombre.setPreferredSize(new Dimension(350, 40));
		nombre.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		fila1.add(nombre);
		
		
		JPanel fila2 = new JPanel() {
			protected void paintComponent(Graphics g) {
							
			}
		};
		
		botonAceptar = new JButton("CONFIRMAR");
		botonAceptar.setPreferredSize(new Dimension(130, 60));
		botonVolver = new JButton("VOLVER");
		botonVolver.setPreferredSize(new Dimension(130, 60));
		
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

	public String getModo() {
		return modo;
	}

	public void setModo(String modo) {
		this.modo = modo;
	}

	public JButton getBotonVolver() {
		return botonVolver;
	}
	
	public String getNombreCivilizacion() {
		return nombre.getText();
	}

	public void setTexto(String texto) {
		nombre.setText(texto);
	}

	public JButton getBotonAceptar() {
		return botonAceptar;
	}
}

