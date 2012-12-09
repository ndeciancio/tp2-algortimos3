package modelo.movimientos;

import static org.junit.Assert.assertTrue;
import modelo.aviones.Avion;
import modelo.aviones.AvionLiviano;
import modelo.general.Posicion;

import org.junit.Test;

public class MovimientoSimpleTest {

	@Test
	public void testAvanzar(){
		Avion a =AvionLiviano.crearAvionLivianoSimple(new Posicion(30,30), 10, 10, 0d, null);
		a.avanzar();
		assertTrue(a.getPosicion().equals(new Posicion(40,30)));
		a.agregarPosicionATrayectoria(new Posicion(40,5));
		a.avanzar();
		assertTrue(a.getPosicion().equals(new Posicion(40,20)));
		a.avanzar();
		a.avanzar();
		a.avanzar();
		a.avanzar();
		a.avanzar();
		assertTrue(a.getPosicion().equals(new Posicion(40,10)));
		System.out.println(a.getDireccion());
		assertTrue(a.getDireccion().equals(-Math.PI*3/2));
	}
	
	
}
