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

import data.QueryBattle;
import data.QueryGui;
import logic.Battle;

public class MainWindow extends JFrame {
	private BufferedImage imagenIcono, imagenFondo;
	private MenuPrincipal menu;
	private MenuLogin login;
	private Game game;
	private QueryGui queryGui;
	private boolean jugando = false;
	
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
	
		setSize(anchoPantalla + 5, altoPantalla - 45);
		setLocation(-2, 0);
		
		
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
	
	public void iniciarMenuPrincipal() {
		menu = new MenuPrincipal(imagenFondo);
		add(menu);
		
		menu.getBotonNueva().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				login.setModo("NUEVA");
				login.setTexto("Nombre de la civilizacion");
				menu.setVisible(false);
				add(login);
				login.setVisible(true);
				repaint();
			}
		});
		
		menu.getBotonContinuar().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				login.setModo("CONTINUAR");
				login.setTexto("Nombre de la civilizacion");
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
	
	public void iniciarMenuLogin() {
		login = new MenuLogin(imagenFondo);
		
		login.getBotonVolver().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				login.setTexto("Nombre de la civilizacion");
				login.setVisible(false);
				menu.setVisible(true);
			}
		});
		
		login.getBotonAceptar().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String name = login.getNombreCivilizacion();
				String modo = login.getModo();
				boolean jugar = false;
				
				if (name.equals("Nombre de la civilizacion") || name.equals("") || name.equals("Se debe poner un nombre")) {
					login.setTexto("Se debe poner un nombre");
					return;
				}
				
				if (modo.equals("NUEVA")) {
					jugar = queryGui.crearCivilizacion(name);
				} else if (modo.equals("CONTINUAR")) {
					jugar = queryGui.encontrarCivilizacion(name);
				}
				
				if (jugar) {
					Battle battallaInciada = queryGui.getBatallaActual();
					iniciarGame(name, battallaInciada);
				}
			}
		});
	}
	
	public void iniciarGame(String nombreCivilizacion, Battle batalla) {
		setTitle("Civilizations: " + nombreCivilizacion);
		
		login.setVisible(false);
		
		game = new Game(nombreCivilizacion, batalla);
		add(game);
		game.setVisible(true);
		
		jugando = true;
		
		repaint();
		
		game.getBotonSaveMenu().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				guardarPartida(nombreCivilizacion, batalla);
				game.setVisible(false);
				login.setTexto("Nombre de la civilizacion");
				menu.setVisible(true);
			}
		});
		
		game.getBotonSaveExit().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				guardarPartida(nombreCivilizacion, batalla);
				System.exit(0);
			}
		});
	}
	
	public void guardarPartida(String nombreCivilizacion, Battle batalla) {
		int idCivilizacion = queryGui.getIdCivilizacion(nombreCivilizacion);
		
		if (idCivilizacion != -1) {
			QueryBattle queryBattle = new QueryBattle();
			queryBattle.saveBattle(batalla, idCivilizacion, "Ninguno");
			System.out.println("Batalla Guardada");
		} else {
			System.out.println("No se guardo la Batalla");
		}
	}

	public Game getGame() {
		return this.game;
	}

	public boolean isJugando() {
		return jugando;
	}
}
