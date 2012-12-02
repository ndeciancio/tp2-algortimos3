package modelo.pistas;

import org.jdom.Attribute;
import org.jdom.Element;


import modelo.aviones.AvionHelicoptero;
import modelo.aviones.AvionLiviano;
import modelo.aviones.AvionPesado;
import modelo.general.Posicion;

import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;

public abstract class Pista implements ObjetoPosicionable {

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
	public abstract Boolean puedeAterrizar(AvionPesado avion);
	public abstract Boolean puedeAterrizar(AvionHelicoptero avion);
	public abstract Boolean esLaPista(AvionLiviano avion);
	public abstract Boolean esLaPista(AvionPesado avion);
	public abstract Boolean esLaPista(AvionHelicoptero avion);
	
	public Integer getRadio() {
		return radio;
	}
	
	public int getX(){
		return this.getPosicion().getX();
	}
	
	public int getY(){
		return this.getPosicion().getY();
	}
	
	protected Element cargarElemento(Element elementoXMLConDatosPista) {
		
		
		elementoXMLConDatosPista.setContent(this.posicion.serializarXML());
		Attribute radio1 = new Attribute ("radio1", this.radio.toString());
		
		elementoXMLConDatosPista.setAttribute(radio1);
		return elementoXMLConDatosPista;
	}

	public abstract Element serializarXML();
	
	public static Pista cargarDesdeXML(Element elementoXML) {
		String pista = elementoXML.getName();
		if (pista.equals("PistaSimple")){
			return PistaSimple.cargarDesdeXML(elementoXML);
		}
		if (pista.equals("PistaLarga")){
			return PistaLarga.cargarDesdeXML(elementoXML);
		}
		if (pista.equals("PistaDobleEntrada")){
			return PistaDobleEntrada.cargarDesdeXML(elementoXML);
		}
		if (pista.equals("PistaHelipuerto")){
			return PistaHelipuerto.cargarDesdeXML(elementoXML);
		}
		
		return null;
	} 
}
