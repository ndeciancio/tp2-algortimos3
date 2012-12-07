package control;

import java.io.FileWriter;
import java.io.IOException;

import modelo.general.ObjetoSerializableXML;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;



public class Archivador {

	public Archivador() {

	}

	public void archivarJuego(ObjetoSerializableXML objetoAArchivarPorSerializacion, String path) throws IOException {
		
		Element elementoAPersitir = objetoAArchivarPorSerializacion.serializarXML();
		Document document = new Document(elementoAPersitir);
		
		XMLOutputter outputter = new XMLOutputter();
		outputter.setFormat(Format.getPrettyFormat());
    
		FileWriter writer = new FileWriter(path);
		outputter.output(document,writer);
		writer.close();
	}

	public Element getElementoRaiz(String path) {
		Element elementoRaiz; 
		try {

	        SAXBuilder builder = new SAXBuilder();
	        Document document = builder.build(path);
	        elementoRaiz = document.getRootElement();
	        
	        
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		return elementoRaiz;
		
	}


}
