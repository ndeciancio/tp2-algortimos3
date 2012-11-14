import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AterrizajeTest {

	@Test
	public void testAvionLivianoDeberiaAterrizar() throws Exception {
		Posicion posPista = new Posicion(10,3);
		Integer radio = 1;
		Pista pistaSimple = new PistaSimple(posPista,radio);
		Posicion posInicialAvion = new Posicion(3,3);
		Avion avion = AvionLiviano.crearAvionLivianoSimple(posInicialAvion,radio,2,0d);
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

}
