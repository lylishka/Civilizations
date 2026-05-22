package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import game.Civilization;
import game.Variables;
import logic.Battle;
import logic.BattleMechanics;
import logic.BuildingException;
import logic.ResourceException;

public class Game extends JPanel {
	private BufferedImage fondo;
	private JTabbedPane tabs;
	private JLabel contador, cantidadMadera, cantidadHierro, cantidadComida, cantidadMana;
	private Battle batallaActual;
	private JButton	botonBattle, botonSave, botonSaveMenu, botonSaveExit;
	private boolean siguienteBatalla = false;
	private String nombreCivilizacion;
	
	private ArrayList<ElementoVisual> elementosEnPantalla = new ArrayList<>();
	
	private Civilization miCivilization;
	
	private JLabel[] contadoresUnidades = new JLabel[9];
	private JLabel[] contadoresEdificios = new JLabel[5];
	
	private static final int ZONA_X_MIN = 300;
	private static final int ZONA_X_MAX = 1100;
	private static final int ZONA_Y_MIN = 120;
	private static final int ZONA_Y_MAX = 580;
	private static final int SPRITE_SIZE = 55;
	
	public Game(String nombreCivilizacion, Battle batallaActual) {
		this.nombreCivilizacion = nombreCivilizacion;
		this.batallaActual = batallaActual;
		this.miCivilization = new Civilization(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		
		setLayout(new BorderLayout());
		
		try {
			fondo = ImageIO.read(new File("./src/gui/fondo-juego.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		iniciarSuperior();
		iniciarInferior();
		restaurarElementos();
	}
	
	public boolean isSiguienteBatalla() {
		return siguienteBatalla;
	}

	public void setSiguienteBatalla(boolean siguienteBatalla) {
		this.siguienteBatalla = siguienteBatalla;
	}

	public JButton getBotonBattle() {
		return botonBattle;
	}

	public JButton getBotonSave() {
		return botonSave;
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

	public JLabel getCantidadMadera() {
		return cantidadMadera;
	}

	public void setCantidadMadera(String cantidadMadera) {
		this.cantidadMadera.setText(cantidadMadera);
	}

	public JLabel getCantidadHierro() {
		return cantidadHierro;
	}

	public void setCantidadHierro(String cantidadHierro) {
		this.cantidadHierro.setText(cantidadHierro);
	}

	public JLabel getCantidadComida() {
		return cantidadComida;
	}

	public void setCantidadComida(String cantidadComida) {
		this.cantidadComida.setText(cantidadComida);
	}

	public JLabel getCantidadMana() {
		return cantidadMana;
	}

	public void setCantidadMana(String cantidadMana) {
		this.cantidadMana.setText(cantidadMana);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		if (fondo != null) {
			g2d.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
		}
		
		for (ElementoVisual elem : elementosEnPantalla) {
			elem.dibujar(g2d);
		}
	}
	
	public void iniciarSuperior() {
		JPanel superior = new JPanel(new BorderLayout());
		superior.setBackground(Color.LIGHT_GRAY);
		
		JPanel izquierdo = new JPanel();
		izquierdo.setOpaque(false);
		
		// Materiales
		agregarRecurso(izquierdo, "madera", 0, false, true, false);
		agregarRecurso(izquierdo, "hierro", 1, false, true, false);
		agregarRecurso(izquierdo, "comida", 0, false, false, true);
		agregarRecurso(izquierdo, "mana", 1, false, false, true);
		
		// Edificios
		agregarRecurso(izquierdo, "granja", 0, false, false, false);
		agregarRecurso(izquierdo, "carpinteria", 1, false, false, false);
		agregarRecurso(izquierdo, "herreria", 2, false, false, false);
		agregarRecurso(izquierdo, "torremagica", 3, false, false, false);
		agregarRecurso(izquierdo, "iglesia", 4, false, false, false);
		
		// Unidades
		agregarRecurso(izquierdo, "espada",  0, true, false, false);
		agregarRecurso(izquierdo, "lanza", 1, true, false, false);
		agregarRecurso(izquierdo, "ballesta", 2, true, false, false);
		agregarRecurso(izquierdo, "canon", 3, true, false, false);
		agregarRecurso(izquierdo, "mago", 4, true, false, false);
		agregarRecurso(izquierdo, "sacerdote", 5, true, false, false);
		agregarRecurso(izquierdo, "torreflechas", 6, true, false, false);
		agregarRecurso(izquierdo, "catapulta", 7, true, false, false);
		agregarRecurso(izquierdo, "torrecohetes", 8, true, false, false);
	
		
		JPanel derecho = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		derecho.setOpaque(false);
		
		JLabel proxima = new JLabel("Próxima Batalla: ");
		proxima.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		contador = new JLabel("3:00");
		contador.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		derecho.add(proxima);
		derecho.add(contador);
		
		superior.add(izquierdo, BorderLayout.WEST);
		superior.add(derecho, BorderLayout.EAST);
		
		add(superior, BorderLayout.NORTH);
	}
	
	public void iniciarInferior() {
		JPanel inferior = new JPanel(new BorderLayout());
		inferior.setOpaque(false);
		
		JPanel botonesGuardar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		botonesGuardar.setOpaque(false);
		
		botonBattle = new JButton("Siguiente Batalla");
		botonSave = new JButton("Guardar");
		botonSaveMenu = new JButton("Guardar e Ir al Menú");
		botonSaveExit = new JButton("Guardar y Salir");
		
		botonBattle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				siguienteBatalla = true;
			}
		});
		
		botonesGuardar.add(botonBattle);
		botonesGuardar.add(botonSave);
		botonesGuardar.add(botonSaveMenu);
		botonesGuardar.add(botonSaveExit);
		
		inferior.add(botonesGuardar, BorderLayout.NORTH);
		
		pestañas(inferior);
		
		add(inferior, BorderLayout.SOUTH);
	}
	
	public void actualizarContador(String tiempo) {
		contador.setText(tiempo);
	}
	
	// MÉTODO DE GENERACIÓN ALEATORIA
	public void añadirElementos(String rutaImagen) {
		int x;
		int y;
		int intentos = 0;
		
		 do {
		        x = ZONA_X_MIN + (int)(Math.random() * (ZONA_X_MAX - ZONA_X_MIN));
		        y = ZONA_Y_MIN + (int)(Math.random() * (ZONA_Y_MAX - ZONA_Y_MIN));
		        intentos++;
		        if (intentos > 500) {
		            JOptionPane.showMessageDialog(this, "No hay espacio libre.", "Mapa lleno", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		    } while (hayColision(x, y));


		elementosEnPantalla.add(new ElementoVisual(rutaImagen, x, y, intentos));
		repaint();
	}
	// Saber si hay colicion
	private boolean hayColision(int x, int y) {
	    for (ElementoVisual elem : elementosEnPantalla) {
	        int distanciaX = elem.getX() - x;
	        int distanciaY = elem.getY() - y;
	        double distancia = Math.sqrt(distanciaX * distanciaX + distanciaY * distanciaY);
	        if (distancia < SPRITE_SIZE) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public void intentarAñadirElemento(String rutaImagen, int indice, String tipo, boolean esUnidad){
		
		miCivilization.setFood(batallaActual.getWasteFoodMana()[0]);
		miCivilization.setMana(batallaActual.getWasteFoodMana()[1]);
		miCivilization.setWood(batallaActual.getWasteWoodIron()[0]);
		miCivilization.setIron(batallaActual.getWasteWoodIron()[1]);
		
		miCivilization.setFarm(batallaActual.getActualNumberBuldingCivilization()[0]);
		miCivilization.setCarpentry(batallaActual.getActualNumberBuldingCivilization()[1]);
		miCivilization.setSmithy(batallaActual.getActualNumberBuldingCivilization()[2]);
		miCivilization.setMagicTower(batallaActual.getActualNumberBuldingCivilization()[3]);
		miCivilization.setChurch(batallaActual.getActualNumberBuldingCivilization()[4]);
		
		miCivilization.getArmy()[8] = new ArrayList<>();
		for(int i = 0; i < batallaActual.getActualNumberUnitsCivilization()[8]; i++) {
			miCivilization.getArmy()[8].add(new game.Priest(0, 0));
		}
		
		try {
			switch (tipo) {
			case "espada": miCivilization.newSwordsman(1); break;
			case "lanza": miCivilization.newSpearman(1); break;
			case "ballesta": miCivilization.newCrossbow(1); break;
			case "canon": miCivilization.newCannon(1); break;
			case "torreflechas": miCivilization.newArrowTower(1); break;
			case "catapulta": miCivilization.newCatapult(1); break;
			case "torrecohetes": miCivilization.newRocketLauncher(1); break;
			case "mago": miCivilization.newMagician(1); break;
			case "sacerdote": miCivilization.newPriest(1); break;
			
			case "granja": miCivilization.newFarm(); break;
			case "carpinteria": miCivilization.newCarpentry(); break;
			case "herreria": miCivilization.newSmithy(); break;
			case "torremagica": miCivilization.newMagicTower(); break;
			case "iglesia": miCivilization.newChurch(); break;
			}
			
			añadirElementos(rutaImagen);
			guardarPosicion(indice);
			
			batallaActual.getWasteFoodMana()[0] = miCivilization.getFood();
			batallaActual.getWasteFoodMana()[1] = miCivilization.getMana();
			batallaActual.getWasteWoodIron()[0] = miCivilization.getWood();
			batallaActual.getWasteWoodIron()[1] = miCivilization.getIron();
			
			if (esUnidad) {
				batallaActual.getActualNumberUnitsCivilization()[indice]++;
				contadoresUnidades[indice].setText("" + batallaActual.getActualNumberUnitsCivilization()[indice]);
			} else {
				int indEdificio = indice - 9;
				batallaActual.getActualNumberBuldingCivilization()[indEdificio]++;
				contadoresEdificios[indEdificio].setText("" + batallaActual.getActualNumberBuldingCivilization()[indEdificio]);
			}
			
			// Actualizar textos numéricos de arriba
				setCantidadMadera(String.valueOf(batallaActual.getWasteWoodIron()[0]));
				setCantidadHierro(String.valueOf(batallaActual.getWasteWoodIron()[1]));
				setCantidadComida(String.valueOf(batallaActual.getWasteFoodMana()[0]));
				setCantidadMana(String.valueOf(batallaActual.getWasteFoodMana()[1]));
			
		} catch (ResourceException | BuildingException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
		}
		
		
	}
	public void guardarPosicion(int indice) {
		if (!elementosEnPantalla.isEmpty()) {
			ElementoVisual ultimo = elementosEnPantalla.get(elementosEnPantalla.size() - 1);
	        batallaActual.getPositions()[indice][0] = ultimo.getX();
	        batallaActual.getPositions()[indice][1] = ultimo.getY();
		}
	}
	
	public void agregarRecurso(JPanel recursos, String nombreImg, int indice, boolean esUnidad, boolean esResiduo, boolean esElemento) {
		ImageIcon icono = new ImageIcon("./src/gui/" + nombreImg + ".png");
		ImageIcon img = new ImageIcon(icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
		JLabel imagen = new JLabel(img);
		
		int valor = obtenerValor(indice, esUnidad, esResiduo, esElemento);
		JLabel cantidad = new JLabel(String.valueOf(valor));
		cantidad.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		if (nombreImg.equals("madera")) {
			cantidadMadera = cantidad;
		} else if (nombreImg.equals("hierro")) {
			cantidadHierro = cantidad;
		} else if (nombreImg.equals("comida")) {
			cantidadComida = cantidad;
		} else if (nombreImg.equals("mana")) {
			cantidadMana = cantidad;
		}
		
		if (esUnidad) {
	        contadoresUnidades[indice] = cantidad;
	    } else if (!esResiduo && !esElemento) {
	        contadoresEdificios[indice] = cantidad;
	    }
		
		JPanel recurso = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
		recurso.setOpaque(false);
		
		recurso.add(imagen);
		recurso.add(cantidad);
		
		recursos.add(recurso);
	}
	
	public int obtenerValor(int indice, boolean esUnidad, boolean esResiduo, boolean esElemento) {
		int valor = 0;
		
		if (esResiduo) {
			valor = batallaActual.getWasteWoodIron()[indice];
		} else if (esElemento) {
			valor = batallaActual.getWasteFoodMana()[indice];
		} else if (esUnidad) {
			valor = batallaActual.getActualNumberUnitsCivilization()[indice];
		} else {
			valor = batallaActual.getActualNumberBuldingCivilization()[indice];
		}
		
		return valor;
	}
	
	public void pestañas(JPanel pestañas) {
		tabs = new JTabbedPane();
		tabs.setBackground(Color.LIGHT_GRAY);
		
		pestañas.add(tabs, BorderLayout.CENTER);
		
		tabs.addTab("TROPAS OFENSIVAS", contenidotabs("tropasO"));
		tabs.addTab("TROPAS ESPECIALES", contenidotabs("tropasE"));
		tabs.addTab("ESTRUCTURAS DEFENSIVAS", contenidotabs("defensas"));
		tabs.addTab("EDIFICIOS", contenidotabs("edificios"));
	}
	
	public JPanel contenidotabs(String tipo) {
		JPanel contenidoPestañas = new JPanel(new BorderLayout());
		contenidoPestañas.setOpaque(false);
		
		JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER));
		botones.setOpaque(false);
		
		JPanel costes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		costes.setOpaque(false);
		costes.setPreferredSize(new Dimension(350, 50));
		
		if (tipo.equals("tropasO")) {
			agregarBotonesTropasO(botones, costes);
		} else if (tipo.equals("tropasE")) {
			agregarBotonesTropasE(botones, costes);			
		} else if (tipo.equals("defensas")) {
			agregarBotonesDefensas(botones, costes);
		} else if (tipo.equals("edificios")) {
			agregarBotonesEdificios(botones, costes);
		}
		
		contenidoPestañas.add(botones, BorderLayout.CENTER);
		contenidoPestañas.add(costes, BorderLayout.EAST);
		
		return contenidoPestañas;
	}
	
	public void agregarBotonesTropasO(JPanel botones, JPanel costes) {
		ImageIcon iconEspadachin = new ImageIcon("./src/gui/espada.png");
		JButton botonEspadachin = new JButton("Espadachin", iconEspadachin);
		botonEspadachin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intentarAñadirElemento("./src/gui/espada.png", 0, "espada", true);
			}
		});
		costes(botones, costes, botonEspadachin, Variables.FOOD_COST_SWORDSMAN, Variables.WOOD_COST_SWORDSMAN, Variables.IRON_COST_SWORDSMAN, Variables.MANA_COST_SWORDSMAN);
		
		ImageIcon iconLancero = new ImageIcon("./src/gui/lanza.png");
		JButton botonLancero = new JButton("Lancero", iconLancero);
		botonLancero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intentarAñadirElemento("./src/gui/lanza.png", 1, "lanza", true);
			}
		});
		costes(botones, costes, botonLancero, Variables.FOOD_COST_SPEARMAN, Variables.WOOD_COST_SPEARMAN, Variables.IRON_COST_SPEARMAN, Variables.MANA_COST_SPEARMAN);
		
