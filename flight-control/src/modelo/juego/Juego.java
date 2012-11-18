package modelo.juego;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import modelo.aviones.Avion;
import modelo.factories.FactoryAvion;
import modelo.general.Mapa;
import modelo.pistas.Pista;



public class Juego {

	private Mapa mapaDeJuego = Mapa.getInstance();
	private Integer cantidadMaximaAvionesPorNivel;
	private Integer interverloDeCreacionAviones = 30;
	private Integer turnosParaCreacionSiguienteAvion = 15;
	private List <FactoryAvion> fabricasDeAviones = new ArrayList <FactoryAvion> ();

	

	
	public Juego (Integer cantidadAviones, List <Pista> pistasDelMapa, List <FactoryAvion> fabricas)
	{
		cantidadMaximaAvionesPorNivel = cantidadAviones;
		agregarPistasAlMapa(pistasDelMapa);
		agregarFabricas(fabricas);
	}
	
	
	private void agregarPistasAlMapa(List<Pista> pistasDelMapa) {
		Iterator<Pista> iteradorPistas = pistasDelMapa.iterator(); 
		
		while (iteradorPistas.hasNext())
		{
			mapaDeJuego.addPista(iteradorPistas.next());
		}
	}	

	private void agregarFabricas(List<FactoryAvion> fabricas){
		Iterator<FactoryAvion> iteradorFabricas = fabricas.iterator();
		
		while (iteradorFabricas.hasNext()){
			fabricasDeAviones.add(iteradorFabricas.next());
		}
		
	}
	
	public void vivir(){

		crearAvion();
		aumentarDificultad();
		retirarAvionDelJuego();
		
	}


	private void crearAvion() {
		//aca no se bien como funciona el random, pero supongo que se le pasa por
		//parametro el tope
		
		Random fabricaRandom = new Random (fabricasDeAviones.size());
		Integer fabricaElegida = fabricaRandom.nextInt();
				
		if (turnosParaCreacionSiguienteAvion >= interverloDeCreacionAviones){
			turnosParaCreacionSiguienteAvion = 0;
			Avion avionNuevo = this.fabricasDeAviones.get(fabricaElegida).fabricarAvion();
			mapaDeJuego.addAvion(avionNuevo);
		}
		
		turnosParaCreacionSiguienteAvion++;
		
	}

	private void aumentarDificultad() {
		if (this.verificarPasarDeNivel()){
			this.interverloDeCreacionAviones -= 5;
		}
		//habria que haber algo que indique que se aumento la dificultad
	}




	private boolean verificarPasarDeNivel() {
		//alguna forma de contar los aviones aterrizados antes de sacarlos del mapa
		return false;
	}


	private void retirarAvionDelJuego() {
		
		mapaDeJuego.sacarAvionesAterrizados();
		
	}
}
