package integracion;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import modelo.aviones.Avion;
import modelo.aviones.AvionLiviano;
import modelo.general.Mapa;
import modelo.general.Posicion;
import modelo.juego.Escenario;
import modelo.pistas.Pista;

import org.junit.Test;

import vista.FlightControl;


public class ChoqueTest {
	
	@Test
	public void testDosAvionesDeFrenteDeberianChocar() throws Exception{
		Mapa.getInstance().resetMapa();
		Posicion pos1 = new Posicion(2,2);
		Posicion pos2 = new Posicion(15,2);
		FlightControl fc = new FlightControl();
		fc.setUpGame();
		Escenario e = new Escenario(10, new ArrayList<Pista>(), null,fc);
		Avion a = AvionLiviano.crearAvionLivianoSimple(pos1, 2, 2, 0d,e);
		Avion b = AvionLiviano.crearAvionLivianoSimple(pos2, 2, 2, Math.PI,e);
		Mapa.getInstance().addAvion(a);
		Mapa.getInstance().addAvion(b);
		a.agregarPosicionATrayectoria(pos2);
		b.agregarPosicionATrayectoria(pos1);
		for(int i=0;i<50;i++){
			a.vivir();
			if(e.getPerdido()){
				break;
			}
			b.vivir();
			if(e.getPerdido()){
				break;
			}
		}
		assertTrue(e.getPerdido());
		
	}
	
}
