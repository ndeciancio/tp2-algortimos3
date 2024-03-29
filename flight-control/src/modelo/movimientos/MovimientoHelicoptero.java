package modelo.movimientos;



import org.jdom.Element;

import modelo.general.Posicion;
import modelo.general.Trayectoria;

public class MovimientoHelicoptero extends Movimiento {

	public MovimientoHelicoptero(Integer velocidad, Double direccion) {
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
			Integer despX = Long.valueOf(Math.round(getVelocidad() * Math.cos(getDireccion()))).intValue();
			Integer despY = Long.valueOf(Math.round(getVelocidad() * Math.sin(getDireccion()))).intValue();
			actualPos.moverEnX(despX);
			actualPos.moverEnY(despY);
		}
		

	}



	public static MovimientoHelicoptero cargarDesdeXML(Element elementoXML) {
		Integer velocidad = Integer.parseInt(elementoXML.getAttributeValue("velocidad"));
		Double direccion = Double.parseDouble(elementoXML.getAttributeValue("direccion"));
		return new MovimientoHelicoptero (velocidad, direccion);
	}


	public Element serializarXML() {
		Element movimientoHelicopteroSerializado = new Element ("MovimientoHelicoptero");
		this.cargarElemento(movimientoHelicopteroSerializado);
		return movimientoHelicopteroSerializado;
	}




}
