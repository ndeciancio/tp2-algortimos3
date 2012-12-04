package persistencia;
import static org.junit.Assert.assertTrue;

import java.io.FileWriter;
import java.io.IOException;

import modelo.movimientos.MovimientoInteligente;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.junit.Test;

public class PersistenciaMovimientoInteligenteTest {
	@Test
	public void testUnMovimientoInteligentePodriaPoderSerializarce () throws IOException{
	
		String pathArchivo = "XML\\PersitenciaMovimientoInteligentePrueba.xml";
		
		Integer velocidadAPersistir = 10;
        Double direccionAPersistir = 0d;
        
        MovimientoInteligente moviemientoAPersistir = new MovimientoInteligente (velocidadAPersistir, direccionAPersistir);
        
        
		Element elementoApersistir = moviemientoAPersistir.serializarXML();
		Document document = new Document(elementoApersistir);
	
		XMLOutputter outputter = new XMLOutputter();
		outputter.setFormat(Format.getPrettyFormat());
    
		FileWriter writer = new FileWriter(pathArchivo);
		outputter.output(document,writer);
		writer.close();
	}
	
	@Test
	public void testUnMovimientoInteligenteSePodriaCrearApartirDeUnArchivoXML() throws IOException
	{
		MovimientoInteligente movimientoInteligenteXML;
		try {
			String pathArchivo = "XML\\PersitenciaMovimientoInteligentePrueba.xml";
	        SAXBuilder builder = new SAXBuilder();
	        Document document = builder.build(pathArchivo);
	        
	        movimientoInteligenteXML = MovimientoInteligente.cargarDesdeXML(document.getRootElement());
	        
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
			assertTrue(movimientoInteligenteXML instanceof MovimientoInteligente);
		
	}
}