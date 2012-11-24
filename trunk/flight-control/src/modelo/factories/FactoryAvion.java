package modelo.factories;

import modelo.aviones.Avion;
import modelo.general.Mapa;

public abstract class FactoryAvion {

	public FactoryAvion(){
		
	}
	
	public abstract Avion fabricarAvion(Mapa mapa);
}
