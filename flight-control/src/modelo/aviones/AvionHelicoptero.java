package modelo.aviones;

import org.jdom.Element;

import modelo.general.Posicion;
import modelo.general.Trayectoria;
import modelo.juego.Escenario;
import modelo.movimientos.Movimiento;
import modelo.movimientos.MovimientoHelicoptero;
import modelo.pistas.Pista;

public class AvionHelicoptero extends Avion {

	private AvionHelicoptero(Posicion posicion, Integer radio, Escenario escenario) {
		super(posicion, radio, escenario);

	}

	public static AvionHelicoptero crearAvionHelicopteroSimple(
			Posicion posicion, Integer radio, Integer velocidad,
			Double direccion, Escenario escenario) {
		AvionHelicoptero avion = new AvionHelicoptero(posicion, radio, escenario);
		Movimiento mov = new MovimientoHelicoptero(velocidad, direccion);
		avion.setMovimiento(mov);
		return avion;
	}

	public Boolean intentarAterrizar(Pista pista) {
		return pista.puedeAterrizar(this);
	}

	@Override
	public Boolean puedoAterrizarEnEstaPista(Pista p) {
		return p.esLaPista(this);
	}

	@Override

	public Element serializarXML() {
		Element avionLivianoSerializado = new Element ("AvionHelicoptero");
		this.cargarElemento(avionLivianoSerializado);
		return avionLivianoSerializado;
	}

	public static AvionHelicoptero cargarDesdeXML(Element elementoXML, Escenario escenario){
		Integer radioXML = Integer.parseInt(elementoXML.getAttributeValue("radio"));
		Posicion posicionXML = Posicion.cargarDesdeXML(elementoXML.getChild("Posicion"));
		Trayectoria trayectoriaXML = Trayectoria.cargarDesdeXML(elementoXML.getChild("Trayectoria"));
		Movimiento movimientoXML = Movimiento.cargarDesdeXML(elementoXML.getChild("Movimiento"));

		AvionHelicoptero avionXML = new AvionHelicoptero(posicionXML, radioXML, escenario);
		
		avionXML.setTrayectoria(trayectoriaXML);
		avionXML.setMovimiento(movimientoXML);
		movimientoXML.setAvion(avionXML);
		return avionXML;
	}
}