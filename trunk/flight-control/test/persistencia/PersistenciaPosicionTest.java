package persistencia;

import static org.junit.Assert.assertTrue;

import java.io.FileWriter;
import java.io.IOException;


import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.junit.Test;


import modelo.general.Posicion;

public class PersistenciaPosicionTest {

		@Test
		public void testUnaPosicionPodriaGuardarSuEstadoEnUnArchivoXML() throws IOException
		{
			String pathArchivo = "XML\\PersitenciaPosicionPrueba.xml";
			Posicion posicion = new Posicion (1,2);
	        
	        
			Element posicioAPersistir = posicion.serializarXML();
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
			Posicion posicionPrueba = new Posicion (1,2);
			Posicion posicionXML;
			try {
				String pathArchivo = "XML\\PersitenciaPosicionPrueba.xml";
		        SAXBuilder builder = new SAXBuilder();
		        Document document = builder.build(pathArchivo);
		        
		        posicionXML = Posicion.cargarDesdeXML(document.getRootElement());
		        
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
			
			assertTrue (posicionPrueba.getX() == posicionXML.getX());
			assertTrue (posicionPrueba.getY() == posicionXML.getY());
			
		}
}
