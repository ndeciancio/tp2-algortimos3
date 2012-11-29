package modelo.factories;

import java.util.ArrayList;
import java.util.List;

import modelo.aviones.Avion;
import modelo.aviones.AvionLiviano;
import modelo.general.Mapa;
import modelo.general.Posicion;
import modelo.general.Trayectoria;

public class FactoryAvionLigeroSimpleSobreBordeY extends FactoryAvion {

	@Override
	public Avion fabricarAvion(Mapa mapa) {
		Double direccion = 0d;
		Integer velocidad = 1;
		Integer radio = 1;
		Posicion posicionInicial = Posicion.crearPosicionSobreBordeY(mapa.getBordeX(),mapa.getBordeY());
		Posicion primerMovimiento = Posicion.crearPrimerDestinoSobreBordeY(posicionInicial);
		List <Posicion> listaPrimerMovimiento = new ArrayList <Posicion> ();
		listaPrimerMovimiento.add(primerMovimiento);
		Trayectoria trayectoriaAvionNuevo = new Trayectoria (listaPrimerMovimiento);
		Avion avionLigeroSimple = AvionLiviano.crearAvionLivianoSimple(posicionInicial, radio, velocidad, direccion);
		avionLigeroSimple.setTrayectoria(trayectoriaAvionNuevo);
		
		return avionLigeroSimple;

	}

}
