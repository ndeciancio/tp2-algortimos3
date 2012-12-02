package modelo.movimientos;

import org.jdom.Element;

import modelo.general.Mapa;
import modelo.general.Posicion;
import modelo.general.Trayectoria;
import modelo.pistas.Pista;

public class MovimientoInteligente extends Movimiento {

	private Pista pistaCercana;
	
	public MovimientoInteligente(Integer velocidad, Double direccion) {
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
		} else {
			if (pistaCercana == null) {
				for (Pista p : Mapa.getInstance().getPistas()) {
					if (getAvion().puedoAterrizarEnEstaPista(p)) {
						pistaCercana = p;
					}
				}
			}
			Integer desfasajeX = pistaCercana.getX() - actualPos.getX();
			Integer desfasajeY = pistaCercana.getY() - actualPos.getY();
			setDireccion(Math.atan2(desfasajeY, desfasajeX));
		}
		Integer despX = Long.valueOf(
				Math.round(getVelocidad() * Math.cos(getDireccion())))
				.intValue();
		Integer despY = Long.valueOf(
				Math.round(getVelocidad() * Math.sin(getDireccion())))
				.intValue();
		actualPos.moverEnX(despX);
		actualPos.moverEnY(despY);

	}

	public static MovimientoInteligente cargarDesdeXML(Element elementoXML) {
		Integer velocidad = Integer.parseInt(elementoXML.getAttributeValue("velocidad"));
		Double direccion = Double.parseDouble(elementoXML.getAttributeValue("velocidad"));
		return new MovimientoInteligente (velocidad, direccion);
	}


	public Element serializarXML() {
		Element movimientoInteligenteSerializado = new Element ("MovimientoInteligente");
		this.cargarElemento(movimientoInteligenteSerializado);

		return movimientoInteligenteSerializado;
	}
}
