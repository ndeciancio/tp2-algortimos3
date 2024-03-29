package integracion;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import modelo.aviones.Avion;
import modelo.aviones.AvionLiviano;
import modelo.general.Mapa;
import modelo.general.Posicion;
import modelo.general.Trayectoria;
import modelo.juego.Escenario;
import modelo.pistas.Pista;
import modelo.pistas.PistaSimple;

import org.junit.Test;

import vista.FlightControl;

public class AterrizajeTest {

	@Test
	public void testAvionLivianoDeberiaAterrizar() throws Exception {
		Mapa.getInstance().resetMapa();
		Posicion posPista = new Posicion(10,3);
		Integer radio = 1;
		Double direccion = 0d;
		Pista pistaSimple = new PistaSimple(posPista,radio,direccion);
		List<Pista> ps = new ArrayList<Pista>();
		ps.add(pistaSimple);
		FlightControl fc = new FlightControl();
		fc.setUpGame();
		Escenario e = new Escenario(10, ps, null,fc);
		Posicion posInicialAvion = new Posicion(3,3);
		Avion avion = AvionLiviano.crearAvionLivianoSimple(posInicialAvion,radio,2,0d, e);
		Mapa.getInstance().addAvion(avion);
		Mapa.getInstance().addPista(pistaSimple);
		List<Posicion> posiciones = new ArrayList<Posicion>();
		posiciones.add(posPista);
		Trayectoria trayectoria = new Trayectoria(posiciones);
		avion.setTrayectoria(trayectoria);
		avion.vivir();
		assertFalse(avion.estaAterrizado());
		avion.vivir();
		assertFalse(avion.estaAterrizado());
		avion.vivir();
		assertTrue(avion.estaAterrizado());
		
	}
	
	@Test
	public void testAvionLivianoPasaPorVariasPosicionesDeberiaAterrizar() throws Exception {
		Mapa.getInstance().resetMapa();
		Posicion posPista = new Posicion(20,3);
		Integer radio = 1;
		Double direccion = 0d;
		Pista pistaSimple = new PistaSimple(posPista,radio,direccion);
		List<Pista> ps = new ArrayList<Pista>();
		ps.add(pistaSimple);
		FlightControl fc = new FlightControl();
		fc.setUpGame();
		Escenario e = new Escenario(10, ps, null,fc);
		Posicion posInicialAvion = new Posicion(3,3);
		Avion avion = AvionLiviano.crearAvionLivianoSimple(posInicialAvion,radio,2,0d, e);
		Mapa.getInstance().addAvion(avion);
		Mapa.getInstance().addPista(pistaSimple);
		List<Posicion> posiciones = new ArrayList<Posicion>();
		Posicion pos1 = new Posicion(5,3);
		Posicion pos2 = new Posicion(8,3);
		posiciones.add(pos1);
		posiciones.add(pos2);
		posiciones.add(posPista);
		Trayectoria trayectoria = new Trayectoria(posiciones);
		avion.setTrayectoria(trayectoria);
		for(int i=0;i<7;i++){
			avion.vivir();
			assertFalse(avion.estaAterrizado());
		}
		avion.vivir();
		assertTrue(avion.estaAterrizado());
		
	}

}
