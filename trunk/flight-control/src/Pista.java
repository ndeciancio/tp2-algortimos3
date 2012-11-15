
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
	
	
	public abstract Boolean puedeAterrizar(AvionLiviano a);

	public Integer getRadio() {
		return radio;
	}
	

}