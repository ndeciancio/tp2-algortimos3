import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import modelo.aviones.Avion;
import modelo.aviones.AvionHelicoptero;
import modelo.general.Mapa;
import modelo.general.Posicion;
import modelo.general.Trayectoria;
import modelo.pistas.Pista;
import modelo.pistas.PistaHelipuerto;

import org.junit.Test;

public class AvionHelicopteroTest {

	@Test
	public void testAvionHelicopteroDeberiaPoderAterrizarEnUnHelipuerto() throws Exception {
		Mapa mapaPrueba = Mapa.getInstance();
		mapaPrueba.resetMapa();
		
		Posicion posPista = new Posicion(10,3);
		Integer radio = 1;
		Pista pistaHelipuerto = new PistaHelipuerto(posPista,radio);
		Posicion posInicialAvion = new Posicion(3,3);
		Avion avion = AvionHelicoptero.crearAvionHelicopteroSimple(posInicialAvion,radio,2,0d);
		
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
	@Test
	public void testAvionHelicopteroDeberiaPoderQuedarceQuietoSiSeLeMandaUnaSeñal() throws Exception {
		Mapa mapaPrueba = Mapa.getInstance();
		mapaPrueba.resetMapa();
		
		Posicion posFinal = new Posicion(10,3);
		Integer radio = 1;

		Posicion posInicialAvion = new Posicion(3,3);
		AvionHelicoptero avion = AvionHelicoptero.crearAvionHelicopteroSimple(posInicialAvion,radio,2,0d);
		
		mapaPrueba.addAvion(avion);
		
		List<Posicion> posiciones = new ArrayList<Posicion>();
		posiciones.add(posFinal);
		Trayectoria trayectoria = new Trayectoria(posiciones);
		avion.setTrayectoria(trayectoria);
		
		avion.setOnSenalDeFreno();
		
		avion.vivir();
		
		assertTrue(avion.getPosicion().getX() == 3);
		assertTrue(avion.getPosicion().getY() == 3);
		
	}
}
