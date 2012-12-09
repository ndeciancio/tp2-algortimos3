package modelo.aviones;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import modelo.aviones.Avion;
import modelo.aviones.AvionHelicoptero;
import modelo.general.Mapa;
import modelo.general.Posicion;
import modelo.general.Trayectoria;
import modelo.juego.Escenario;
import modelo.pistas.Pista;
import modelo.pistas.PistaHelipuerto;
import modelo.pistas.PistaSimple;

import org.junit.Test;

import vista.FlightControl;

public class AvionHelicopteroTest {

	@Test
	public void testAvionHelicopteroDeberiaPoderAterrizarEnUnHelipuerto() throws Exception {
		Mapa mapaPrueba = Mapa.getInstance();
		mapaPrueba.resetMapa();
		
		Posicion posPista = new Posicion(10,3);
		Integer radio = 1;
		Double direccion = 0d;
		Pista pistaHelipuerto = new PistaHelipuerto(posPista,radio,direccion);
		List<Pista> ps = new ArrayList<Pista>();
		ps.add(pistaHelipuerto);
		FlightControl fc = new FlightControl();
		fc.setUpGame();
		Escenario e = new Escenario(10, ps, null,fc);
		
		Posicion posInicialAvion = new Posicion(3,3);
		Avion avion = AvionHelicoptero.crearAvionHelicopteroSimple(posInicialAvion,radio,2,0d, e);
		
		mapaPrueba.addAvion(avion);
		mapaPrueba.addPista(pistaHelipuerto);
		
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

}
