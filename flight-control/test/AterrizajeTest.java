import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AterrizajeTest {

	@Test
	public void testAvionLivianoDeberiaAterrizar() {
		Posicion posPista = new Posicion(4,3);
		Pista pistaLiviana = new PistaLiviana(posPista);
		Posicion posInicialAvion = new Posicion(3,3);
		Avion avion = new AvionLiviano(posInicialAvion,pistaLiviana);
		List<Posicion> posiciones = new ArrayList<Posicion>();
		posiciones.add(posPista);
		Trayectoria trayectoria = new Trayectoria(posiciones);
		avion.setTrayectoria(trayectoria);
		avion.avanzar();
		avion.chequearAterrizaje();
		assertTrue(avion.estaAterrizado());
		
		
	}

}
