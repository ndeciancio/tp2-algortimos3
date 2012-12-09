package modelo.movimientos;

import static org.junit.Assert.assertTrue;
import modelo.aviones.Avion;
import modelo.aviones.AvionHelicoptero;
import modelo.general.Posicion;

import org.junit.Test;

public class MovimientoHelicopteroTest {

	@Test
	public void testAvanzar(){
		Avion a = AvionHelicoptero.crearAvionHelicopteroSimple(new Posicion(40,40), 10, 10, 0d, null);
		a.avanzar();
		assertTrue(a.getPosicion().equals(new Posicion(40,40)));
		a.agregarPosicionATrayectoria(new Posicion(40,5));
		a.avanzar();
		assertTrue(a.getPosicion().equals(new Posicion(40,30)));
		
	}
	
}
