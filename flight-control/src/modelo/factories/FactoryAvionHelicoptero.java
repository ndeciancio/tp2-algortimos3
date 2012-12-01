package modelo.factories;

import modelo.aviones.Avion;
import modelo.aviones.AvionHelicoptero;
import modelo.general.Mapa;
import modelo.general.Posicion;
import modelo.juego.Juego;

public class FactoryAvionHelicoptero implements FactoryAvion {

	@Override
	public Avion fabricarAvion(Mapa mapa, Juego juego) {
		Double direccion = 0d;
		Integer velocidad = 5;
		Integer radio = 15;
		Posicion posicionInicial = Posicion.crearPosicionSobreBorde(mapa
				.getBordeX(), mapa.getBordeY());
		Posicion primerMovimiento = Posicion.crearPrimerDestino(mapa.getBordeX(), mapa.getBordeY());
		Avion avionHelicoptero = AvionHelicoptero.crearAvionHelicopteroSimple(
				posicionInicial, radio, velocidad, direccion, juego);
		avionHelicoptero.agregarPosicionATrayectoria(primerMovimiento);
		return avionHelicoptero;
	}

}
