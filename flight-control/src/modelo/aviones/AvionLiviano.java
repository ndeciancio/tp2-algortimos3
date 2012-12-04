package modelo.aviones;




import org.jdom.Element;

import modelo.general.Posicion;
import modelo.general.Trayectoria;
import modelo.juego.Escenario;
import modelo.movimientos.Movimiento;
import modelo.movimientos.MovimientoInteligente;
import modelo.movimientos.MovimientoSimple;
import modelo.pistas.Pista;

public class AvionLiviano extends Avion {

	
	private AvionLiviano(Posicion posicion, Integer radio, Escenario escenario){
		super(posicion, radio, escenario);
	}
	
	public static AvionLiviano crearAvionLivianoSimple(Posicion posicion,Integer radio, Integer velocidad, Double direccion, Escenario escenario){
		AvionLiviano avion = new AvionLiviano(posicion, radio, escenario);
		Movimiento mov = new MovimientoSimple(velocidad, direccion);
		avion.setMovimiento(mov);
		return avion;
	}
	
	public static AvionLiviano crearAvionLivianoInteligente(Posicion posicion,Integer radio, Integer velocidad, Double direccion, Escenario escenario){
		AvionLiviano avion = new AvionLiviano(posicion, radio, escenario);
		Movimiento mov = new MovimientoInteligente(velocidad, direccion);
		avion.setMovimiento(mov);
		mov.setAvion(avion);
		return avion;
	}
	
	@Override
	public Boolean intentarAterrizar(Pista p){
		return p.puedeAterrizar(this);
	}
	
	@Override
	public Boolean puedoAterrizarEnEstaPista(Pista p) {
		return p.esLaPista(this);
	}

	public Element serializarXML() {
		Element avionLivianoSerializado = new Element ("AvionLiviano");
		this.cargarElemento(avionLivianoSerializado);
		return avionLivianoSerializado;
	}

	public static AvionLiviano cargarDesdeXML(Element elementoXML, Escenario escenario){
		Integer radioXML = Integer.parseInt(elementoXML.getAttributeValue("radio"));
		Posicion posicionXML = Posicion.cargarDesdeXML(elementoXML.getChild("Posicion"));
		Trayectoria trayectoriaXML = Trayectoria.cargarDesdeXML(elementoXML.getChild("Trayectoria"));
		Movimiento movimientoXML = Movimiento.cargarDesdeXML(elementoXML.getChild("Movimiento"));
	
		AvionLiviano avionXML = new AvionLiviano(posicionXML, radioXML, escenario);
		
		avionXML.setTrayectoria(trayectoriaXML);
		avionXML.setMovimiento(movimientoXML);
		
		return avionXML;
	}
	
	
	

}
