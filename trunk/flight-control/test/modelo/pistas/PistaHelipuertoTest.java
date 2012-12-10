package modelo.pistas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import modelo.aviones.AvionHelicoptero;
import modelo.aviones.AvionLiviano;
import modelo.aviones.AvionPesado;
import modelo.general.Posicion;

import org.junit.Test;

public class PistaHelipuertoTest {

	@Test
	public void testPuedeAterrizar(){
		AvionLiviano a1 = AvionLiviano.crearAvionLivianoSimple(new Posicion(10,10), 2, 2, 0d, null);
		AvionPesado a2 = AvionPesado.crearAvionPesadoSimple(new Posicion(10,10), 2, 2, 0d, null);
		AvionHelicoptero a3 = AvionHelicoptero.crearAvionHelicopteroSimple(new Posicion(10,10), 2, 2, 0d, null);
		a3.agregarPosicionATrayectoria(new Posicion(15,10));
		Pista p = new PistaHelipuerto(new Posicion(15,10),2,0d);
		
		assertFalse(p.puedeAterrizar(a1));
		assertFalse(p.puedeAterrizar(a2));
		assertFalse(p.puedeAterrizar(a3));
		a3.avanzar();
		assertTrue(p.puedeAterrizar(a3));
	}
	
	
	@Test
	public void testEsLaPista(){
		AvionLiviano a1 = AvionLiviano.crearAvionLivianoSimple(new Posicion(10,10), 2, 2, 0d, null);
		AvionPesado a2 = AvionPesado.crearAvionPesadoSimple(new Posicion(10,10), 2, 2, 0d, null);
		AvionHelicoptero a3 = AvionHelicoptero.crearAvionHelicopteroSimple(new Posicion(10,10), 2, 2, 0d, null);
		Pista p = new PistaHelipuerto(new Posicion(15,10),2,0d);
		
		assertTrue(p.esLaPista(a3));
		assertFalse(p.esLaPista(a2));
		assertFalse(p.esLaPista(a1));
	}
	
}
