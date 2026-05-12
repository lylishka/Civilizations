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

public class MainWindow extends JFrame {
	private BufferedImage imagenIcono, imagenFondo;
	private MenuPanel menu;
	
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
		
		
		menu = new MenuPanel(imagenFondo);
		add(menu);
		
		menu.getBotonExit().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		setVisible(true);
	}
}
