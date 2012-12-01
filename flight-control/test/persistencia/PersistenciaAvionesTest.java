package persistencia;

import java.io.FileWriter;
import java.io.IOException;

import modelo.aviones.AvionLiviano;
import modelo.general.Mapa;
import modelo.general.Posicion;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.junit.Test;

public class PersistenciaAvionesTest {

	
	@Test
	public void testUnAvionLivianoPodriaPoderPersistirce () throws IOException{
		String pathArchivo = "XML\\PersitenciaAvionPrueba.xml";
		Integer velocidad = 5;
		Integer radio = 15;
		Posicion posicionInicial = new Posicion (1,2); 
		Double direccion = Posicion.crearPrimerDireccion(posicionInicial, Mapa.getInstance().getBordeX(), Mapa.getInstance().getBordeY());
		
		AvionLiviano avionPrueba= AvionLiviano.crearAvionLivianoSimple(posicionInicial, radio, velocidad, direccion, null);
        
        
		Element posicioAPersistir = avionPrueba.serializarXML();
		Document document = new Document(posicioAPersistir);
		
        XMLOutputter outputter = new XMLOutputter();
        outputter.setFormat(Format.getPrettyFormat());
        
        FileWriter writer = new FileWriter(pathArchivo);
        outputter.output(document,writer);
        writer.close();
	}
}
