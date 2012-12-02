package persistencia;
import static org.junit.Assert.assertTrue;

import java.io.FileWriter;
import java.io.IOException;

import modelo.general.Posicion;
import modelo.pistas.PistaLarga;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.junit.Test;

//PistaLarga y PistaHelicopteros Funcionan de la misma forma en su Persistencia

public class PersistenciaPistaLargaTest {
	@Test
	public void testUnaPistaLargaPodriaPoderSerializarce () throws IOException{
	
		String pathArchivo = "XML\\PersitenciaPistaLargaPrueba.xml";
		
		Integer radioApersistir = 2;
        Posicion posicionAPersistir = new Posicion (2,2);
        
        PistaLarga pistaAPersistir = new PistaLarga (posicionAPersistir, radioApersistir);
        
        
		Element posicioAPersistir = pistaAPersistir.serializarXML();
		Document document = new Document(posicioAPersistir);
	
		XMLOutputter outputter = new XMLOutputter();
		outputter.setFormat(Format.getPrettyFormat());
    
		FileWriter writer = new FileWriter(pathArchivo);
		outputter.output(document,writer);
		writer.close();
	}
	
	@Test
	public void testUnaPistaLargaSePodriaCrearApartirDeUnArchivoXML() throws IOException
	{
		PistaLarga pistaLargaXML;
		try {
			String pathArchivo = "XML\\PersitenciaPistaLargaPrueba.xml";
	        SAXBuilder builder = new SAXBuilder();
	        Document document = builder.build(pathArchivo);
	        
	        pistaLargaXML = PistaLarga.cargarDesdeXML(document.getRootElement());
	        
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
			assertTrue (pistaLargaXML.getPosicion() instanceof Posicion);
			assertTrue(pistaLargaXML.getPosicion().getX() == 2);
	}
}
