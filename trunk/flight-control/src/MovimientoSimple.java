
public class MovimientoSimple extends Movimiento {

	
	public MovimientoSimple(Integer velocidad, Double direccion) {
		super(velocidad, direccion);
	}

	@Override
	public void avanzar(Trayectoria trayectoria, Posicion actualPos) {
		Posicion siguiente = trayectoria.getPosicionSiguiente();
		if (siguiente != null){
			Integer desfasajeX = siguiente.getX() - actualPos.getX();
			Integer desfasajeY = siguiente.getY() - actualPos.getY();
			setDireccion(Math.atan2(desfasajeY, desfasajeX)); 
			if(desfasajeX.equals(0) && desfasajeY.equals(0)){
				trayectoria.eliminarPosicion();
			}
		}
		Integer despX = Long.valueOf(Math.round(getVelocidad() * Math.cos(getDireccion()))).intValue();
		Integer despY = Long.valueOf(Math.round(getVelocidad() * Math.sin(getDireccion()))).intValue();
		actualPos.moverEnX(despX);
		actualPos.moverEnY(despY);
		
	} 

}