		ImageIcon iconBallesta = new ImageIcon("./src/gui/ballesta.png");
		JButton botonBallesta = new JButton("Ballesta", iconBallesta);
		botonBallesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intentarAñadirElemento("./src/gui/ballesta.png", 2, "ballesta", true);
			}
		});
		costes(botones, costes, botonBallesta, Variables.FOOD_COST_CROSSBOW, Variables.WOOD_COST_CROSSBOW, Variables.IRON_COST_CROSSBOW, Variables.MANA_COST_CROSSBOW);
		
		ImageIcon iconCanon = new ImageIcon("./src/gui/canon.png");
		JButton botonCanon = new JButton("Canon", iconCanon);
		botonCanon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intentarAñadirElemento("./src/gui/canon.png", 3, "canon", true);
			}
		});
		costes(botones, costes, botonCanon, Variables.FOOD_COST_CANNON, Variables.WOOD_COST_CANNON, Variables.IRON_COST_CANNON, Variables.MANA_COST_CANNON);
	}
	
	public void agregarBotonesTropasE(JPanel botones, JPanel costes) {		
		ImageIcon iconMago = new ImageIcon("./src/gui/mago.png");
		JButton botonMago = new JButton("Mago", iconMago);
		botonMago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intentarAñadirElemento("./src/gui/mago.png", 7, "mago", true);
			}
		});
		costes(botones, costes, botonMago, Variables.FOOD_COST_MAGICIAN, Variables.WOOD_COST_MAGICIAN, Variables.IRON_COST_MAGICIAN, Variables.MANA_COST_MAGICIAN);
		
		ImageIcon iconSacerdote = new ImageIcon("./src/gui/sacerdote.png");
		JButton botonSacerdote = new JButton("Sacerdote", iconSacerdote);
		botonSacerdote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intentarAñadirElemento("./src/gui/sacerdote.png", 8, "sacerdote", true);
			}
		});
		costes(botones, costes, botonSacerdote, Variables.FOOD_COST_PRIEST, Variables.WOOD_COST_PRIEST, Variables.IRON_COST_PRIEST, Variables.MANA_COST_PRIEST);
	}
	
	public void agregarBotonesDefensas(JPanel botones, JPanel costes) {
		ImageIcon iconTorreFlechas = new ImageIcon("./src/gui/torreflechas.png");
		JButton botonTorreFlechas = new JButton("Torre de Flechas", iconTorreFlechas);
		botonTorreFlechas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intentarAñadirElemento("./src/gui/torreflechas.png", 4, "torreflechas", true);
			}
		});
		costes(botones, costes, botonTorreFlechas, Variables.FOOD_COST_ARROWTOWER, Variables.WOOD_COST_ARROWTOWER, Variables.IRON_COST_ARROWTOWER, Variables.MANA_COST_ARROWTOWER);
		
		ImageIcon iconCatapulta = new ImageIcon("./src/gui/catapulta.png");
		JButton botonCatapulta = new JButton("Catapulta", iconCatapulta);
		botonCatapulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intentarAñadirElemento("./src/gui/catapulta.png", 5, "catapulta", true);
			}
		});
		costes(botones, costes, botonCatapulta, Variables.FOOD_COST_CATAPULT, Variables.WOOD_COST_CATAPULT, Variables.IRON_COST_CATAPULT, Variables.MANA_COST_CATAPULT);
		
		ImageIcon iconTorreLanzaCohetes = new ImageIcon("./src/gui/torrecohetes.png");
		JButton botonTorreLanzaCohetes = new JButton("Torre Lanza Cohetes", iconTorreLanzaCohetes);
		botonTorreLanzaCohetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intentarAñadirElemento("./src/gui/torrecohetes.png", 6, "torrecohetes", true);
			}
		});
		costes(botones, costes, botonTorreLanzaCohetes, Variables.FOOD_COST_ROCKETLAUNCHERTOWER, Variables.WOOD_COST_ROCKETLAUNCHERTOWER, Variables.IRON_COST_ROCKETLAUNCHERTOWER, Variables.MANA_COST_ROCKETLAUNCHERTOWER);
	}
	
	public void agregarBotonesEdificios(JPanel botones, JPanel costes) {
		ImageIcon iconGranja = new ImageIcon("./src/gui/granja.png");
		JButton botonGranja = new JButton("Granja", iconGranja);
		botonGranja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intentarAñadirElemento("./src/gui/granja.png", 9, "granja", false);
			}
		});
		costes(botones, costes, botonGranja, Variables.FOOD_COST_FARM, Variables.WOOD_COST_FARM, Variables.IRON_COST_FARM, 0);
		
		ImageIcon iconCarpinteria = new ImageIcon("./src/gui/carpinteria.png");
		JButton botonCarpinteria = new JButton("Carpinteria", iconCarpinteria);
		botonCarpinteria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intentarAñadirElemento("./src/gui/carpinteria.png", 10, "carpinteria", false);
			}
		});
		costes(botones, costes, botonCarpinteria, Variables.FOOD_COST_CARPENTRY, Variables.WOOD_COST_CARPENTRY, Variables.IRON_COST_CARPENTRY, 0);
		
		ImageIcon iconHerreria = new ImageIcon("./src/gui/herreria.png");
		JButton botonHerreria = new JButton("Herreria", iconHerreria);
		botonHerreria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intentarAñadirElemento("./src/gui/herreria.png", 11, "herreria", false);
			}
		});
		costes(botones, costes, botonHerreria, Variables.FOOD_COST_SMITHY, Variables.WOOD_COST_SMITHY, Variables.IRON_COST_SMITHY, 0);
		
		ImageIcon iconTorreMagica = new ImageIcon("./src/gui/torremagica.png");
		JButton botonTorreMagica = new JButton("Torre Mágica", iconTorreMagica);
		botonTorreMagica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intentarAñadirElemento("./src/gui/torremagica.png", 12, "torremagica", false);
			}
		});
		costes(botones, costes, botonTorreMagica, Variables.FOOD_COST_MAGICTOWER, Variables.WOOD_COST_MAGICTOWER, Variables.IRON_COST_MAGICTOWER, 0);
		
		ImageIcon iconIglesia = new ImageIcon("./src/gui/iglesia.png");
		JButton botonIglesia = new JButton("Iglesia", iconIglesia);
		botonIglesia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intentarAñadirElemento("./src/gui/iglesia.png", 13, "iglesia", false);
			}
		});
		costes(botones, costes, botonIglesia, Variables.FOOD_COST_CHURCH, Variables.WOOD_COST_CHURCH, Variables.IRON_COST_CHURCH, 0);
	}
	
	public void costes(JPanel botones, JPanel costes, JButton boton, int food, int wood, int iron, int mana) {
		botones.add(boton);
		
		JPanel coste = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		coste.setOpaque(false);
		coste.setVisible(false);
		
		if (food > 0) {
			infoConten(coste, "comida", food);
		}
		
		if (wood > 0) {
			infoConten(coste, "madera", wood);
		}
		
		if (iron > 0) {
			infoConten(coste, "hierro", iron);
		}
		
		if (mana > 0) {
			infoConten(coste, "mana", mana);
		}
		
		costes.add(coste);
		
		boton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				coste.setVisible(true);
			}
			
			public void mouseExited(MouseEvent e ) {
				coste.setVisible(false);
			}
		});
	}
	
	public void infoConten(JPanel conten, String nombreImg, int valor) {
		ImageIcon icono = new ImageIcon("./src/gui/" + nombreImg + ".png");
		ImageIcon img = new ImageIcon(icono.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		JLabel imagen = new JLabel(img);	
		
		JLabel cantidad = new JLabel(String.valueOf(valor));
		cantidad.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		JPanel info = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		info.setOpaque(false);
		
		info.add(imagen);
		info.add(cantidad);
		
		conten.add(info);
	}
	public void ejecutarBatalla(int numBatalla) {
		BattleMechanics mecanicas = (BattleMechanics) batallaActual;
		
		mecanicas.generarEnemigo(numBatalla);
		
		int espadachines = ((ArrayList) mecanicas.getArmies()[1].get(0)).size();
	    int lanceros     = ((ArrayList) mecanicas.getArmies()[1].get(1)).size();
	    int ballestas    = ((ArrayList) mecanicas.getArmies()[1].get(2)).size();
	    int canones      = ((ArrayList) mecanicas.getArmies()[1].get(3)).size();
		
		int[] enemios = mecanicas.getActualNumberUnitsEnemy();
		for (int i = 0; i < enemios[0]; i++) {
			añadirElementos("./src/gui/espada.png");
		}
		for (int i = 0; i < enemios[1]; i++) {
			añadirElementos("./src/gui/lanza.png");
		}
		for (int i = 0; i < enemios[2]; i++) {
			añadirElementos("./src/gui/ballesta.png");
		}
		for (int i = 0; i < enemios[3]; i++) {
			añadirElementos("./src/gui/canon.png");
		}
		
		repaint();
	}
	public void resolverBatalla(int numBatalla) {
		BattleMechanics mecanicas = (BattleMechanics) batallaActual;
		
		boolean ganamos = mecanicas.executeBattle();
		
		elementosEnPantalla = new ArrayList<>();
		
		int[] cantidades = batallaActual.getActualNumberUnitsCivilization();
		for (int i = 0; i < 9; i++) {
			 int sobrevivientes = ((ArrayList) mecanicas.getArmies()[0].get(i)).size();
		        batallaActual.getActualNumberUnitsCivilization()[i] = sobrevivientes;
		        if (contadoresUnidades[i] != null) {
		            contadoresUnidades[i].setText(String.valueOf(sobrevivientes));
		        }
			}
		
		restaurarElementos();
		
		// Actualizar recursos
		setCantidadMadera(String.valueOf(batallaActual.getWasteWoodIron()[0]));
	    setCantidadHierro(String.valueOf(batallaActual.getWasteWoodIron()[1]));
	    setCantidadComida(String.valueOf(batallaActual.getWasteFoodMana()[0]));
	    setCantidadMana(String.valueOf(batallaActual.getWasteFoodMana()[1]));
	    
	    // Mostrar resultado
	    String titulo = ganamos ? "¡Victoria!" : "Derrota";
	    JOptionPane.showMessageDialog(this, mecanicas.getBattleReport(numBatalla), 
	        titulo, ganamos ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
	    
	    repaint();
		}
	
	public void restaurarElementos() {
		String[] rutas = {
		        "./src/gui/espada.png",       
		        "./src/gui/lanza.png",        
		        "./src/gui/ballesta.png",     
		        "./src/gui/canon.png",        
		        "./src/gui/torreflechas.png", 
		        "./src/gui/catapulta.png",    
		        "./src/gui/torrecohetes.png", 
		        "./src/gui/mago.png",         
		        "./src/gui/sacerdote.png",    
		        "./src/gui/granja.png",       
		        "./src/gui/carpinteria.png",  
		        "./src/gui/herreria.png",     
		        "./src/gui/torremagica.png",  
		        "./src/gui/iglesia.png"       
		    };
		
		int [][] positions = batallaActual.getPositions();
		for (int i = 0; i < rutas.length; i++) {
			int x = positions[i][0];
			int y = positions[i][1];
			
			if (x > 0 && y > 0) {
				elementosEnPantalla.add(new ElementoVisual(rutas[i], x, y, i));
			}
		}

	}
}

class ElementoVisual {
	private BufferedImage imagen;
	private int x;
	private int y;
	private int tipo;
	
	public ElementoVisual(String rutaImagen, int x, int y, int tipo) {
		try {
			this.imagen = ImageIO.read(new File(rutaImagen));
		} catch (IOException e) {
			System.err.println("No se pudo cargar la imagen en: " + rutaImagen);
		}
		this.x = x;
		this.y = y;
		this.tipo = tipo;
	}
	
	public int getTipo() { return tipo; }
	public int getX() { return x; }
    public int getY() { return y; }
	
	public void dibujar(Graphics2D g2d) {
		if (imagen != null) {
			g2d.drawImage(imagen, x, y, 45, 45, null);
		}
	}
	

}