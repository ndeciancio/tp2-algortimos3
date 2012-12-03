package modelo.pistas;

import org.jdom.Attribute;
import org.jdom.Element;

import modelo.aviones.AvionHelicoptero;
import modelo.aviones.AvionLiviano;
import modelo.aviones.AvionPesado;
import modelo.general.Posicion;

public class PistaDobleEntrada extends Pista {

	private Posicion posicion2;
	private Integer radio2;
	
	public PistaDobleEntrada(Posicion posicion,Posicion posicion2, Integer radio, Integer radio2, Double direccion) {
		super(posicion,radio,direccion);
		this.posicion2 = posicion2;
		this.radio2 = radio2;
	}

	@Override
	public Boolean puedeAterrizar(AvionLiviano avion) {
		if(getPosicion().estaCercaDe(avion.getPosicion(), avion.getRadio()+this.getRadio())){
			return true;
		}else if(getPosicion2().estaCercaDe(avion.getPosicion(), avion.getRadio()+this.getRadio2())){
			return true;
		}else{
			return false;
		}
	}
	
	

	@Override
	public Boolean puedeAterrizar(AvionPesado avion) {
		return false;
	}
	
	public Boolean puedeAterrizar(AvionHelicoptero avion) {
		return false;
	}

	public Posicion getPosicion2() {
		return posicion2;
	}

	public Integer getRadio2() {
		return radio2;
	}

	@Override
	public Boolean esLaPista(AvionLiviano avion) {
		return true;
	}

	@Override
	public Boolean esLaPista(AvionPesado avion) {
		return false;
	}

	@Override
	public Boolean esLaPista(AvionHelicoptero avion) {
		return false;
	}

	@SuppressWarnings("unchecked")
	public Element serializarXML() {
		Element pistaDobleSerializada = new Element ("PistaDobleEntrada");
		
		Element primeraEntradaSerializada = new Element ("PrimeraEntrada");
		this.cargarElemento(primeraEntradaSerializada);
		
		Element segundaEntradaSerializada = new Element ("SegundaEntrada");
		Attribute radio2 = new Attribute ("radio2",this.radio2.toString());
		Element posicion2Serializada = this.posicion2.serializarXML();
		segundaEntradaSerializada.setAttribute(radio2);
		segundaEntradaSerializada.setContent(posicion2Serializada);
		
		pistaDobleSerializada.getChildren().add(primeraEntradaSerializada);
		pistaDobleSerializada.getChildren().add(segundaEntradaSerializada);
		
		return pistaDobleSerializada;
	}
	
	public static PistaDobleEntrada cargarDesdeXML(Element elementoXML) {
		Element primeraEntrada = elementoXML.getChild("PrimeraEntrada");
		Element segundaEntrada = elementoXML.getChild("SegundaEntrada");
		
		Posicion primeraPosicion = Posicion.cargarDesdeXML(primeraEntrada.getChild("Posicion"));
		Posicion segundaPosicion = Posicion.cargarDesdeXML(segundaEntrada.getChild("Posicion"));
		
		Integer radio1 = Integer.parseInt(primeraEntrada.getAttributeValue("radio1"));
		Integer radio2 = Integer.parseInt(segundaEntrada.getAttributeValue("radio2"));
		
		return new PistaDobleEntrada (primeraPosicion, segundaPosicion, radio1, radio2,null);
		
	}
	
}
