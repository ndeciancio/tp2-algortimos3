package vista;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

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
import fiuba.algo3.titiritero.modelo.GameLoop;

public class FlightControl {

	private Escenario game;
	private GameLoop gameLoop;
	private List<ViewManager> viewManagers;
	
	private Integer puntos = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlightControl game = new FlightControl();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FlightControl() {
			initialize();
	}

	public void initialize() {
		this.viewManagers = new ArrayList<ViewManager>();
		viewManagers.add(new VistaVisual(this));

	}

	public void setUpGame(){
		puntos =0;
		if(this.gameLoop != null){
			gameLoop.detenerEjecucion();
			gameLoop.removeAll();
			System.out.println(gameLoop.getCantidadDeObjetosVivos());
		}
		this.gameLoop = new GameLoop(100);
		clean();
		List<Pista> pistas = new ArrayList<Pista>();
		Pista p = new PistaSimple(new Posicion(40, 50), 10,Math.PI/2);
		Pista p2 = new PistaLarga(new Posicion(160, 70), 10,Math.PI/6);
		Pista p3 = new PistaHelipuerto(new Posicion(40, 578), 10,0d);
		pistas.add(p);
		pistas.add(p2);
		pistas.add(p3);
		List<FactoryAvion> factories = new ArrayList<FactoryAvion>();
		FactoryAvion fl = new FactoryAvionLiviano();
		FactoryAvion fp = new FactoryAvionPesado();
		FactoryAvion fh = new FactoryAvionHelicoptero();
		factories.add(fl);
		factories.add(fp);
		factories.add(fh);

		this.game = new Escenario(15, pistas, factories, this);
		this.gameLoop.agregar(game);
		VistaPista vp = new VistaPista(p);
		VistaPista vp2 = new VistaPista(p2);
		VistaPista vp3 = new VistaPista(p3);
		for (ViewManager manager : viewManagers) {
			manager.addVistaPista(vp);
			manager.addVistaPista(vp2);
			manager.addVistaPista(vp3);
			manager.showPuntaje(puntos);
		}
	}
	
	public void addAvion(Avion a) {
		this.gameLoop.agregar(a);
		VistaAvion va = new VistaAvion(a);
		for (ViewManager manager : viewManagers) {
			manager.addVistaAvion(va);
		}
	}

	public void removerAvion(Avion a) {
		puntos++;
		this.gameLoop.remover(a);
		VistaAvion va = new VistaAvion(a);
		for (ViewManager manager : viewManagers) {
			manager.removerVistaAvion(va);
			manager.showPuntaje(puntos);
		}
	}

	public GameLoop getGameLoop() {
		return gameLoop;
	}

	public List<ViewManager> getViewManagers() {
		return viewManagers;
	}

	public void huboUnClick(Integer x, Integer y) {
		this.game.huboUnClick(x, y);
	}
	
	public void finalizarJuego(){
		this.gameLoop.detenerEjecucion();
		for(ViewManager manager:viewManagers){
			manager.detenerEjecucion();
			manager.showPerdido();
		}
		
	}
	
	public void clean(){
		for(ViewManager manager:viewManagers){
			manager.detenerEjecucion();
			manager.removeAll();
		}
	}


}
