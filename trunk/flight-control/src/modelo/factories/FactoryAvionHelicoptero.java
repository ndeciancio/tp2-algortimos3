package modelo.factories;

import java.util.ArrayList;
import java.util.List;

import modelo.aviones.Avion;
import modelo.aviones.AvionHelicoptero;
import modelo.general.Mapa;
import modelo.general.Posicion;
import modelo.general.Trayectoria;
import modelo.juego.Juego;

public class FactoryAvionHelicoptero implements FactoryAvion {

	@Override
	public Avion fabricarAvion(Mapa mapa, Juego juego) {
		Double direccion = 0d;
		Integer velocidad = 2;
		Integer radio = 15;
		Posicion posicionInicial = Posicion.crearPosicionSobreBorde(mapa
				.getBordeX(), mapa.getBordeY());
		Posicion primerMovimiento = Posicion.crearPrimerDestino(mapa
				.getBordeX(), mapa.getBordeY());
		List<Posicion> listaPrimerMovimiento = new ArrayList<Posicion>();
		listaPrimerMovimiento.add(primerMovimiento);
		Trayectoria trayectoriaAvionNuevo = new Trayectoria(
				listaPrimerMovimiento);
		Avion avionHelicoptero = AvionHelicoptero.crearAvionHelicopteroSimple(
				posicionInicial, radio, velocidad, direccion, juego);
		avionHelicoptero.setTrayectoria(trayectoriaAvionNuevo);
		return avionHelicoptero;
	}

}
