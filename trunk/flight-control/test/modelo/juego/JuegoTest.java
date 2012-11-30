package modelo.juego;

import java.util.ArrayList;
import java.util.List;

import modelo.exceptions.FalloEnFabricacionAvionException;
import modelo.factories.FactoryAvion;
import modelo.general.Posicion;
import modelo.pistas.Pista;
import modelo.pistas.PistaSimple;

import org.junit.Test;

public class JuegoTest {

	@Test (expected = FalloEnFabricacionAvionException.class)
	public void testVivirDeberiaLanzarUnaExcepcionSiNoHayFabricasEnElJuego() {
		List <Pista> pistasPrueba= new ArrayList <Pista> ();
		List <FactoryAvion> fabricasPrueba= new ArrayList <FactoryAvion> ();
		Integer cantidadDeAvionesPorNivel = 10;
		Posicion posicionPrueba = new Posicion (3,3);
		PistaSimple pistaSimplePrueba = new PistaSimple (posicionPrueba, 1);
		pistasPrueba.add(pistaSimplePrueba);
		
		Juego juegoPrueba = new Juego (cantidadDeAvionesPorNivel, pistasPrueba, fabricasPrueba);
		juegoPrueba.vivir();

	}

}
