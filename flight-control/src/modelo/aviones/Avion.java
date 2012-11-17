package modelo.aviones;
import java.util.List;

import modelo.exceptions.ChoqueException;
import modelo.general.Mapa;
import modelo.general.Posicion;
import modelo.general.Trayectoria;
import modelo.movimientos.Movimiento;
import modelo.pistas.Pista;


public abstract class Avion {
	
	protected Posicion posicion;
	
	private Trayectoria trayectoria;
	private Boolean aterrizado = false;
	private Movimiento movimiento;
	private Integer radio;
	
	public Avion(Posicion posicion, Integer radio){
		this.posicion = posicion;
		this.radio = radio;
	}
	
	
	public void avanzar() {
		getMovimiento().avanzar(getTrayectoria(), getPosicion());
	}

	public void vivir() throws Exception{
		if(!estaAterrizado()){
			avanzar();
			chequearChoques();
			chequearAterrizaje();
		}
	}
	
	public void chequearChoques() throws ChoqueException{
		List<Avion> avionesCercanos = Mapa.getInstance().getAvionesCercanos(this);
		if (avionesCercanos.size() > 1){
			throw new ChoqueException();
		}
	}
	
	public Boolean chequearAterrizaje(){
		List<Pista> pistas = Mapa.getInstance().getPistas();
		for(Pista p : pistas){
			if(intentarAterrizar(p)){
				aterrizar();
				return true;
			}
		}
		return false;
	}
	
	public abstract Boolean intentarAterrizar(Pista p);
	
	
	public Boolean estaCercaDe(Avion a){
		return getPosicion().estaCercaDe(a.getPosicion(),a.getRadio()+this.getRadio());
	}
	
	public void aterrizar(){
		aterrizado = true;
	}
	
	public Posicion getPosicion() {
		return posicion;
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


	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}


	public Movimiento getMovimiento() {
		return movimiento;
	}


	public Integer getRadio() {
		return radio;
	}


}
