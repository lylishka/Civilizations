package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.module.ResolutionException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import game.Civilization;
import logic.Battle;
import logic.BuildingException;
import logic.ResourceException;

public class Game extends JPanel {
	private BufferedImage fondo;
	private JTabbedPane tabs;
	private JLabel contador;
	private Battle batallaActual;
	private JButton	botonSaveMenu, botonSaveExit;
	
	private ArrayList<ElementoVisual> elementosEnPantalla = new ArrayList<>();
	private Civilization miCivilization;
	
	public Game(String nombreCivilizacion, Battle batallaActual) {
		this.batallaActual = batallaActual;
		setLayout(new BorderLayout());
		
		try {
			fondo = ImageIO.read(new File("./src/gui/fondo-juego.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JPanel arribaCentro = new JPanel(new FlowLayout(1)) {
			protected void paintComponent(Graphics g) {
				
			}
		};
		
		contador = new JLabel("3:00");
		contador.setFont(new Font("Times New Roman", Font.BOLD, 50));
		
		arribaCentro.add(contador);
		add(arribaCentro, BorderLayout.NORTH);
		
		JPanel abajo = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				
			}
		};
		
		JPanel abajoDerecha = new JPanel(new FlowLayout(FlowLayout.RIGHT)) {
			protected void paintComponent(Graphics g) {
				
			}
		};
		
		botonSaveMenu = new JButton("Guardar e Ir al Menú");
		botonSaveExit = new JButton("Guardar y Salir");
		
		abajoDerecha.add(botonSaveMenu);
		abajoDerecha.add(botonSaveExit);
		
		abajo.add(abajoDerecha, BorderLayout.NORTH);
		
		tabs = new JTabbedPane();
		tabs.setBackground(Color.LIGHT_GRAY);
		
		tabs.setFocusable(false);
		
		JPanel tab1 = new JPanel();
		tab1.setBackground(Color.LIGHT_GRAY);
		tab1.setOpaque(false);
		agregarBotonesTropasO(tab1);
		
		JPanel tab2 = new JPanel();
		tab2.setBackground(Color.LIGHT_GRAY);
		tab2.setOpaque(false);
		agregarBotonesTropasE(tab2);
		
		JPanel tab3 = new JPanel();
		tab3.setBackground(Color.LIGHT_GRAY);
		tab3.setOpaque(false);
		agregarBotonesDefensas(tab3);
		
		JPanel tab4 = new JPanel();
		tab4.setBackground(Color.LIGHT_GRAY);
		tab4.setOpaque(false);
		agregarBotonesEdificios(tab4);
		
		tabs.addTab("TROPAS OFENSIVAS", tab1);
		tabs.addTab("TROPAS ESPECIALES", tab2);
		tabs.addTab("ESTRUCTURAS DEFENSIVAS", tab3);
		tabs.addTab("EDIFICIOS", tab4);
		
		abajo.add(tabs, BorderLayout.CENTER);
		add(abajo, BorderLayout.SOUTH);
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

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
		
		for (ElementoVisual elem : elementosEnPantalla) {
			elem.dibujar(g2d);
		}
	}
	
	public void actualizarContador(String tiempo) {
		contador.setText(tiempo);
	}
	
	public void añadirElementos(String rutaImagen) {

		// Límites seguros dentro del área visible del mapa
				int margenMinX = 60;
				int margenMaxX = getWidth() - 120;
				int margenMinY = 120;               // Evita que aparezcan encima del contador superior
				int margenMaxY = getHeight() - 260; // Evita que aparezcan ocultos tras el JTabbedPane inferior
				
				int x = margenMinX;
				int y = margenMinY;
				
				// Fórmula estándar: minimo + (int)(Math.random() * (maximo - minimo + 1))
				if (margenMaxX > margenMinX) {
					x = margenMinX + (int) (Math.random() * (margenMaxX - margenMinX + 1));
				}
				if (margenMaxY > margenMinY) {
					y = margenMinY + (int) (Math.random() * (margenMaxY - margenMinY + 1));
				}

				// Añadimos el nuevo elemento a la lista y repintamos el panel
				elementosEnPantalla.add(new ElementoVisual(rutaImagen, x, y));
				repaint();
	}
	
