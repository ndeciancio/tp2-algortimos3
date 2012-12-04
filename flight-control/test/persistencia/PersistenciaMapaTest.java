package persistencia;


import java.io.FileWriter;
import java.io.IOException;

import modelo.general.Mapa;

import modelo.aviones.AvionHelicoptero;
import modelo.aviones.AvionLiviano;
import modelo.pistas.*;

import modelo.general.Posicion;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.junit.Test;

//Por ahora vamos a guardar los aviones en el mapa, la dimensiones van a quedar 
//staticas dentro de la clase

public class PersistenciaMapaTest {
	@Test
	public void testUnAvionLivianoSimplePodriaPoderSerializarce () throws IOException{
		String pathArchivo = "XML\\PersitenciaMapaPrueba.xml";
		//arrange
		
		Mapa mapaApersistir = Mapa.getInstance();
		mapaApersistir.resetMapa();
		
		Integer radioApersistir = 5;
        Posicion posicionSimpleAPersistir = new Posicion (2,2);
        Double direccionSimpleApersistir = 0d;
        Posicion posicionHelipuertoAPersistir = new Posicion (4,4);
        Double direccionHelipuertoAPersitir = 1d;
        
        PistaSimple pistaSimpleAPersistir = new PistaSimple (posicionSimpleAPersistir, radioApersistir,direccionSimpleApersistir);
        PistaHelipuerto pistaHelipuertoAPersitir = new PistaHelipuerto(posicionHelipuertoAPersistir, radioApersistir, direccionHelipuertoAPersitir);
        
		Posicion posicionHelicopteroAPersistir = new Posicion (2,2);
		Posicion posicionTrayectoriaHelicopteroAPersistir = new Posicion (3,3);
		
		Posicion posicionAvionAPersistir = new Posicion (4,4);
		Posicion posicionTrayectoriaAvionAPersistir = new Posicion (5,5);
		
		Integer velocidadAPersistir = 10;
        Double direccionHelicopteroAPersistir = 0d;
        Double direccionAvionAPersistir = 1d;
        
        AvionHelicoptero helicopteroDelMapaApersistir = AvionHelicoptero.crearAvionHelicopteroSimple(posicionHelicopteroAPersistir, radioApersistir, velocidadAPersistir, direccionHelicopteroAPersistir, null);
        AvionLiviano avionDelMapaApersistir = AvionLiviano.crearAvionLivianoSimple(posicionAvionAPersistir, radioApersistir, velocidadAPersistir, direccionAvionAPersistir, null);

        helicopteroDelMapaApersistir.agregarPosicionATrayectoria(posicionTrayectoriaHelicopteroAPersistir);
        avionDelMapaApersistir.agregarPosicionATrayectoria(posicionTrayectoriaAvionAPersistir);
        //act
        
        mapaApersistir.addAvion(avionDelMapaApersistir);
        mapaApersistir.addAvion(helicopteroDelMapaApersistir);
        mapaApersistir.addPista(pistaHelipuertoAPersitir);
        mapaApersistir.addPista(pistaSimpleAPersistir);
		
		Element elementoApersistir = mapaApersistir.serializarXML();
		Document document = new Document(elementoApersistir);
	
		XMLOutputter outputter = new XMLOutputter();
		outputter.setFormat(Format.getPrettyFormat());
    
		FileWriter writer = new FileWriter(pathArchivo);
		outputter.output(document,writer);
		writer.close();
	}
	

}
