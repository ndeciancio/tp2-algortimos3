package modelo.aviones;
import java.util.List;

import modelo.exceptions.ChoqueException;
import modelo.exceptions.FalloEnLaCreacionMedianteXML;
import modelo.general.Mapa;
import modelo.general.ObjetoSerializableXML;
import modelo.general.Posicion;
import modelo.general.Trayectoria;
import modelo.juego.Escenario;
import modelo.movimientos.Movimiento;
import modelo.pistas.Pista;

import org.jdom.Attribute;
import org.jdom.Element;

import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;
import fiuba.algo3.titiritero.modelo.ObjetoVivo;


public abstract class Avion implements ObjetoVivo, ObjetoPosicionable,ObjetoSerializableXML {
	
	protected Posicion posicion;
	protected Boolean aterrizado = false;
	
	private Trayectoria trayectoria = new Trayectoria();
	private Movimiento movimiento;
	private Integer radio;
	
	private Boolean seleccionado = false;
	
	private Escenario escenario;
	
	public Avion(Posicion posicion, Integer radio, Escenario escenario){
		this.posicion = posicion;
		this.radio = radio;
		this.escenario = escenario;
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
				System.out.println("HUBO UN CHOQUE EN " + this.getX()+" "+ this.getY() );
				escenario.huboUnChoque();
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
		return getPosicion().estaCercaDe(a.getPosicion(),a.getRadio());
	}
	
	public void aterrizar(){
		aterrizado = true;
		escenario.aterrizo(this);
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
	
	public Double getDireccion(){
		return this.movimiento.getDireccion();
	}
	
	public abstract Element serializarXML();


	@SuppressWarnings("unchecked")
	protected Element cargarElemento(Element elementoXMLConDatosAvion) {
		Attribute radio = new Attribute ("radio",this.radio.toString());
		
		Element posicion = this.posicion.serializarXML();
		Element trayectoria = this.trayectoria.serializarXML();
		Element movimientoCategoria = new Element ("Movimiento");
		
		Element movimiento = this.movimiento.serializarXML();
		movimientoCategoria.setContent(movimiento);
		
		elementoXMLConDatosAvion.setAttribute(radio);
		elementoXMLConDatosAvion.getChildren().add(posicion);
		elementoXMLConDatosAvion.getChildren().add(trayectoria);
		elementoXMLConDatosAvion.getChildren().add(movimientoCategoria);
		
		return elementoXMLConDatosAvion;
	}


	
	public static Avion cargarDesdeXML(Element elementoXML, Escenario escenario) {
		
		String elementoAvion = elementoXML.getName();
		
		if (elementoAvion.equals("AvionLiviano")){
			return AvionLiviano.cargarDesdeXML(elementoXML, escenario);
		}
		
		if (elementoAvion.equals("AvionPesado")){
			return AvionPesado.cargarDesdeXML(elementoXML, escenario);
		}
		
		if (elementoAvion.equals("AvionHelicoptero")){
			return AvionHelicoptero.cargarDesdeXML(elementoXML, escenario);
		}
	
		FalloEnLaCreacionMedianteXML errorEnCreacion = new FalloEnLaCreacionMedianteXML(
				"Hubo Un Error Al Crear Un Avion Desde Un Nodo XML");
		throw errorEnCreacion;
	} 
}
