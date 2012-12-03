package vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
	private JLabel nivel;
	private JPanel panel;
	private JButton btnIniciar;
	private JButton btnDetener;
	private Clip clip;

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

		btnIniciar = new JButton("Iniciar Juego");
		btnIniciar.setBounds(870, 100, 130, 25);
		frame.getContentPane().add(btnIniciar);
		btnDetener = new JButton("Pausar");

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
				nivel.setVisible(true);
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

		panel = new SuperficiePanel();
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
		puntuacion.setSize(200, 20);
		puntuacion.setHorizontalAlignment(JLabel.LEFT);
		puntuacion.setVisible(false);
		
		nivel = new JLabel("Nivel: 1");
		nivel.setLocation(850, 190);
		nivel.setSize(200, 20);
		nivel.setHorizontalAlignment(JLabel.LEFT);
		nivel.setVisible(false);

		frame.getContentPane().add(puntuacion);
		frame.getContentPane().add(nivel);
		
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
		if (panel != null) {
			panel.setBackground(new Color(0, 127, 0));
		}
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

	@Override
	public void showPerdido() {
		panel.setBackground(new Color(0, 0, 0));
		btnDetener.setText("Pausar");
		btnDetener.setVisible(false);
		Graphics grafico = ((SuperficiePanel) panel).getBuffer();
		URL dir = this.getClass().getResource("/images/game-over.jpg");
		URL dirSound = this.getClass()
		.getResource("/images/crashSound.wav");
		try {
			clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem
					.getAudioInputStream(dirSound);
			clip.open(inputStream);
			clip.start();
			BufferedImage imagen = ImageIO.read(dir);
			grafico.drawImage(imagen, 220, 150, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		panel.repaint();

	}

	@Override
	public void showLevelUp(Integer nivel) {
		this.nivel.setText("Nivel: " + nivel);
	}

}
