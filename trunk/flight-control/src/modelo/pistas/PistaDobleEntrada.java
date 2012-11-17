package modelo.pistas;

import modelo.aviones.AvionLiviano;
import modelo.aviones.AvionHelicoptero;
import modelo.aviones.AvionPesado;
import modelo.general.Posicion;

public class PistaDobleEntrada extends Pista {

	private Posicion posicion2;
	private Integer radio2;
	
	public PistaDobleEntrada(Posicion posicion,Posicion posicion2, Integer radio, Integer radio2) {
		super(posicion,radio);
		this.posicion2 = posicion2;
		this.radio2 = radio2;
	}

	@Override
	public Boolean puedeAterrizar(AvionLiviano avion) {
		if(getPosicion().estaCercaDe(avion.getPosicion(), avion.getRadio()+this.getRadio())){
			return true;
		}else if(getPosicion2().estaCercaDe(avion.getPosicion(), avion.getRadio()+this.getRadio2())){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Boolean puedeAterrizar(AvionPesado avion) {
		return false;
	}
	
	public Boolean puedeAterrizar(AvionHelicoptero avion) {
		return false;
	}

	public Posicion getPosicion2() {
		return posicion2;
	}

	public Integer getRadio2() {
		return radio2;
	}

}
