package modelo.juego;

import java.util.ArrayList;
import java.util.List;

import modelo.exceptions.FalloEnFabricacionAvionException;
import modelo.factories.FactoryAvion;
import modelo.general.Posicion;
import modelo.pistas.Pista;
import modelo.pistas.PistaSimple;

import org.junit.Test;

public class EscenarioTest {

	@Test (expected = FalloEnFabricacionAvionException.class)
	public void testVivirDeberiaLanzarUnaExcepcionSiNoHayFabricasEnElJuego() {
		
		List <FactoryAvion> fabricasPrueba= new ArrayList <FactoryAvion> ();


		Integer cantidadDeAvionesPorNivel = 10;
		
		List <Pista> pistasPrueba= new ArrayList <Pista> ();
		Posicion posicionPrueba = new Posicion (3,3);
		Double direccionPrueba = 0d;
		PistaSimple pistaSimplePrueba = new PistaSimple (posicionPrueba, 1, direccionPrueba);
		pistasPrueba.add(pistaSimplePrueba);
		
		Escenario juegoPrueba = new Escenario(cantidadDeAvionesPorNivel, pistasPrueba, fabricasPrueba,null);
		juegoPrueba.vivir();

	}

}
