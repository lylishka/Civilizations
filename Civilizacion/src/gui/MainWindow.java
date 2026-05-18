package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import data.QueryGui;

public class MainWindow extends JFrame {
	private BufferedImage imagenIcono, imagenFondo;
	private MenuPrincipal menu;
	private MenuLogin login;
	private Game game;
	private QueryGui queryGui;
	
	public MainWindow() {
		setTitle("Civilization");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		int anchoVentana = 900;
		int altoVentana = 600;
		
		setSize(anchoVentana, altoVentana);
		
		
		Toolkit pantalla = Toolkit.getDefaultToolkit();
		Dimension grandaria = pantalla.getScreenSize();
		
		int anchoPantalla = grandaria.width;
		int altoPantalla = grandaria.height;
	
		int x = (anchoPantalla - anchoVentana) / 2;
		int y = (altoPantalla - altoVentana) / 2;
		setLocation(x, y);
		
		
		try {
			imagenIcono = ImageIO.read(new File("./src/gui/civilizations_icon.png"));
			setIconImage(imagenIcono);
			
			imagenFondo = ImageIO.read(new File("./src/gui/civilizations_fondo.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		queryGui = new QueryGui();
		
		iniciarMenuPrincipal();
		iniciarMenuLogin();
		
		setVisible(true);
	}
	
	private void iniciarMenuPrincipal() {
		menu = new MenuPrincipal(imagenFondo);
		add(menu);
		
		menu.getBotonNueva().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				login.limpiarNombre();
				login.setModo("NUEVA");
				menu.setVisible(false);
				add(login);
				login.setVisible(true);
				repaint();
			}
		});
		
		menu.getBotonContinuar().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				login.limpiarNombre();
				login.setModo("CONTINUAR");
				menu.setVisible(false);
				add(login);
				login.setVisible(true);
				repaint();
			}
		});
		
		menu.getBotonExit().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	private void iniciarMenuLogin() {
		login = new MenuLogin(imagenFondo);
		
		login.getBotonVolver().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				login.setVisible(false);
				menu.setVisible(true);
			}
		});
		
		login.getBotonAceptar().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String name = login.getNombreCivilizacion();
				String modo = login.getModo();
				boolean jugar = false;
						
				if (modo.equals("NUEVA")) {
					jugar = queryGui.crearCivilizacion(name);
				} else if (modo.equals("CONTINUAR")) {
					jugar = queryGui.encontrarCivilizacion(name);
				}
				
				if (jugar) {
					iniciarGame(name);
				}
			}
		});
	}
	
	private void iniciarGame(String nombreCivilizacion) {
		setTitle("Civilizations: " + nombreCivilizacion);
		
		login.setVisible(false);
		
		game = new Game(nombreCivilizacion);
		add(game);
		game.setVisible(true);
		
		repaint();
	}
}
