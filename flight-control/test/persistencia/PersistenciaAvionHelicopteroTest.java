package persistencia;
import static org.junit.Assert.assertTrue;

import java.io.FileWriter;
import java.io.IOException;

import modelo.aviones.AvionHelicoptero;
import modelo.general.Posicion;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.junit.Test;

//El escenario no se persiste por que sera asignado en el momento de cargar el juego
//Se contempla tambien que un avion no puede vivir sin un juego pero eso no
//tiene relevancia con la persistencia

public class PersistenciaAvionHelicopteroTest {
	@Test
	public void testUnAvionLivianoSimplePodriaPoderSerializarce () throws IOException{
	
		String pathArchivo = "XML\\PersitenciaAvionHelicopteroSimplePrueba.xml";
		
		Posicion posicionAPersistir = new Posicion (2,2);
		Posicion posicionTrayectoriaAPersistir = new Posicion (3,3);
		Integer radioAPersistir = 5;
		Integer velocidadAPersistir = 10;
        Double direccionAPersistir = 0d;
        
        AvionHelicoptero avionAPersistir = AvionHelicoptero.crearAvionHelicopteroSimple(posicionAPersistir, radioAPersistir, velocidadAPersistir, direccionAPersistir, null);
        avionAPersistir.agregarPosicionATrayectoria(posicionTrayectoriaAPersistir);
        
		Element elementoApersistir = avionAPersistir.serializarXML();
		Document document = new Document(elementoApersistir);
	
		XMLOutputter outputter = new XMLOutputter();
		outputter.setFormat(Format.getPrettyFormat());
    
		FileWriter writer = new FileWriter(pathArchivo);
		outputter.output(document,writer);
		writer.close();
	}
	
@Test
	public void testUnLivianoSePodriaCrearApartirDeUnArchivoXML() throws IOException, JDOMException
	{
	AvionHelicoptero avionXML;
		try {
			String pathArchivo = "XML\\PersitenciaAvionHelicopteroSimplePrueba.xml";
	        SAXBuilder builder = new SAXBuilder();
	        Document document = builder.build(pathArchivo);
	        
	        avionXML = AvionHelicoptero.cargarDesdeXML(document.getRootElement(), null);
	        
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
			assertTrue(avionXML instanceof AvionHelicoptero);

	}
}
