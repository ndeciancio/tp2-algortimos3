
public abstract class Movimiento {
	
	private Integer velocidad; //posiciones avanzadas por ciclo
	
	private Double direccion; //angulo medido en radianes
	
	public Movimiento(Integer velocidad, Double direccion){
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
	public void setDireccion(Double direccion) {
		this.direccion = direccion;
	}
}