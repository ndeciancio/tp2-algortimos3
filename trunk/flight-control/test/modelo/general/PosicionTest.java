package modelo.general;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PosicionTest {

	@Test
	public void testCrearPosicionSobreBordeDeberiaCrearUnaPosicionEnElBorde() {

		Integer bordeX = 100;
		Integer bordeY = 100;
		Posicion pos = Posicion.crearPosicionSobreBorde(bordeX, bordeY);

		Boolean flag = pos.getX().equals(0) || pos.getX().equals(bordeX)
				|| pos.getY().equals(0) || pos.getY().equals(bordeY);

		assertTrue(flag);

	}

	@Test
	public void testCrearPrimerDestinoDeberiaCrearUnaPosicionDentroDelMapa() {
		Integer bordeX = 100;
		Integer bordeY = 100;
		Posicion pos = Posicion.crearPrimerDestino(bordeX, bordeY);
		Boolean flag = (pos.getX() >= 0 && pos.getX() <= bordeX)
				|| (pos.getY() >= 0 || pos.getY() <= bordeY);

		assertTrue(flag);

	}
	
	
	@Test
	public void testCrearPrimerDireccion(){
		Integer bordeX = 100;
		Integer bordeY = 100;
		Posicion pos = new Posicion(0,50);
		Double dir = Posicion.crearPrimerDireccion(pos, bordeX, bordeY);
		assertTrue(dir >= -Math.PI && dir <= Math.PI);
	}
	
	@Test
	public void testEstaCercaDe(){
		Posicion pos1 =new Posicion(30,30);
		Posicion pos2 = new Posicion(40,40);
		
		assertTrue(pos1.estaCercaDe(pos2, 15));
		assertFalse(pos1.estaCercaDe(pos2, 10));
		
	}
	
	@Test
	public void testMoverEnX(){
		Posicion pos = new Posicion(30,30);
		pos.moverEnX(2);
		assertTrue(pos.equals(new Posicion(32,30)));
		
	}
	
	@Test
	public void testMoverEnY(){
		Posicion pos = new Posicion(30,30);
		pos.moverEnY(2);
		assertTrue(pos.equals(new Posicion(30,32)));
		
	}

}
