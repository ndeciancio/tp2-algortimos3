package modelo.aviones;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import modelo.exceptions.ChoqueException;
import modelo.general.Mapa;
import modelo.general.Posicion;

import org.junit.Test;

public class AvionTest {

	@Test(expected=ChoqueException.class)
	public void testChequearChoques() throws Exception {
		Avion a1 = AvionLiviano.crearAvionLivianoSimple(new Posicion(10, 10),
				10, 5, 0d, null);
		Avion a2 = AvionLiviano.crearAvionLivianoSimple(new Posicion(22, 10),
				10, 5, 0d, null);
		Mapa.getInstance().addAvion(a1);
		Mapa.getInstance().addAvion(a2);
		try {
			a2.chequearChoques();
		} catch (Exception e) {
			assertTrue(false);
		}
		a1.avanzar();
		a2.chequearChoques();

	}
	
	@Test 
	public void testEstaCercaDe(){
		Avion a1 = AvionLiviano.crearAvionLivianoSimple(new Posicion(10, 10),
				10, 5, 0d, null);
		Avion a2 = AvionLiviano.crearAvionLivianoSimple(new Posicion(22, 10),
				10, 5, 0d, null);
		
		assertFalse(a1.estaCercaDe(a2));
		a1.avanzar();
		assertTrue(a1.estaCercaDe(a2));
	}
	

}
