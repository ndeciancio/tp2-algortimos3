package modelo.juego;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import modelo.aviones.Avion;
import modelo.exceptions.FalloEnFabricacionAvionException;
import modelo.factories.FactoryAvion;
import modelo.general.Mapa;
import modelo.general.ObjetoSerializableXML;
import modelo.general.Posicion;
import modelo.pistas.Pista;

import org.jdom.Attribute;
import org.jdom.Element;

import vista.FlightControl;
import fiuba.algo3.titiritero.modelo.ObjetoVivo;

public class Escenario implements ObjetoVivo, ObjetoSerializableXML {

	private Mapa mapaDeJuego = Mapa.getInstance();
	private Integer cantidadMaximaAvionesPorNivel;
	private Integer intervaloDeCreacionAviones = 30;
	private Integer turnosParaCreacionSiguienteAvion = 30;
	
	private List<FactoryAvion> fabricasDeAviones;

	private Boolean perdido = false;
	private FlightControl flightControl;

	private Integer cantAvionesAterrizados = 0;

	private static Random fabricaRandom = new Random();

	public Escenario(Integer cantidadAvionesMaximaPorNivel,
			List<Pista> pistasDelMapa, List<FactoryAvion> fabricas,
			FlightControl flightControl) {
		Mapa.getInstance().resetMapa();
		cantidadMaximaAvionesPorNivel = cantidadAvionesMaximaPorNivel;
		agregarPistasAlMapa(pistasDelMapa);
		this.fabricasDeAviones = fabricas;
		this.flightControl = flightControl;
	}

	private void agregarPistasAlMapa(List<Pista> pistasDelMapa) {
		Iterator<Pista> iteradorPistas = pistasDelMapa.iterator();

		while (iteradorPistas.hasNext()) {
			mapaDeJuego.addPista(iteradorPistas.next());
		}
	}

	public void vivir() {
		crearAvion();
	}

	private void crearAvion() {
		// aca no se bien como funciona el random, pero supongo que se le pasa
		// por
		// parametro el tope
		if(cantAvionesAterrizados > cantidadMaximaAvionesPorNivel){
			levelUp();
		}
		if (this.fabricasDeAviones.size() == 0) {
			FalloEnFabricacionAvionException falloEnFabricacion = new FalloEnFabricacionAvionException(
					"Fallo En La Fabricacion De Un Avion");
			throw falloEnFabricacion;

		}

		Integer fabricaElegida = fabricaRandom
				.nextInt(fabricasDeAviones.size());
		if (turnosParaCreacionSiguienteAvion >= intervaloDeCreacionAviones) {
			turnosParaCreacionSiguienteAvion = 0;
			Avion avionNuevo = this.fabricasDeAviones.get(fabricaElegida)
					.fabricarAvion(this.mapaDeJuego, this);
			mapaDeJuego.addAvion(avionNuevo);
			flightControl.addAvion(avionNuevo);
		}

		turnosParaCreacionSiguienteAvion++;

	}


	public void huboUnChoque() {
		this.perdido = true;
		flightControl.finalizarJuego();
	}

	public void huboUnClick(Integer x, Integer y) {
		if (!Mapa.getInstance().seleccionarAvion(new Posicion(x, y))) {
			Mapa.getInstance().agregarPosicionAlAvionSeleccionado(
					new Posicion(x, y));
		}
	}

	public void aterrizo(Avion a) {
		cantAvionesAterrizados++;
		Mapa.getInstance().removerAvion(a);
		flightControl.removerAvion(a);
	}
	
	public void levelUp(){
		Long l = Math.round(intervaloDeCreacionAviones * 0.8);
		intervaloDeCreacionAviones= l.intValue();
		cantAvionesAterrizados =0;
		flightControl.levelUp();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Element serializarXML() {
		Element juegoSerializado = new Element ("Juego");
		Attribute cantidadMaximaAvionesPorNivel = new Attribute (
									"cantidadMaximaAvionesPorNivel",
									this.cantidadMaximaAvionesPorNivel.toString());
		Attribute intervaloDeCreacionAviones = new Attribute (
									"intervaloDeCreacionAviones",
									this.intervaloDeCreacionAviones.toString());	
		Attribute turnosParaCreacionSiguienteAvion = new Attribute (
									"turnosParaCreacionSiguienteAvion",
									this.turnosParaCreacionSiguienteAvion.toString());
		Attribute cantAvionesAterrizados = new Attribute (
									"cantAvionesAterrizados",
									this.cantAvionesAterrizados.toString());
		Element fabricasDeAviones = new Element ("fabricasDeAviones");
		Iterator<FactoryAvion> iteradorFabrica = this.fabricasDeAviones.iterator();
		while (iteradorFabrica.hasNext()){
			fabricasDeAviones.getChildren().add(iteradorFabrica.next().serializarXML());
		}
		
		Element mapa = this.mapaDeJuego.serializarXML();
		
		juegoSerializado.setAttribute(cantAvionesAterrizados);
		juegoSerializado.setAttribute(turnosParaCreacionSiguienteAvion);
		juegoSerializado.setAttribute(intervaloDeCreacionAviones);
		juegoSerializado.setAttribute(cantidadMaximaAvionesPorNivel);
		juegoSerializado.getChildren().add(mapa);
		juegoSerializado.getChildren().add(fabricasDeAviones);
		
		return juegoSerializado;
	}

	public void cargarAtributosDesdeXML(Element elementoXML) {
		this.cantAvionesAterrizados = Integer.parseInt(elementoXML.getAttributeValue("cantAvionesAterrizados"));
		this.turnosParaCreacionSiguienteAvion = Integer.parseInt(elementoXML.getAttributeValue("turnosParaCreacionSiguienteAvion"));
		this.intervaloDeCreacionAviones = Integer.parseInt(elementoXML.getAttributeValue("intervaloDeCreacionAviones"));
		
		Element mapa = elementoXML.getChild("Mapa");
		Element aviones = mapa.getChild("Aviones");
		Iterator<Element> iteradorAviones = aviones.getChildren().iterator(); 
		while (iteradorAviones.hasNext()){
			Avion avionXML = Avion.cargarDesdeXML(iteradorAviones.next(), this);
			mapaDeJuego.addAvion(avionXML);

		}
		
	}

	
}
