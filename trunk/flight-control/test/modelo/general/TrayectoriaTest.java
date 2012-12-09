package modelo.general;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class TrayectoriaTest {

	@Test
	public void testAgregarPosicion(){
		Trayectoria t = new Trayectoria();
		t.agregarPosicion(new Posicion(30,30));
		assertNotNull(t.getPosicionSiguiente());
	}
	
	@Test
	public void testEliminarPosicion(){
		Trayectoria t = new Trayectoria();
		t.agregarPosicion(new Posicion(30,30));
		t.eliminarPosicion();
		assertNull(t.getPosicionSiguiente());
	}
	
}
