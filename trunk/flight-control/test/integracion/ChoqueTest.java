package integracion;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import modelo.aviones.Avion;
import modelo.aviones.AvionLiviano;
import modelo.general.Mapa;
import modelo.general.Posicion;
import modelo.general.Trayectoria;
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
		List<Posicion> posiciones1 = new ArrayList<Posicion>();
		List<Posicion> posiciones2 = new ArrayList<Posicion>();
		posiciones1.add(pos2);
		posiciones2.add(pos1);
		Trayectoria t1 = new Trayectoria(posiciones1);
		Trayectoria t2 = new Trayectoria(posiciones2);
		a.setTrayectoria(t1);
		b.setTrayectoria(t2);
		for(int i=0;i<50;i++){
			a.vivir();
			b.vivir();
			if(e.getPerdido()){
				break;
			}
		}
		assertTrue(e.getPerdido());
		
	}
	
}
