package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import modelo.aviones.Avion;
import modelo.factories.FactoryAvion;
import modelo.factories.FactoryAvionLiviano;
import modelo.general.Posicion;
import modelo.juego.Juego;
import modelo.pistas.Pista;
import modelo.pistas.PistaSimple;
import fiuba.algo3.titiritero.dibujables.SuperficiePanel;
import fiuba.algo3.titiritero.modelo.GameLoop;
import fiuba.algo3.titiritero.modelo.SuperficieDeDibujo;
import fiuba.algo3.titiritero.modelo.ViewLoop;

public class VistaVisual implements ViewManager {

	private JFrame frame;
	private GameLoop gameLoop;

	private ViewLoop viewLoop;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaVisual window = new VistaVisual();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VistaVisual() {
		try {
			initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setForeground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 1050, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gameLoop.iniciarEjecucion();
				viewLoop.iniciarEjecucion();
			}
		});
		btnIniciar.setBounds(42, 16, 77, 25);
		frame.getContentPane().add(btnIniciar);
		
		JButton btnDetener = new JButton("Detener");
		btnDetener.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameLoop.detenerEjecucion();
				viewLoop.detenerEjecucion();
			}
		});
		btnDetener.setBounds(325, 16, 92, 25);
		frame.getContentPane().add(btnDetener);
		
		JPanel panel = new SuperficiePanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(40, 50, 840, 650);
		frame.getContentPane().add(panel);
		
		this.gameLoop = new GameLoop(100,(SuperficieDeDibujo) panel);
		this.viewLoop = new ViewLoop(20,(SuperficieDeDibujo) panel);
		
		
		List<Pista> pistas = new ArrayList<Pista>();
		Pista p = new PistaSimple(new Posicion(40,50), 15);
		pistas.add(p);
		List<FactoryAvion> factories = new ArrayList<FactoryAvion>();
		FactoryAvion f = new FactoryAvionLiviano();
		factories.add(f);
		List<ViewManager> managers = new ArrayList<ViewManager>();
		managers.add(this);
		final Juego juego = new Juego(15,pistas,factories,managers);
		this.gameLoop.agregar(juego);
		VistaPista vp = new VistaPista(p);
		this.viewLoop.agregar(vp);
		
		panel.addMouseListener(new MouseAdapter() {
					
			@Override
			public void mouseClicked(MouseEvent arg0) {
				juego.huboUnClick(arg0.getX(),arg0.getY());
					
			}});

		frame.setFocusable(true);
		btnDetener.setFocusable(false);
		btnIniciar.setFocusable(false);
				
		frame.addKeyListener(new KeyListener(
				) {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				System.out.println("Key pressed");
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
			
				System.out.println("Ping");
				
			}  
			 	
		});
		
		
		
	}
	
	@Override
	public void addAvion(Avion a) {
		VistaAvion va = new VistaAvion(a);
		this.viewLoop.agregar(va);
		this.gameLoop.agregar(a);
	}
	
	public void removerAvion(Avion a){
		this.gameLoop.remover(a);
		VistaAvion va = new VistaAvion(a);
		this.viewLoop.remover(va);
	}


}
