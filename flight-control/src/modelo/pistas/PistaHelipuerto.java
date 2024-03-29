package modelo.pistas;


import org.jdom.Element;

import modelo.aviones.AvionHelicoptero;
import modelo.aviones.AvionLiviano;
import modelo.aviones.AvionPesado;
import modelo.general.Posicion;

public class PistaHelipuerto extends Pista {

	public PistaHelipuerto(Posicion posicion, Integer radio, Double direccion) {
		super(posicion, radio,direccion);

	}

	@Override
	public Boolean puedeAterrizar(AvionLiviano a) {
		return false;
	}

	@Override
	public Boolean puedeAterrizar(AvionPesado avion) {
		return false;
	}

	@Override
	public Boolean puedeAterrizar(AvionHelicoptero avion) {
		if(getPosicion().estaCercaDe(avion.getPosicion(), avion.getRadio()+this.getRadio())){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Boolean esLaPista(AvionLiviano avion) {
		return false;
	}

	@Override
	public Boolean esLaPista(AvionPesado avion) {
		return false;
	}

	@Override
	public Boolean esLaPista(AvionHelicoptero avion) {
		return true;
	}

	public Element serializarXML() {
		Element pistaHelipuertoSerializada = new Element ("PistaHelipuerto");
		this.cargarElemento(pistaHelipuertoSerializada);
		return pistaHelipuertoSerializada;
	}
	
	public static PistaHelipuerto cargarDesdeXML(Element elementoXML) {
		
		Double direccionXML = Double.parseDouble(elementoXML.getAttributeValue("direccion"));
		Posicion posicionDelXML = Posicion.cargarDesdeXML(elementoXML.getChild("Posicion"));
		Integer radioDelXML = Integer.parseInt(elementoXML.getAttributeValue("radio1"));
		return new PistaHelipuerto (posicionDelXML, radioDelXML,direccionXML);
		
	}

}
