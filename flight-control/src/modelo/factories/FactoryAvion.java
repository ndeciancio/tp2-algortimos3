package modelo.factories;

import org.jdom.Element;

import modelo.aviones.Avion;
import modelo.general.Mapa;
import modelo.juego.Escenario;

public abstract class FactoryAvion {
	public FactoryAvion(){
		
	}
	public abstract Avion fabricarAvion(Mapa mapa, Escenario escenario);

	public abstract Element serializarXML();
	
	public static FactoryAvion crearDesdeXML(Element elementoXML){
		String nombreFabrica = elementoXML.getName();
		if (nombreFabrica.equals("FactoryAvionHelicoptero")){
			return new FactoryAvionHelicoptero();
		}
		if (nombreFabrica.equals("FactoryAvionLiviano")){
			return new FactoryAvionLiviano();
		}
		if (nombreFabrica.equals("FactoryAvionPesado")){
			return new FactoryAvionPesado();
		}
		
		return null;
		
	}
}
