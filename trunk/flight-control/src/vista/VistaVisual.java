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
import modelo.factories.FactoryAvionHelicoptero;
import modelo.factories.FactoryAvionLiviano;
import modelo.factories.FactoryAvionPesado;
import modelo.general.Posicion;
import modelo.juego.Escenario;
import modelo.pistas.Pista;
import modelo.pistas.PistaHelipuerto;
import modelo.pistas.PistaLarga;
import modelo.pistas.PistaSimple;
import fiuba.algo3.titiritero.dibujables.SuperficiePanel;
import fiuba.algo3.titiritero.modelo.GameLoop;
import fiuba.algo3.titiritero.modelo.SuperficieDeDibujo;
import fiuba.algo3.titiritero.modelo.ViewLoop;

public class VistaVisual implements ViewManager {

	private JFrame frame;

	private FlightControl flightControl;

	private ViewLoop viewLoop;

	/**
	 * Create the application.
	 */
	public VistaVisual(FlightControl flightControl) {
		try {
			this.flightControl = flightControl;
			initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * 
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
				flightControl.getGameLoop().iniciarEjecucion();
				for (ViewManager manager : flightControl.getViewManagers()) {
					manager.iniciarEjecucion();
				}
			}
		});
		btnIniciar.setBounds(42, 16, 77, 25);
		frame.getContentPane().add(btnIniciar);

		JButton btnDetener = new JButton("Pausar");
		btnDetener.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flightControl.getGameLoop().detenerEjecucion();
				for (ViewManager manager : flightControl.getViewManagers()) {
					manager.detenerEjecucion();
				}
			}
		});
		btnDetener.setBounds(325, 16, 92, 25);
		frame.getContentPane().add(btnDetener);

		JPanel panel = new SuperficiePanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(40, 50, 840, 650);
		frame.getContentPane().add(panel);

		this.viewLoop = new ViewLoop(100, (SuperficieDeDibujo) panel);

		panel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				flightControl.huboUnClick(arg0.getX(), arg0.getY());

			}
		});

		frame.setFocusable(true);
		btnDetener.setFocusable(false);
		btnIniciar.setFocusable(false);

		frame.setVisible(true);

	}

	@Override
	public void addVistaAvion(VistaAvion va) {
		this.viewLoop.agregar(va);
	}

	@Override
	public void removerVistaAvion(VistaAvion va) {
		this.viewLoop.remover(va);
		System.out.println("Removiendo");
		System.out.print("Cant Actual"
				+ this.viewLoop.getCantidadDeObjetosDibujables());

	}

	@Override
	public void addVistaPista(VistaPista vp) {
		this.viewLoop.agregar(vp);

	}

	@Override
	public void removerVistaPista(VistaPista vp) {
		this.viewLoop.remover(vp);
	}

	public void iniciarEjecucion() {
		viewLoop.iniciarEjecucion();
	}

	public void detenerEjecucion() {
		viewLoop.detenerEjecucion();
	}

}
