package modelo.movimientos;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import modelo.aviones.Avion;
import modelo.aviones.AvionLiviano;
import modelo.general.Posicion;
import modelo.juego.Escenario;
import modelo.pistas.Pista;
import modelo.pistas.PistaSimple;

import org.junit.Test;

public class MovimientoInteligenteTest {

	@Test
	public void testAvanzar(){
		Avion a =AvionLiviano.crearAvionLivianoInteligente(new Posicion(30,30), 10, 10, 0d, null);
		Pista p = new PistaSimple(new Posicion(70,30),10,0d);
		List<Pista> lp = new ArrayList<Pista>();
		lp.add(p);
		Escenario e = new Escenario(10,lp,null,null);
		
		a.avanzar();
		assertTrue(a.getPosicion().equals(new Posicion(40,30)));
		a.avanzar();
		assertTrue(a.getPosicion().equals(new Posicion(50,30)));
		a.agregarPosicionATrayectoria(new Posicion(50,5));
		a.avanzar();
		assertTrue(a.getPosicion().equals(new Posicion(50,20)));
		
		
	}
	
}
