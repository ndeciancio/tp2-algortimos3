package modelo.factories;

import java.util.ArrayList;
import java.util.List;

import modelo.aviones.Avion;
import modelo.aviones.AvionPesado;
import modelo.general.Mapa;
import modelo.general.Posicion;
import modelo.general.Trayectoria;

public class FactoryAvionPesadoSimpleSobreBordeX extends FactoryAvion {

	@Override
	public Avion fabricarAvion(Mapa mapa) {
		Double direccion = 0d;
		Integer velocidad = 1;
		Integer radio = 1;
		Posicion posicionInicial = Posicion.crearPosicionSobreBordeX(mapa.getBordeX(),mapa.getBordeY());
		Posicion primerMovimiento = Posicion.crearPrimerDestinoSobreBordeX(posicionInicial);
		List <Posicion> listaPrimerMovimiento = new ArrayList <Posicion> ();
		listaPrimerMovimiento.add(primerMovimiento);
		Trayectoria trayectoriaAvionNuevo = new Trayectoria (listaPrimerMovimiento);
		Avion avionPesadoSimple = AvionPesado.crearAvionPesadoSimple(posicionInicial, radio, velocidad, direccion);
		avionPesadoSimple.setTrayectoria(trayectoriaAvionNuevo);
		return avionPesadoSimple;
	}

}
