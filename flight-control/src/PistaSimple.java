
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
	
}
