package modelo.movimientos;

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

}