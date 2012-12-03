package modelo.juego;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;

import modelo.aviones.Avion;
import modelo.exceptions.FalloEnFabricacionAvionException;
import modelo.factories.FactoryAvion;
import modelo.general.Mapa;
import modelo.general.Posicion;
import modelo.pistas.Pista;
import vista.FlightControl;
import vista.ViewManager;
import fiuba.algo3.titiritero.modelo.ObjetoVivo;

public class Escenario implements ObjetoVivo {

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
	
}
