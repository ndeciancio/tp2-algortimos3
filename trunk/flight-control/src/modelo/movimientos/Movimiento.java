package modelo.movimientos;

import org.jdom.Element;

import modelo.aviones.Avion;
import modelo.general.Posicion;
import modelo.general.Trayectoria;

public abstract class Movimiento {

	protected Integer velocidad; // posiciones avanzadas por ciclo

	protected Double direccion; // angulo medido en radianes
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


	
	public static Movimiento cargarDesdeXML(Element elementoXML) {
		String movimiento = elementoXML.getName();
		if (movimiento.equals("MovimientoSimple")){
			return MovimientoSimple.cargarDesdeXML(elementoXML);
		}
		
		
		return null;
	} 
}
