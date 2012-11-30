package modelo.factories;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.aviones.Avion;
import modelo.aviones.AvionLiviano;
import modelo.general.Mapa;
import modelo.general.Posicion;
import modelo.general.Trayectoria;
import modelo.juego.Juego;

public class FactoryAvionLiviano implements FactoryAvion {

	private static Random r = new Random(1024);
	
	@Override
	public Avion fabricarAvion(Mapa mapa, Juego juego) {
		Double direccion = 0d;
		Integer velocidad = 10;
		Integer radio = 15;
		Posicion posicionInicial = Posicion.crearPosicionSobreBorde(mapa
				.getBordeX(), mapa.getBordeY());
		Posicion primerMovimiento = Posicion.crearPrimerDestino(mapa
				.getBordeX(), mapa.getBordeY());
		List<Posicion> listaPrimerMovimiento = new ArrayList<Posicion>();
		listaPrimerMovimiento.add(primerMovimiento);
		Trayectoria trayectoriaAvionNuevo = new Trayectoria(
				listaPrimerMovimiento);
		
		Integer x = 0;
		r.nextInt(2000);
		Avion avionLigeroSimple =null;
		if(x>1000){
			avionLigeroSimple = AvionLiviano.crearAvionLivianoSimple(
					posicionInicial, radio, velocidad, direccion, juego);
		}else{
			avionLigeroSimple = AvionLiviano.crearAvionLivianoSimple(
					posicionInicial, radio, velocidad, direccion, juego);
		}
		avionLigeroSimple.setTrayectoria(trayectoriaAvionNuevo);
		return avionLigeroSimple;
	}

}
