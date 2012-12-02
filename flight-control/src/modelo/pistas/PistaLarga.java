package modelo.pistas;

import org.jdom.Element;

import modelo.aviones.AvionHelicoptero;
import modelo.aviones.AvionLiviano;
import modelo.aviones.AvionPesado;
import modelo.general.Posicion;

public class PistaLarga extends Pista {

	public PistaLarga(Posicion posicion, Integer radio) {
		super(posicion, radio);
	}

	@Override
	public Boolean puedeAterrizar(AvionLiviano a) {
		return false;
	}
	
	@Override
	public Boolean puedeAterrizar(AvionHelicoptero a) {
		return false;
	}
	
	@Override
	public Boolean puedeAterrizar(AvionPesado avion){
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
		return true;
	}

	@Override
	public Boolean esLaPista(AvionHelicoptero avion) {
		return false;
	}

	@Override
	public Element serializarXML() {
		// TODO Auto-generated method stub
		return null;
	}

}
