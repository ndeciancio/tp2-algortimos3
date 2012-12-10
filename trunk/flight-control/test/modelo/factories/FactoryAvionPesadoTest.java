package modelo.factories;

import static org.junit.Assert.assertNotNull;
import modelo.aviones.Avion;
import modelo.general.Mapa;

import org.junit.Test;

public class FactoryAvionPesadoTest {

	@Test
	public void testFabricarAvion() {
		for (int i = 0; i < 20; i++) {
			FactoryAvionPesado f = new FactoryAvionPesado();
			Avion a = f.fabricarAvion(Mapa.getInstance(), null);
			assertNotNull(a);
			assertNotNull(a.getPosicion());
			assertNotNull(a.getRadio());
			assertNotNull(a.getMovimiento());
		}
	}
	
}
