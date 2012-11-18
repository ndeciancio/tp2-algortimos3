package modelo.factories;

import modelo.aviones.Avion;

public abstract class FactoryAvion {

	public FactoryAvion(){
		
	}
	
	public abstract Avion fabricarAvion();
}
