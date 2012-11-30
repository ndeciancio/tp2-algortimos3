package modelo.aviones;
import java.util.List;

import modelo.exceptions.ChoqueException;
import modelo.general.Mapa;
import modelo.general.Posicion;
import modelo.general.Trayectoria;
import modelo.juego.Juego;
import modelo.movimientos.Movimiento;
import modelo.pistas.Pista;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;
import fiuba.algo3.titiritero.modelo.ObjetoVivo;


public abstract class Avion implements ObjetoVivo, ObjetoPosicionable {
	
	protected Posicion posicion;
	protected Boolean aterrizado = false;
	
	private Trayectoria trayectoria;
	private Movimiento movimiento;
	private Integer radio;
	
	private Juego juego;
	
	public Avion(Posicion posicion, Integer radio, Juego juego){
		this.posicion = posicion;
		this.radio = radio;
		this.juego = juego;
	}
	
	
	public void avanzar() {
		getMovimiento().avanzar(getTrayectoria(), getPosicion());
	}

	public void vivir(){
		if(!estaAterrizado()){
			avanzar();
			try{
				chequearChoques();
			}catch(ChoqueException e){
				juego.huboUnChoque();
				System.out.println("HUBO UN CHOQUE");
			}
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

	public int getX(){
		return this.getPosicion().getX();
	}
	
	public int getY(){
		return this.getPosicion().getY();
	}
	

}
