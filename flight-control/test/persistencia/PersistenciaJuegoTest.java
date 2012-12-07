package persistencia;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import modelo.juego.Escenario;
import modelo.pistas.Pista;
import modelo.pistas.PistaSimple;
import modelo.factories.FactoryAvion;
import modelo.factories.FactoryAvionLiviano;
import modelo.general.Posicion;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.junit.Test;

import vista.FlightControl;



public class PersistenciaJuegoTest {
	@Test
	public void testUnAvionLivianoSimplePodriaPoderSerializarce () throws IOException{
	
		String pathArchivo = "XML\\PersitenciaJuegoPrueba.xml";
		FlightControl simulador = new FlightControl ();
		
		List <FactoryAvion> fabricasPrueba= new ArrayList <FactoryAvion> ();
		FactoryAvionLiviano fabricaLivianaPrueba = new FactoryAvionLiviano();
		fabricasPrueba.add(fabricaLivianaPrueba);

		Integer cantidadDeAvionesPorNivel = 10;
		
		List <Pista> pistasPrueba= new ArrayList <Pista> ();
		Posicion posicionPrueba = new Posicion (50,50);
		Double direccionPrueba = 0d;
		PistaSimple pistaSimplePrueba = new PistaSimple (posicionPrueba, 1, direccionPrueba);
		pistasPrueba.add(pistaSimplePrueba);
		
		Escenario juegoAPersistir = new Escenario(cantidadDeAvionesPorNivel, pistasPrueba, fabricasPrueba,simulador);

		for (int X=1;X==20;X++)
		{
			juegoAPersistir.vivir();
		}

		Element elementoApersistir = juegoAPersistir.serializarXML();
		Document document = new Document(elementoApersistir);
	
		XMLOutputter outputter = new XMLOutputter();
		outputter.setFormat(Format.getPrettyFormat());
    
		FileWriter writer = new FileWriter(pathArchivo);
		outputter.output(document,writer);
		writer.close();

	}



}