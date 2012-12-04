package persistencia;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import modelo.factories.FactoryAvion;
import modelo.factories.FactoryAvionLiviano;
import modelo.general.Posicion;
import modelo.juego.Escenario;
import modelo.pistas.Pista;
import modelo.pistas.PistaSimple;

import org.junit.Test;

import vista.FlightControl;

import control.Archivador;

public class ArchivadorTest {

	@Test
	public void unArchivadorPodriaGuardarElEstadoDeUnJuego() throws IOException{
		//PrepararEscenario
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

		
		
		String pathArchivador = "XML\\Juego.xml";
		Archivador archivadorPrueba = new Archivador(pathArchivador);
		archivadorPrueba.archivarJuego(juegoAPersistir);
	}
	
	@Test
	public 	void unArchivadorPodriaCrearUnJuegoApartirDeUnXML(){
		FlightControl simulador = new FlightControl ();
		String pathArchivador = "XML\\Juego.xml";
		Archivador archivadorPrueba = new Archivador(pathArchivador);
		
		Escenario juegoDelXML = archivadorPrueba.crearJuegoDesdeXMLConVista(simulador);
		
		assertTrue (juegoDelXML instanceof Escenario);
		
	}
}
