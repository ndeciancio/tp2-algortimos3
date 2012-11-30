package modelo.pistas;

import modelo.aviones.AvionHelicoptero;
import modelo.aviones.AvionLiviano;
import modelo.aviones.AvionPesado;
import modelo.general.Posicion;

public class PistaSimple extends Pista {
	
	public PistaSimple(Posicion posicion, Integer radio){
		super(posicion, radio);
	}
	
	public Boolean puedeAterrizar(AvionLiviano avion){
		if(getPosicion().estaCercaDe(avion.getPosicion(), avion.getRadio()+this.getRadio())){
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
	
}
