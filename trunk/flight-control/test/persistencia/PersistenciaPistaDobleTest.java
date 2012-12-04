package persistencia;
import static org.junit.Assert.assertTrue;

import java.io.FileWriter;
import java.io.IOException;

import modelo.general.Posicion;

import modelo.pistas.PistaDobleEntrada;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.junit.Test;

public class PersistenciaPistaDobleTest {
	@Test
	public void testUnaPistaDoblePodriaPoderSerializarce () throws IOException{
	
		String pathArchivo = "XML\\PersitenciaPistaDoblePrueba.xml";
		
		Integer radio1Apersistir = 2;
        Posicion posicion1APersistir = new Posicion (2,2);
		Integer radio2Apersistir = 2;
        Posicion posicion2APersistir = new Posicion (1,1);
        Double direccionApersistir = 1d;
        PistaDobleEntrada pistaAPersistir = new PistaDobleEntrada (posicion1APersistir, posicion2APersistir, radio1Apersistir, radio2Apersistir,direccionApersistir);
        
        
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
		PistaDobleEntrada pistaDobleXML;
		try {
			String pathArchivo = "XML\\PersitenciaPistaDoblePrueba.xml";
	        SAXBuilder builder = new SAXBuilder();
	        Document document = builder.build(pathArchivo);
	        
	        pistaDobleXML = PistaDobleEntrada.cargarDesdeXML(document.getRootElement());
	        
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
			assertTrue (pistaDobleXML.getPosicion() instanceof Posicion);
			assertTrue(pistaDobleXML.getPosicion().getX() == 2);
			
			assertTrue (pistaDobleXML.getPosicion2().getX() == 1 );
	}
}
