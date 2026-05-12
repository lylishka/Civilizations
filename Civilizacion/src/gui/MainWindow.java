package gui;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MainWindow extends JFrame {
	private BufferedImage imagen;
	private MenuPanel menu;
	
	public MainWindow() {
		setTitle("Civilization");
		setBounds(300, 150, 900, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		try {
			imagen = ImageIO.read(new File("./src/gui/civilizations_icon.png"));
			setIconImage(imagen);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setLayout(new BorderLayout());
		menu = new MenuPanel();
		
		add(menu, BorderLayout.CENTER);
		
		
		setVisible(true);
	}
}
