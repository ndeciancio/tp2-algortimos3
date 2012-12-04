package modelo.factories;

import org.jdom.Element;

import modelo.aviones.Avion;
import modelo.aviones.AvionHelicoptero;
import modelo.general.Mapa;

import modelo.general.Posicion;
import modelo.juego.Escenario;

public class FactoryAvionHelicoptero extends FactoryAvion{


	public FactoryAvionHelicoptero(){
		super();
	}
	public Avion fabricarAvion(Mapa mapa, Escenario escenario) {
		Double direccion = 0d;
		Integer velocidad = 5;
		Integer radio = 15;
		Posicion posicionInicial = Posicion.crearPosicionSobreBorde(mapa
				.getBordeX(), mapa.getBordeY());
		Posicion primerMovimiento = Posicion.crearPrimerDestino(mapa.getBordeX(), mapa.getBordeY());
		Avion avionHelicoptero = AvionHelicoptero.crearAvionHelicopteroSimple(
				posicionInicial, radio, velocidad, direccion, escenario);
		avionHelicoptero.agregarPosicionATrayectoria(primerMovimiento);
		return avionHelicoptero;
	}

	@Override
	public Element serializarXML() {
		
		return new Element ("FactoryAvionHelicoptero");
	}


	

}
