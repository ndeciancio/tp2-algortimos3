package persistencia;
import static org.junit.Assert.assertTrue;

import java.io.FileWriter;

import java.io.IOException;

import modelo.movimientos.Movimiento;
import modelo.movimientos.MovimientoSimple;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.junit.Test;

public class PersistenciaMovimientoTest {
	@Test
	public void testUnMovimientoSimplePodriaPoderSerializarce () throws IOException{
	
		String pathArchivo = "XML\\PersitenciaMovimientoSimplePrueba.xml";
		
		Integer velocidadAPersistir = 10;
        Double direccionAPersistir = 0d;
        
        MovimientoSimple moviemientoAPersistir = new MovimientoSimple (velocidadAPersistir, direccionAPersistir);
        
        
		Element posicioAPersistir = moviemientoAPersistir.serializarXML();
		Document document = new Document(posicioAPersistir);
	
		XMLOutputter outputter = new XMLOutputter();
		outputter.setFormat(Format.getPrettyFormat());
    
		FileWriter writer = new FileWriter(pathArchivo);
		outputter.output(document,writer);
		writer.close();
	}
	
	@Test
	public void testUnaPosicionSePodriaCrearApartirDeUnArchivoXML() throws IOException
	{
		MovimientoSimple movimientoSimpleXML;
		try {
			String pathArchivo = "XML\\PersitenciaMovimientoSimplePrueba.xml";
	        SAXBuilder builder = new SAXBuilder();
	        Document document = builder.build(pathArchivo);
	        
	        movimientoSimpleXML = MovimientoSimple.cargarDesdeXML(document.getRootElement());
	        
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
			assertTrue(movimientoSimpleXML instanceof MovimientoSimple);
		
	}
}
