package modelo.factories;

import modelo.aviones.Avion;
import modelo.general.Mapa;
import modelo.juego.Escenario;

public abstract interface FactoryAvion {
	
	public abstract Avion fabricarAvion(Mapa mapa, Escenario escenario);
}