	public void agregarBotonesTropasO(JPanel tropas) {
		ImageIcon iconEspadachin = new ImageIcon("./src/gui/espada.png");
		JButton botonEspadachin = new JButton("Espadachin", iconEspadachin);
		botonEspadachin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				añadirElementos("./src/gui/espada.png");
				
			}
		});
		tropas.add(botonEspadachin);
		
		ImageIcon iconLancero = new ImageIcon("./src/gui/lanza.png");
		JButton botonLancero = new JButton("Lancero", iconLancero);
		botonLancero.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				añadirElementos("./src/gui/lanza.png");
				
			}
			
		});
		tropas.add(botonLancero);
		
		ImageIcon iconBallesta = new ImageIcon("./src/gui/ballesta.png");
		JButton botonBallesta = new JButton("Ballesta", iconBallesta);
		botonBallesta.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				añadirElementos("./src/gui/ballesta.png");
				
			}
		});
		tropas.add(botonBallesta);
		
		ImageIcon iconCanon = new ImageIcon("./src/gui/canon.png");
		JButton botonCanon = new JButton("Canon", iconCanon);
		botonCanon.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				añadirElementos("./src/gui/canon.png");
			}
		});
		tropas.add(botonCanon);
	}
	
	public void agregarBotonesTropasE(JPanel tropas) {		
		ImageIcon iconMago = new ImageIcon("./src/gui/mago.png");
		JButton botonMago = new JButton("Mago", iconMago);
		botonMago.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				añadirElementos("./src/gui/mago.png");
			}
		});
		tropas.add(botonMago);
		
		ImageIcon iconSacerdote = new ImageIcon("./src/gui/sacerdote.png");
		JButton botonSacerdote = new JButton("Sacerdote", iconSacerdote);
		botonSacerdote.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				añadirElementos("./src/gui/sacerdote.png");
			}
		});
		tropas.add(botonSacerdote);
	}
	
	public void agregarBotonesDefensas(JPanel defensas) {
		ImageIcon iconTorreFlechas = new ImageIcon("./src/gui/torreflechas.png");
		JButton botonTorreFlechas = new JButton("Torre de Flechas", iconTorreFlechas);
		botonTorreFlechas.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				añadirElementos("./src/gui/torreflechas.png");
			}
		});
		defensas.add(botonTorreFlechas);
		
		ImageIcon iconCatapulta = new ImageIcon("./src/gui/catapulta.png");
		JButton botonCatapulta = new JButton("Catapulta", iconCatapulta);
		botonCatapulta.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				añadirElementos("./src/gui/catapulta.png");
			}
		});
		defensas.add(botonCatapulta);
		
		ImageIcon iconTorreLanzaCohetes = new ImageIcon("./src/gui/torrecohetes.png");
		JButton botonTorreLanzaCohetes = new JButton("Torre Lanza Cohetes", iconTorreLanzaCohetes);
		botonTorreLanzaCohetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				añadirElementos("./src/gui/torrecohetes.png");
			}
		});
		defensas.add(botonTorreLanzaCohetes);
	}
	public void agregarBotonesEdificios(JPanel edificios) {
		ImageIcon iconGranja = new ImageIcon("./src/gui/granja.png");
		JButton botonGranja = new JButton("Granja", iconGranja);
		botonGranja.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				añadirElementos("./src/gui/granja.png");
			}
		});
		edificios.add(botonGranja);
		
		ImageIcon iconCarpinteria = new ImageIcon("./src/gui/carpinteria.png");
		JButton botonCarpinteria = new JButton("Carpinteria", iconCarpinteria);
		botonCarpinteria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				añadirElementos("./src/gui/carpinteria.png");
			}
		});
		edificios.add(botonCarpinteria);
		
		ImageIcon iconHerreria = new ImageIcon("./src/gui/herreria.png");
		JButton botonHerreria = new JButton("Herreria", iconHerreria);
		botonHerreria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				añadirElementos("./src/gui/herreria.png");
			}
		});
		edificios.add(botonHerreria);
		
		ImageIcon iconTorreMagica = new ImageIcon("./src/gui/torremagica.png");
		JButton botonTorreMagica = new JButton("Torre Mágica", iconTorreMagica);
		botonTorreMagica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				añadirElementos("./src/gui/torremagica.png");
			}
		});
		edificios.add(botonTorreMagica);
		
		ImageIcon iconIglesia = new ImageIcon("./src/gui/iglesia.png");
		JButton botonIglesia = new JButton("Iglesia", iconIglesia);
		botonIglesia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				añadirElementos("./src/gui/iglesia.png");
			}
		});
		edificios.add(botonIglesia);
	}
}

class ElementoVisual {
	private BufferedImage imagen;
	private int x;
	private int y;
	
	public ElementoVisual(String rutaImagen, int x, int y) {
		try {
			this.imagen = ImageIO.read(new File(rutaImagen));
		} catch (IOException e) {
			System.err.println("No se pudo cargar la imagen en: " + rutaImagen);
		}
		this.x = x;
		this.y = y;
	}
	
	public void dibujar(Graphics2D g2d) {
		if (imagen != null) {
			// Dibuja el icono de la tropa o edificio con un tamaño proporcional de 45x45 píxeles
			g2d.drawImage(imagen, x, y, 45, 45, null);
		}
	}
}