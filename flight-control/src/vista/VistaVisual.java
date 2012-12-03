package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fiuba.algo3.titiritero.dibujables.SuperficiePanel;
import fiuba.algo3.titiritero.modelo.SuperficieDeDibujo;
import fiuba.algo3.titiritero.modelo.ViewLoop;

public class VistaVisual implements ViewManager {

	private JFrame frame;

	private FlightControl flightControl;

	private ViewLoop viewLoop;

	private JLabel puntuacion;

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
		frame.setBounds(10, 10, 1050, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Flight Control");

		final JButton btnIniciar = new JButton("Iniciar Juego");
		btnIniciar.setBounds(870, 100, 130, 25);
		frame.getContentPane().add(btnIniciar);
		final JButton btnDetener = new JButton("Pausar");

		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				flightControl.setUpGame();
				flightControl.getGameLoop().iniciarEjecucion();
				for (ViewManager manager : flightControl.getViewManagers()) {
					manager.iniciarEjecucion();
				}
				if (btnIniciar.getText().equals("Iniciar Juego")) {
					btnIniciar.setText("Reiniciar Juego");

				}
				puntuacion.setVisible(true);
				btnDetener.setText("Pausar");
				btnDetener.setVisible(true);
			}
		});
		btnDetener.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnDetener.getText().equals("Pausar")) {
					flightControl.getGameLoop().detenerEjecucion();
					for (ViewManager manager : flightControl.getViewManagers()) {
						manager.detenerEjecucion();
					}
					btnDetener.setText("Reanudar");
				} else {
					flightControl.getGameLoop().iniciarEjecucion();
					for (ViewManager manager : flightControl.getViewManagers()) {
						manager.iniciarEjecucion();
					}
					btnDetener.setText("Pausar");
				}

			}
		});
		btnDetener.setBounds(870, 135, 130, 25);
		btnDetener.setVisible(false);
		frame.getContentPane().add(btnDetener);

		JPanel panel = new SuperficiePanel();
		panel.setBackground(new Color(0, 127, 0));
		panel.setBounds(5, 5, 800, 600);
		frame.getContentPane().add(panel);

		this.viewLoop = new ViewLoop(100, (SuperficieDeDibujo) panel);

		panel.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent arg0) {
				flightControl.huboUnClick(arg0.getX(), arg0.getY());

			}
		});

		// Agrego Labels para puntuacion
		puntuacion = new JLabel("Aviones Aterrizados: 0");
		puntuacion.setLocation(850, 170);
		puntuacion.setSize(200, 50);
		puntuacion.setHorizontalAlignment(JLabel.LEFT);
		puntuacion.setVisible(false);

		frame.getContentPane().add(puntuacion);

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
	}

	@Override
	public void addVistaPista(VistaPista vp) {
		this.viewLoop.agregar(vp);

	}

	@Override
	public void removerVistaPista(VistaPista vp) {
		this.viewLoop.remover(vp);
	}

	@Override
	public void showPuntaje(Integer puntos) {
		puntuacion.setText("Aviones Aterrizados: " + puntos);
	}

	public void iniciarEjecucion() {
		viewLoop.iniciarEjecucion();
	}

	public void detenerEjecucion() {
		if (viewLoop.estaEjecutando()) {
			viewLoop.detenerEjecucion();
		}
	}

	public void removeAll() {
		viewLoop.removeAll();
	}

}
