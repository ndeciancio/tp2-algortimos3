package modelo.aviones;
import static org.junit.Assert.assertTrue;
import modelo.general.Posicion;
import modelo.movimientos.MovimientoHelicoptero;
import modelo.pistas.Pista;
import modelo.pistas.PistaHelipuerto;

import org.junit.Test;

public class AvionHelicopteroTest {

	@Test
	public void testCrearAvionHelicoptero(){
	
		Avion a = AvionHelicoptero.crearAvionHelicopteroSimple(new Posicion(10,10),2, 3,0d,null);
		assertTrue(a.getDireccion().equals(0d));
		assertTrue(a.getMovimiento().getClass().equals(MovimientoHelicoptero.class));
		assertTrue(a.getPosicion().equals(new Posicion(10,10)));
		assertTrue(a.getRadio().equals(2));
		
		
	}
		
	@Test
	public void testPuedoAterrizar(){
		Avion a = AvionHelicoptero.crearAvionHelicopteroSimple(new Posicion(10,10),2, 3,0d,null);
		Pista p = new PistaHelipuerto(new Posicion(10,10),10,0d);
		assertTrue(a.intentarAterrizar(p));
		assertTrue(a.puedoAterrizarEnEstaPista(p));
		
	}

}
