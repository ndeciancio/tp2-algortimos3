package modelo.movimientos;

import modelo.general.Mapa;
import modelo.general.Posicion;
import modelo.general.Trayectoria;

import org.jdom.Element;

public class MovimientoSimple extends Movimiento {

	public MovimientoSimple(Integer velocidad, Double direccion) {
		super(velocidad, direccion);
	}

	@Override
	public void avanzar(Trayectoria trayectoria, Posicion actualPos) {
		Posicion siguiente = trayectoria.getPosicionSiguiente();
		if (siguiente != null) {
			Integer desfasajeX = siguiente.getX() - actualPos.getX();
			Integer desfasajeY = siguiente.getY() - actualPos.getY();
			setDireccion(Math.atan2(desfasajeY, desfasajeX));
			if (siguiente.estaCercaDe(actualPos, getVelocidad())) {
				trayectoria.eliminarPosicion();
			}
		}
		Integer despX = Long.valueOf(
				Math.round(getVelocidad() * Math.cos(getDireccion())))
				.intValue();
		Integer despY = Long.valueOf(
				Math.round(getVelocidad() * Math.sin(getDireccion())))
				.intValue();
		actualPos.moverEnX(despX);
		actualPos.moverEnY(despY);
		Integer bordeX = Mapa.getInstance().getBordeX();
		Integer bordeY = Mapa.getInstance().getBordeY();
		if (actualPos.getX() < 0 || actualPos.getX() > bordeX
				|| actualPos.getY() < 0 || actualPos.getY() > bordeY) {
			setDireccion(getDireccion()-Math.PI);
		}

	}

	public Element serializarXML() {
		Element movimientoSimpleSerializado = new Element ("MovimientoSimple");
		this.cargarElemento(movimientoSimpleSerializado);
		return movimientoSimpleSerializado;
	}
	
	public static MovimientoSimple cargarDesdeXML(Element elementoXML) {
		Integer velocidad = Integer.parseInt(elementoXML.getAttributeValue("velocidad"));
		Double direccion = Double.parseDouble(elementoXML.getAttributeValue("direccion"));
		
		return new MovimientoSimple (velocidad, direccion);
		
	}

}
