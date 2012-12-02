package modelo.movimientos;



import org.jdom.Attribute;
import org.jdom.Element;

import modelo.general.Posicion;
import modelo.general.Trayectoria;

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
			if(siguiente.estaCercaDe(actualPos, getVelocidad())){
				trayectoria.eliminarPosicion();
			}
		}
		Integer despX = Long.valueOf(Math.round(getVelocidad() * Math.cos(getDireccion()))).intValue();
		Integer despY = Long.valueOf(Math.round(getVelocidad() * Math.sin(getDireccion()))).intValue();
		actualPos.moverEnX(despX);
		actualPos.moverEnY(despY);
	}

	@Override
	
	public Element serializarXML() {
		Element movimientoSimpleSerializado = new Element ("MovimientoSimple");
		Attribute velocidad = new Attribute ("velocidad",this.velocidad.toString());
		Attribute direccion = new Attribute ("direccion", this.direccion.toString());
		movimientoSimpleSerializado.setAttribute(velocidad);
		movimientoSimpleSerializado.setAttribute(direccion);
		return movimientoSimpleSerializado;
	}

	public static MovimientoSimple cargarDesdeXML(Element elementoXML) {
		Integer velocidad = Integer.parseInt(elementoXML.getAttributeValue("velocidad"));
		Double direccion = Double.parseDouble(elementoXML.getAttributeValue("velocidad"));
		
		return new MovimientoSimple (velocidad, direccion);
		
	}

}
