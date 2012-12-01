package modelo.juego;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import modelo.aviones.Avion;
import modelo.exceptions.FalloEnFabricacionAvionException;
import modelo.factories.FactoryAvion;
import modelo.general.Mapa;
import modelo.general.Posicion;
import modelo.pistas.Pista;
import vista.ViewManager;
import fiuba.algo3.titiritero.modelo.ObjetoVivo;

public class Juego implements ObjetoVivo {

	private Mapa mapaDeJuego = Mapa.getInstance();
	private Integer cantidadMaximaAvionesPorNivel;
	private Integer interverloDeCreacionAviones = 30;
	private Integer turnosParaCreacionSiguienteAvion = 30;
	private List<FactoryAvion> fabricasDeAviones;

	private Boolean perdido = false;
	private List<ViewManager> viewManagers;
	
	private Integer cantAvionesAterrizados = 0;
	
	private static Random fabricaRandom = new Random(444);
	
	
	public Juego(Integer cantidadAvionesMaximaPorNivel,
			List<Pista> pistasDelMapa, List<FactoryAvion> fabricas,
			List<ViewManager> managers) {
		cantidadMaximaAvionesPorNivel = cantidadAvionesMaximaPorNivel;
		agregarPistasAlMapa(pistasDelMapa);
		this.fabricasDeAviones = fabricas;
		this.viewManagers = managers;
	}

	private void agregarPistasAlMapa(List<Pista> pistasDelMapa) {
		Iterator<Pista> iteradorPistas = pistasDelMapa.iterator();

		while (iteradorPistas.hasNext()) {
			mapaDeJuego.addPista(iteradorPistas.next());
		}
	}

	public void vivir() {

		crearAvion();
		aumentarDificultad();
		retirarAvionDelJuego();

	}

	private void crearAvion() {
		// aca no se bien como funciona el random, pero supongo que se le pasa
		// por
		// parametro el tope

		if (this.fabricasDeAviones.size() == 0) {
			FalloEnFabricacionAvionException falloEnFabricacion = new FalloEnFabricacionAvionException(
					"Fallo En La Fabricacion De Un Avion");
			throw falloEnFabricacion;

		}

		
		Integer fabricaElegida = fabricaRandom
				.nextInt(fabricasDeAviones.size());
		if (turnosParaCreacionSiguienteAvion >= interverloDeCreacionAviones) {
			turnosParaCreacionSiguienteAvion = 0;
			Avion avionNuevo = this.fabricasDeAviones.get(fabricaElegida)
					.fabricarAvion(this.mapaDeJuego, this);
			mapaDeJuego.addAvion(avionNuevo);
			for (ViewManager manager : viewManagers) {
				manager.addAvion(avionNuevo);
			}
		}

		turnosParaCreacionSiguienteAvion++;

	}

	private void aumentarDificultad() {
		if (this.verificarPasarDeNivel()) {
			interverloDeCreacionAviones -= 5;
		}
		// habria que haber algo que indique que se aumento la dificultad
	}

	private boolean verificarPasarDeNivel() {
		// alguna forma de contar los aviones aterrizados antes de sacarlos del
		// mapa
		return false;
	}

	private void retirarAvionDelJuego() {

		mapaDeJuego.sacarAvionesAterrizados();

	}

	public void huboUnChoque() {
		this.perdido = true;
	}

	public void huboUnClick(Integer x, Integer y){
		if(!Mapa.getInstance().seleccionarAvion(new Posicion(x,y))){
			Mapa.getInstance().agregarPosicionAlAvionSeleccionado(new Posicion(x,y));
		}
	}
	
	public void aterrizo(Avion a){
		cantAvionesAterrizados++;
		for(ViewManager manager: viewManagers){
			manager.removerAvion(a);
		}
	}
}
