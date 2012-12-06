import java.util.ArrayList;
import java.util.List;

import modelo.aviones.Avion;
import modelo.aviones.AvionLiviano;
import modelo.exceptions.ChoqueException;
import modelo.general.Mapa;
import modelo.general.Posicion;
import modelo.general.Trayectoria;

import org.junit.Test;


public class ChoqueTest {
	
	@Test(expected = ChoqueException.class)
	public void testDosAvionesDeFrenteDeberianChocar() throws Exception{
		Mapa.getInstance().resetMapa();
		Posicion pos1 = new Posicion(2,2);
		Posicion pos2 = new Posicion(15,2);
		Avion a = AvionLiviano.crearAvionLivianoSimple(pos1, 2, 2, 0d,null);
		Avion b = AvionLiviano.crearAvionLivianoSimple(pos2, 2, 2, Math.PI,null);
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
		for(;;){
			a.vivir();
			b.vivir();
		}
		
	}
	
}