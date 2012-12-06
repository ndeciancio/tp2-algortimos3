package vista;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Element;

import modelo.aviones.Avion;
import modelo.factories.FactoryAvion;
import modelo.factories.FactoryAvionHelicoptero;
import modelo.factories.FactoryAvionLiviano;
import modelo.factories.FactoryAvionPesado;
import modelo.general.ObjetoSerializableXML;
import modelo.general.Posicion;
import modelo.juego.Escenario;
import modelo.pistas.Pista;
import modelo.pistas.PistaDobleEntrada;
import modelo.pistas.PistaHelipuerto;
import modelo.pistas.PistaLarga;
import modelo.pistas.PistaSimple;
import fiuba.algo3.titiritero.modelo.GameLoop;

public class FlightControl {

	private Escenario game;
	private GameLoop gameLoop;
	private List<ViewManager> viewManagers;
	
	private Integer puntos = 0;
	private Integer level = 1;

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
		level = 1;
		if(this.gameLoop != null){
			gameLoop.detenerEjecucion();
			gameLoop.removeAll();
		}
		this.gameLoop = new GameLoop(100);
		clean();
		List<Pista> pistas = new ArrayList<Pista>();
		Pista p = new PistaSimple(new Posicion(40, 50), 10,Math.PI/2);
		Pista p2 = new PistaLarga(new Posicion(160, 70), 10,Math.PI/6);
		Pista p3 = new PistaHelipuerto(new Posicion(40, 578), 10,0d);
		Pista p4 = new PistaDobleEntrada(new Posicion(200,300), new Posicion(350,300), 10, 10, 0d);
		pistas.add(p);
		pistas.add(p2);
		pistas.add(p3);
		pistas.add(p4);
		List<FactoryAvion> factories = new ArrayList<FactoryAvion>();
		FactoryAvion fl = new FactoryAvionLiviano();
		FactoryAvion fp = new FactoryAvionPesado();
		FactoryAvion fh = new FactoryAvionHelicoptero();
		factories.add(fl);
		factories.add(fp);
		factories.add(fh);

		this.game = new Escenario(10, pistas, factories, this);
		this.gameLoop.agregar(game);
		VistaPista vp = new VistaPista(p);
		VistaPista vp2 = new VistaPista(p2);
		VistaPista vp3 = new VistaPista(p3);
		VistaPista vp4 = new VistaPista(p4);
		for (ViewManager manager : viewManagers) {
			manager.addVistaPista(vp);
			manager.addVistaPista(vp2);
			manager.addVistaPista(vp3);
			manager.addVistaPista(vp4);
			manager.showPuntaje(puntos);
			manager.showLevelUp(level);
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
	
	public void levelUp(){
		level++;
		for(ViewManager manager:viewManagers){
			manager.showLevelUp(level);
		}
	}

	public Escenario getEscenario() {
		
		return this.game;
	}



	@SuppressWarnings("unchecked")
	public Element serializarXML() {
		Element flightControl = new Element ("FlightControl");
		Attribute puntos = new Attribute ("puntos",this.puntos.toString());
		Attribute level = new Attribute ("level",this.level.toString());
		flightControl.setAttribute(puntos);
		flightControl.setAttribute(level);	
		flightControl.getChildren().add(this.game.serializarXML());
		return flightControl;
	}

	public void setUpGameDesdeXML(Element elementoRaiz) {

		this.level = Integer.parseInt(elementoRaiz.getAttributeValue("level"));
		this.puntos = Integer.parseInt(elementoRaiz.getAttributeValue("puntos"));
		
		if(this.gameLoop != null){
			gameLoop.detenerEjecucion();
			gameLoop.removeAll();
		}
		this.gameLoop = new GameLoop(100);
		clean();
		
		Element juegoXML = elementoRaiz.getChild("Juego");
		Element pistasXML = juegoXML.getChild("Mapa").getChild("Pistas");
		Element factoriesXML = juegoXML.getChild("fabricasDeAviones");
		
		Iterator<Element> iteradorPistas = pistasXML.getChildren().iterator();
		Iterator<Element> iteradorFactories = factoriesXML.getChildren().iterator();
		
		List<Pista> pistas = new ArrayList<Pista>();
		List<VistaPista> vistasPistas = new ArrayList<VistaPista>();
		List<FactoryAvion> factories = new ArrayList<FactoryAvion>();
		
		while (iteradorPistas.hasNext()){
			Element pistaElemento = iteradorPistas.next();
			Pista pistaXML =Pista.cargarDesdeXML(pistaElemento);
			pistas.add(pistaXML);
			VistaPista vp = new VistaPista(pistaXML); 
			vistasPistas.add(vp);
		}

		while (iteradorFactories.hasNext()){
			Element fabricaElemento = iteradorFactories.next();
			factories.add(FactoryAvion.crearDesdeXML(fabricaElemento));
		}
		
		this.game = new Escenario (10, pistas, factories, this);
		this.gameLoop.agregar(game);
		

		for (ViewManager manager : viewManagers) {
			manager.addVistaPista(vistasPistas.get(0));
			manager.addVistaPista(vistasPistas.get(1));
			manager.addVistaPista(vistasPistas.get(2));
			manager.addVistaPista(vistasPistas.get(3));
			manager.showPuntaje(puntos);
			manager.showLevelUp(level);
			}
				
		}



}
