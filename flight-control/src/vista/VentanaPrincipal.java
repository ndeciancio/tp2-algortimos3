package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fiuba.algo3.titiritero.dibujables.SuperficiePanel;
import fiuba.algo3.titiritero.modelo.GameLoop;

public class VentanaPrincipal {

	private JFrame frame;
	private GameLoop gameLoop;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
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
	public VentanaPrincipal() {
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
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gameLoop.iniciarEjecucion();
			}
		});
		btnIniciar.setBounds(42, 16, 77, 25);
		frame.getContentPane().add(btnIniciar);
		
		JButton btnDetener = new JButton("Detener");
		btnDetener.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameLoop.detenerEjecucion();
			}
		});
		btnDetener.setBounds(325, 16, 92, 25);
		frame.getContentPane().add(btnDetener);
		
		JPanel panel = new SuperficiePanel();
		panel.setBackground(new Color(0, 100, 0));
		panel.setBounds(42, 53, 575, 387);
		frame.getContentPane().add(panel);
		

		
		/*this.gameLoop = new GameLoop((SuperficieDeDibujo) panel);
		final ObjetoMultiforma modelo = new ObjetoMultiforma();
		this.gameLoop.agregar(modelo);
		Circulo circulo = new VistaObjetoMultiforma(modelo);
		this.gameLoop.agregar(circulo);
		
		ObjetoMultiforma modelo2 = new ObjetoMultiforma();
		modelo2.mutar();
		this.gameLoop.agregar(modelo2);
		Figura cuadrado = new Vista2ObjetoMultiforma(modelo2);
		this.gameLoop.agregar(cuadrado);

		ObjetoMultiforma modelo3 = new ObjetoMultiforma();
		modelo3.inmutar();
		this.gameLoop.agregar(modelo3);
		Imagen imagen = new Vista3ObjetoMultiforma(modelo3);
		this.gameLoop.agregar(imagen);
		
		panel.addMouseListener(new MouseAdapter() {
					
			@Override
			public void mouseClicked(MouseEvent arg0) {
				modelo.moverA(arg0.getX(), arg0.getY());
					
			}});*/

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
	
	
	
}
