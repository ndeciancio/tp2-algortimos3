package control;

import java.io.FileWriter;
import java.io.IOException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import vista.FlightControl;

public class Archivador {

	private String path;

	public Archivador(String pathArchivador) {
		this.path = pathArchivador;
	}

	public void archivarJuego(FlightControl simuladorAPersistir) throws IOException {
		
		Element elementoAPersitir = simuladorAPersistir.serializarXML();
		Document document = new Document(elementoAPersitir);
		
		XMLOutputter outputter = new XMLOutputter();
		outputter.setFormat(Format.getPrettyFormat());
    
		FileWriter writer = new FileWriter(path);
		outputter.output(document,writer);
		writer.close();
	}

	public void cargarSimuladorDesdeXML(FlightControl flightControl) {
		Element elementoRaiz; 
		try {

	        SAXBuilder builder = new SAXBuilder();
	        Document document = builder.build(path);
	        elementoRaiz = document.getRootElement();
	        
	        
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		flightControl.setUpGameDesdeXML(elementoRaiz);
	}


}
