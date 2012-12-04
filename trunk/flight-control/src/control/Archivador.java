package control;

import static org.junit.Assert.assertTrue;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import vista.FlightControl;

import modelo.aviones.AvionHelicoptero;
import modelo.factories.FactoryAvion;
import modelo.juego.Escenario;
import modelo.pistas.Pista;

public class Archivador {

	private String path;

	public Archivador(String pathArchivador) {
		this.path = pathArchivador;
	}

	public void archivarJuego(Escenario juegoAPersistir) throws IOException {
		
		Element elementoAPersitir = juegoAPersistir.serializarXML();
		Document document = new Document(elementoAPersitir);
		
		XMLOutputter outputter = new XMLOutputter();
		outputter.setFormat(Format.getPrettyFormat());
    
		FileWriter writer = new FileWriter(path);
		outputter.output(document,writer);
		writer.close();
	}

	public Escenario crearJuegoDesdeXMLConVista(FlightControl simulador) {
		Element elementoRaiz; 
		Escenario escenarioXML;
		List<Pista> pistas = new ArrayList <Pista> ();
		List<FactoryAvion> factorys = new ArrayList <FactoryAvion> ();
		Integer cantidadAvionesMaximaPorNivel;
		try {

	        SAXBuilder builder = new SAXBuilder();
	        Document document = builder.build(path);
	        elementoRaiz = document.getRootElement();
	        
	        
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
		Element factorysXML = elementoRaiz.getChild("fabricasDeAviones");
		Iterator<Element> iteradorFactorys = factorysXML.getChildren().iterator();
		while (iteradorFactorys.hasNext()){
			FactoryAvion fabrica = FactoryAvion.crearDesdeXML(iteradorFactorys.next());
			factorys.add(fabrica);
		}
			
		Element pistasXML = elementoRaiz.getChild("Mapa").getChild("Pistas");
		Iterator <Element> iteradorPistas = pistasXML.getChildren().iterator();
		while (iteradorPistas.hasNext()){
			Pista pista = Pista.cargarDesdeXML(iteradorPistas.next());
			pistas.add(pista);
		}
		
		cantidadAvionesMaximaPorNivel = Integer.parseInt(elementoRaiz.getAttributeValue("cantidadMaximaAvionesPorNivel"));
		escenarioXML = new Escenario (cantidadAvionesMaximaPorNivel, pistas, factorys, simulador);
		escenarioXML.cargarAtributosDesdeXML(elementoRaiz);
		return escenarioXML;

	}

}
