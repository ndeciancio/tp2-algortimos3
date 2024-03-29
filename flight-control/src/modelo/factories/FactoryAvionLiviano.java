package modelo.factories;

import java.util.Random;

import org.jdom.Element;

import modelo.aviones.Avion;
import modelo.aviones.AvionLiviano;
import modelo.general.Mapa;

import modelo.general.Posicion;
import modelo.juego.Escenario;

public class FactoryAvionLiviano extends FactoryAvion {

	private static Random r = new Random();
	
	public FactoryAvionLiviano(){
		super();
	}
	@Override
	public Avion fabricarAvion(Mapa mapa, Escenario escenario) {
		Integer velocidad = 5;
		Integer radio = 15;
		Posicion posicionInicial = Posicion.crearPosicionSobreBorde(mapa
				.getBordeX(), mapa.getBordeY());
		Double direccion = Posicion.crearPrimerDireccion(
				posicionInicial, mapa.getBordeX(), mapa.getBordeY());
		Integer x = r.nextInt(2000);
		Avion avionLigeroSimple = null;
		if (x > 500) {
			avionLigeroSimple = AvionLiviano.crearAvionLivianoSimple(
					posicionInicial, radio, velocidad, direccion, escenario);
		} else {
			avionLigeroSimple = AvionLiviano.crearAvionLivianoInteligente(
					posicionInicial, radio, velocidad, direccion, escenario);
		}
		return avionLigeroSimple;
	}

	public Element serializarXML() {
		
		return new Element ("FactoryAvionLiviano");
	}

}
