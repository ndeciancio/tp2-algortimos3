package modelo.pistas;


import org.jdom.Attribute;
import org.jdom.Element;


import modelo.aviones.AvionHelicoptero;
import modelo.aviones.AvionLiviano;
import modelo.aviones.AvionPesado;
import modelo.exceptions.FalloEnLaCreacionMedianteXML;
import modelo.general.ObjetoSerializableXML;
import modelo.general.Posicion;

import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;

public abstract class Pista implements ObjetoPosicionable,ObjetoSerializableXML {

	private Posicion posicion;
	private Integer radio;
	private Double direccion;
	
	public Pista(Posicion posicion, Integer radio, Double direccion){
		this.posicion = posicion;
		this.radio = radio;
		this.direccion = direccion;
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
	
	public Double getDireccion(){
		return direccion;
	}
	
	public int getX(){
		return this.getPosicion().getX();
	}
	
	public int getY(){
		return this.getPosicion().getY();
	}
	

	@SuppressWarnings("unchecked")
	protected Element cargarElemento(Element elementoXMLConDatosPista) {
		Attribute radio1 = new Attribute ("radio1", this.radio.toString());
		Attribute direccion = new Attribute ("direccion", Double.valueOf(this.direccion).toString());
		
		elementoXMLConDatosPista.getAttributes().add(radio1);
		elementoXMLConDatosPista.getAttributes().add(direccion);
		
		elementoXMLConDatosPista.setContent(this.posicion.serializarXML());

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
		
		FalloEnLaCreacionMedianteXML errorEnCreacion = new FalloEnLaCreacionMedianteXML(
				"Hubo Un Error Al Crear Una Pista Desde Un Nodo XML");
		throw errorEnCreacion;
	} 
}
