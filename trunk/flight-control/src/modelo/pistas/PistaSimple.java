package modelo.pistas;

import org.jdom.Element;

import modelo.aviones.AvionHelicoptero;
import modelo.aviones.AvionLiviano;
import modelo.aviones.AvionPesado;
import modelo.general.Posicion;


public class PistaSimple extends Pista {
	
	public PistaSimple(Posicion posicion, Integer radio, Double direccion){
		super(posicion, radio,direccion);
	}
	
	public Boolean puedeAterrizar(AvionLiviano avion){
		if(getPosicion().estaCercaDe(avion.getPosicion(), this.getRadio())){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Boolean puedeAterrizar(AvionPesado avion) {
		return false;
	}
	
	@Override
	public Boolean puedeAterrizar(AvionHelicoptero avion) {
		return false;
	}

	@Override
	public Boolean esLaPista(AvionLiviano avion) {
		return true;
	}

	@Override
	public Boolean esLaPista(AvionPesado avion) {
		return false;
	}

	@Override
	public Boolean esLaPista(AvionHelicoptero avion) {
		return false;
	}
	
	public Element serializarXML() {
		Element pistaSimpleSerializada = new Element ("PistaSimple");
		this.cargarElemento(pistaSimpleSerializada);
		return pistaSimpleSerializada;
	}
	
	public static PistaSimple cargarDesdeXML(Element elementoXML) {
		Posicion posicionDelXML = Posicion.cargarDesdeXML(elementoXML.getChild("Posicion"));
		Integer radioDelXML = Integer.parseInt(elementoXML.getAttributeValue("radio1"));
		return new PistaSimple (posicionDelXML, radioDelXML,null);
		
	}
	
}
