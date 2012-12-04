package persistencia;
import static org.junit.Assert.assertTrue;

import java.io.FileWriter;
import java.io.IOException;

import modelo.movimientos.MovimientoHelicoptero;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.junit.Test;

public class PersistenciaMovimientoHelicopteroTest {
	@Test
	public void testUnMovimientoHelicopteroPodriaPoderSerializarce () throws IOException{
	
		String pathArchivo = "XML\\PersitenciaMovimientoHelicopteroPrueba.xml";
		
		Integer velocidadAPersistir = 10;
        Double direccionAPersistir = 0d;
        
        MovimientoHelicoptero moviemientoAPersistir = new MovimientoHelicoptero (velocidadAPersistir, direccionAPersistir);
        
        
		Element elementoApersistir = moviemientoAPersistir.serializarXML();
		Document document = new Document(elementoApersistir);
	
		XMLOutputter outputter = new XMLOutputter();
		outputter.setFormat(Format.getPrettyFormat());
    
		FileWriter writer = new FileWriter(pathArchivo);
		outputter.output(document,writer);
		writer.close();
	}
	
	@Test
	public void testUnMovimientoHelicopteroSePodriaCrearApartirDeUnArchivoXML() throws IOException
	{
		MovimientoHelicoptero movimientoHelicopteroXML;
		try {
			String pathArchivo = "XML\\PersitenciaMovimientoHelicopteroPrueba.xml";
	        SAXBuilder builder = new SAXBuilder();
	        Document document = builder.build(pathArchivo);
	        
	        movimientoHelicopteroXML = MovimientoHelicoptero.cargarDesdeXML(document.getRootElement());
	        
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
			assertTrue(movimientoHelicopteroXML instanceof MovimientoHelicoptero);
		
	}
}