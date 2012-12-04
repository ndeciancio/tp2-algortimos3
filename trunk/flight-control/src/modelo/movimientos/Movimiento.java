package modelo.movimientos;

import org.jdom.Attribute;
import org.jdom.Element;

import modelo.aviones.Avion;
import modelo.general.Posicion;
import modelo.general.Trayectoria;

public abstract class Movimiento {

	private Integer velocidad; // posiciones avanzadas por ciclo

	private Double direccion; // angulo medido en radianes
	private Avion avion;

	public Movimiento(Integer velocidad, Double direccion) {
		this.velocidad = velocidad;
		this.direccion = direccion;
	}

	public abstract void avanzar(Trayectoria trayectoria, Posicion actualPos);

	public Integer getVelocidad() {
		return velocidad;
	}

	public Double getDireccion() {
		return direccion;
	}

	protected void setDireccion(Double direccion) {
		this.direccion = direccion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}
	public Avion getAvion(){
		return avion;
	}
	
	public abstract Element serializarXML();


	protected Element cargarElemento(Element elementoXMLConDatosMovimiento) {
		Attribute velocidad = new Attribute ("velocidad",this.velocidad.toString());
		Attribute direccion = new Attribute ("direccion", this.direccion.toString());
		elementoXMLConDatosMovimiento.setAttribute(velocidad);
		elementoXMLConDatosMovimiento.setAttribute(direccion);
		return elementoXMLConDatosMovimiento;
	}


	
	public static Movimiento cargarDesdeXML(Element elementoXML) {
		
		Element elementoMovimiento; 

		elementoMovimiento = elementoXML.getChild("MovimientoSimple");
		
		if (elementoMovimiento != null){
			return MovimientoSimple.cargarDesdeXML(elementoMovimiento);
		}
		
		elementoMovimiento = elementoXML.getChild("MovimientoHelicoptero");
		
		if (elementoMovimiento != null){
			return MovimientoHelicoptero.cargarDesdeXML(elementoMovimiento);
		}
		elementoMovimiento = elementoXML.getChild("MovimientoInteligente");
		
		if (elementoMovimiento != null){
			return MovimientoInteligente.cargarDesdeXML(elementoMovimiento);
		}
		
		return null;
	} 
}
