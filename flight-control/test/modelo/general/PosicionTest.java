package modelo.general;

import static org.junit.Assert.*;

import org.junit.Test;

public class PosicionTest {

	@Test
	public void testCrearPosicionSobreBordeXDeDeberiaCrearUnaPosicionEnElBordeXConYEntre0y100() {

		boolean resultadoPrueba = false;
		Integer bordeX = 100;
		Integer bordeY = 100;
		Posicion posicionPruebaBorde = Posicion.crearPosicionSobreBordeX(bordeX, bordeY);
		int x = 0;
		
		while ( (!resultadoPrueba) && (x <= 100) ){
					
				Posicion posicionPruebaSobreY0 = new Posicion (x,0);
				Posicion posicionPruebaSobreY100 = new Posicion (x,100);
				boolean resultadoPruebaSobreY0 = posicionPruebaBorde.verificarIgualdad(posicionPruebaSobreY0);
				boolean resultadoPruebaSobreY100 = posicionPruebaBorde.verificarIgualdad(posicionPruebaSobreY100);
				resultadoPrueba = (resultadoPruebaSobreY0 || resultadoPruebaSobreY100); 
					
			x++;
		}
		
		

		assertTrue(resultadoPrueba);
		
	}
	
	@Test
	public void testCrearPosicionSobreBordeYDeDeberiaCrearUnaPosicionEnElBordeYConXEntre0y100() {

		boolean resultadoPrueba = false;
		Integer bordeX = 100;
		Integer bordeY = 100;
		Posicion posicionPruebaBorde = Posicion.crearPosicionSobreBordeY(bordeX, bordeY);
		int y = 0;
		
		while ( (!resultadoPrueba) && (y <= 100) ){
					
				Posicion posicionPruebaSobreY0 = new Posicion (0,y);
				Posicion posicionPruebaSobreY100 = new Posicion (100,y);
				boolean resultadoPruebaSobreY0 = posicionPruebaBorde.verificarIgualdad(posicionPruebaSobreY0);
				boolean resultadoPruebaSobreY100 = posicionPruebaBorde.verificarIgualdad(posicionPruebaSobreY100);
				resultadoPrueba = (resultadoPruebaSobreY0 || resultadoPruebaSobreY100); 
					
			y++;
		}
		
		

		assertTrue(resultadoPrueba);
		
	}
	
	@Test
	public void testcrearPrimerDestinoSobreBordeXDeDeberiaCrearUnaPosicionSiguienASuPosicionOriginalSobreXEnY0() {
		Posicion posicionSobreX = new Posicion (5,0);
		Posicion posicionSiguiente = Posicion.crearPrimerDestinoSobreBordeX(posicionSobreX);
		int posicionSiguienteSobreY = posicionSobreX.getY() + 1;
		
		assertTrue ( posicionSiguienteSobreY == posicionSiguiente.getY() );
		
	}
	
	@Test
	public void testcrearPrimerDestinoSobreBordeXDeDeberiaCrearUnaPosicionSiguienASuPosicionOriginalSobreXEnY100() {
		Posicion posicionSobreX = new Posicion (5,100);
		Posicion posicionSiguiente = Posicion.crearPrimerDestinoSobreBordeX(posicionSobreX);
		int posicionSiguienteSobreY = posicionSobreX.getY() - 1;
		
		assertTrue ( posicionSiguienteSobreY == posicionSiguiente.getY() );
		
	}
}
