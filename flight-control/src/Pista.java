
public abstract class Pista {

	private Posicion posicion;
	private Integer radio;
	
	public Pista(Posicion posicion, Integer radio){
		this.posicion = posicion;
		this.radio = radio;
	}

	public Posicion getPosicion() {
		return posicion;
	}
	
	public Boolean puedeAterrizar(Avion a){
		return false;
	}
	
	public Boolean puedeAterrizar(AvionLiviano a){
		return false;
	}

	public Integer getRadio() {
		return radio;
	}
	

}
