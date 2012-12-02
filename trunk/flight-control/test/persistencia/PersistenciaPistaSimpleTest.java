package persistencia;
import static org.junit.Assert.assertTrue;

import java.io.FileWriter;
import java.io.IOException;

import modelo.general.Posicion;
import modelo.pistas.PistaSimple;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.junit.Test;

public class PersistenciaPistaSimpleTest {
	@Test
	public void testUnaPistaSimplePodriaPoderSerializarce () throws IOException{
	
		String pathArchivo = "XML\\PersitenciaPistaSimplePrueba.xml";
		
		Integer radioApersistir = 2;
        Posicion posicionAPersistir = new Posicion (2,2);
        
        PistaSimple pistaAPersistir = new PistaSimple (posicionAPersistir, radioApersistir);
        
        
		Element posicioAPersistir = pistaAPersistir.serializarXML();
		Document document = new Document(posicioAPersistir);
	
		XMLOutputter outputter = new XMLOutputter();
		outputter.setFormat(Format.getPrettyFormat());
    
		FileWriter writer = new FileWriter(pathArchivo);
		outputter.output(document,writer);
		writer.close();
	}
	
	@Test
	public void testUnaPistaSimpleSePodriaCrearApartirDeUnArchivoXML() throws IOException
	{
		PistaSimple pistaSimpleXML;
		try {
			String pathArchivo = "XML\\PersitenciaPistaSimplePrueba.xml";
	        SAXBuilder builder = new SAXBuilder();
	        Document document = builder.build(pathArchivo);
	        
	        pistaSimpleXML = PistaSimple.cargarDesdeXML(document.getRootElement());
	        
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
			assertTrue (pistaSimpleXML.getPosicion() instanceof Posicion);
			assertTrue(pistaSimpleXML.getPosicion().getX() == 2);
	}
}
