package modelo.factories;

import modelo.aviones.Avion;
import modelo.aviones.AvionLiviano;
import modelo.general.Mapa;
import modelo.general.Posicion;

public class FactoryAvionLigeroSimpleSobreBordeX extends FactoryAvion {

	@Override
	public Avion fabricarAvion(Mapa mapa) {
		Double direccion = 0d;
		Integer velocidad = 2;
		Integer radio = 2;
		Posicion posicion = Posicion.crearPosicionSobreBordeX(mapa.getBordeX(),mapa.getBordeY());
		
		Avion avionLigeroSimple = AvionLiviano.crearAvionLivianoSimple(posicion, radio, velocidad, direccion);
		return avionLigeroSimple;
	}

}
