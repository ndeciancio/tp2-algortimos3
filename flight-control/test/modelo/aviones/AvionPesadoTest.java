package modelo.aviones;

import static org.junit.Assert.assertTrue;
import modelo.general.Posicion;
import modelo.movimientos.MovimientoInteligente;
import modelo.movimientos.MovimientoSimple;
import modelo.pistas.Pista;
import modelo.pistas.PistaLarga;

import org.junit.Test;

public class AvionPesadoTest {

	@Test
	public void testCrearAvionPesadoSimple(){
	
		Avion a = AvionPesado.crearAvionPesadoSimple(new Posicion(10,10),2, 3,0d,null);
		assertTrue(a.getDireccion().equals(0d));
		assertTrue(a.getMovimiento().getClass().equals(MovimientoSimple.class));
		assertTrue(a.getPosicion().equals(new Posicion(10,10)));
		assertTrue(a.getRadio().equals(2));
		
		
	}
	
	@Test
	public void testCrearAvionLivianoInteligente(){
		
		Avion a = AvionPesado.crearAvionPesadoInteligente(new Posicion(10,10),2, 3,0d,null);
		assertTrue(a.getDireccion().equals(0d));
		assertTrue(a.getMovimiento().getClass().equals(MovimientoInteligente.class));
		assertTrue(a.getPosicion().equals(new Posicion(10,10)));
		assertTrue(a.getRadio().equals(2));
		
	}
	
	@Test
	public void testPuedoAterrizar(){
		Avion a = AvionPesado.crearAvionPesadoSimple(new Posicion(10,10),2, 3,0d,null);
		Pista p = new PistaLarga(new Posicion(10,10),10,0d);
		assertTrue(a.intentarAterrizar(p));
		assertTrue(a.puedoAterrizarEnEstaPista(p));
		
	}
	
}
