
public abstract class Avion {
	
	protected Posicion posicion;
	private Pista pista;
	private Trayectoria trayectoria;
	private Boolean aterrizado;
	
	public Avion(Posicion posicion, Pista pista){
		this.posicion = posicion;
		this.pista = pista;
	}
	
	public abstract void avanzar();

	public Boolean chequearAterrizaje(){
		aterrizado = true;
		return true; //TODO HARDCODED!!!!!
	}
	
	public Posicion getPosicion() {
		return posicion;
	}

	public Pista getPista() {
		return pista;
	}

	public void setTrayectoria(Trayectoria trayectoria) {
		this.trayectoria = trayectoria;
	}

	public Trayectoria getTrayectoria() {
		return trayectoria;
	}

	public Boolean estaAterrizado() {
		return aterrizado;
	}
}
