package modelo.aviones;

import org.jdom.Element;

import modelo.general.Posicion;
import modelo.general.Trayectoria;
import modelo.juego.Escenario;
import modelo.movimientos.Movimiento;
import modelo.movimientos.MovimientoInteligente;
import modelo.movimientos.MovimientoSimple;
import modelo.pistas.Pista;

public class AvionPesado extends Avion {

	private AvionPesado(Posicion posicion, Integer radio, Escenario escenario) {
		super(posicion, radio, escenario);
	}

	public static AvionPesado crearAvionPesadoSimple(Posicion posicion,Integer radio, Integer velocidad, Double direccion, Escenario escenario){
		AvionPesado avion = new AvionPesado(posicion, radio, escenario);
		Movimiento mov = new MovimientoSimple(velocidad, direccion);
		avion.setMovimiento(mov);
		return avion;
	}
	
	public static AvionPesado crearAvionPesadoInteligente(Posicion posicion,Integer radio, Integer velocidad, Double direccion, Escenario escenario){
		AvionPesado avion = new AvionPesado(posicion, radio, escenario);
		Movimiento mov = new MovimientoInteligente(velocidad, direccion);
		avion.setMovimiento(mov);
		mov.setAvion(avion);
		return avion;
	}
	
	@Override
	public Boolean intentarAterrizar(Pista p) {
		return p.puedeAterrizar(this);
	}

	@Override
	public Boolean puedoAterrizarEnEstaPista(Pista p) {
		return p.esLaPista(this);
	}

	public Element serializarXML() {
		Element avionLivianoSerializado = new Element ("AvionHelicoptero");
		this.cargarElemento(avionLivianoSerializado);
		return avionLivianoSerializado;
	}

	public static AvionPesado cargarDesdeXML(Element elementoXML, Escenario escenario){
		Integer radioXML = Integer.parseInt(elementoXML.getAttributeValue("radio"));
		Posicion posicionXML = Posicion.cargarDesdeXML(elementoXML.getChild("Posicion"));
		Trayectoria trayectoriaXML = Trayectoria.cargarDesdeXML(elementoXML.getChild("Trayectoria"));
		Movimiento movimientoXML = Movimiento.cargarDesdeXML(elementoXML.getChild("Movimiento"));

		AvionPesado avionXML = new AvionPesado(posicionXML, radioXML, escenario);
		
		avionXML.setTrayectoria(trayectoriaXML);
		avionXML.setMovimiento(movimientoXML);
		movimientoXML.setAvion(avionXML);
		return avionXML;
	}
}
