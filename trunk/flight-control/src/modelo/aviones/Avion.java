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
	
	private Trayectoria trayectoria = new Trayectoria();
	private Movimiento movimiento;
	private Integer radio;
	
	private Boolean seleccionado = false;
	
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
	public abstract Boolean puedoAterrizarEnEstaPista(Pista p);
	
	public Boolean estaCercaDe(Avion a){
		return getPosicion().estaCercaDe(a.getPosicion(),a.getRadio()+this.getRadio());
	}
	
	public void aterrizar(){
		aterrizado = true;
		juego.aterrizo(this);
	}
	
	public void agregarPosicionATrayectoria(Posicion pos){
		trayectoria.agregarPosicion(pos);
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
	
	public void seleccionar(){
		trayectoria = new Trayectoria();
		seleccionado = true;
	}
	
	public void desSeleccionar(){
		seleccionado = false;
	}
	
	public Boolean estaSeleccionado(){
		return seleccionado;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		System.out.println("Intentando equals");
		Avion other = (Avion) obj;
		if (posicion == null) {
			if (other.posicion != null)
				return false;
		} else if (!posicion.equals(other.posicion))
			return false;
		if (radio == null) {
			if (other.radio != null)
				return false;
		} else if (!radio.equals(other.radio))
			return false;
		if (seleccionado == null) {
			if (other.seleccionado != null)
				return false;
		} else if (!seleccionado.equals(other.seleccionado))
			return false;
		return true;
	}
}
