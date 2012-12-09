package modelo.general;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import modelo.aviones.Avion;
import modelo.aviones.AvionLiviano;
import modelo.pistas.PistaSimple;

import org.junit.Test;

public class MapaTest {

	@Test
	public void testAddPista(){
		Mapa m = Mapa.getInstance();
		m.resetMapa();
		m.addPista(new PistaSimple(new Posicion(0,0),10,0d));
		assertTrue(m.getPistas().size() == 1);
	}
	
	@Test
	public void testAddAvion(){
		Mapa m = Mapa.getInstance();
		m.resetMapa();
		m.addAvion(AvionLiviano.crearAvionLivianoSimple(new Posicion(2,2), 10, 10, 0d, null));
		assertTrue(m.getCantidadAviones() == 1);
	}
	
	@Test
	public void testGetAvionesCercanos(){
		Mapa m = Mapa.getInstance();
		m.resetMapa();
		Avion a1 = AvionLiviano.crearAvionLivianoSimple(new Posicion(2,2), 10, 10, 0d, null);
		Avion a2 = AvionLiviano.crearAvionLivianoSimple(new Posicion(4,5), 10, 10, 0d, null);
		Avion a3 = AvionLiviano.crearAvionLivianoSimple(new Posicion(500,500), 10, 10, 0d, null);
		m.addAvion(a1);
		assertTrue(m.getAvionesCercanos(a2).size()>0);
		assertTrue(m.getAvionesCercanos(a3).size()==0);
	}
	
	@Test
	public void testSeleccionarAvion(){
		Mapa m = Mapa.getInstance();
		m.resetMapa();
		Avion a1 = AvionLiviano.crearAvionLivianoSimple(new Posicion(2,2), 10, 10, 0d, null);
		Avion a2 = AvionLiviano.crearAvionLivianoSimple(new Posicion(200,5), 10, 10, 0d, null);
		Avion a3 = AvionLiviano.crearAvionLivianoSimple(new Posicion(500,500), 10, 10, 0d, null);
		m.addAvion(a1);
		m.addAvion(a2);
		m.addAvion(a3);
		m.seleccionarAvion(new Posicion(4,4));
		assertTrue(a1.estaSeleccionado());
		assertFalse(a2.estaSeleccionado());
		assertFalse(a3.estaSeleccionado());
		
	}
	
	@Test
	public void testDesSeleccionarTodos(){
		Mapa m = Mapa.getInstance();
		m.resetMapa();
		Avion a1 = AvionLiviano.crearAvionLivianoSimple(new Posicion(2,2), 10, 10, 0d, null);
		Avion a2 = AvionLiviano.crearAvionLivianoSimple(new Posicion(200,5), 10, 10, 0d, null);
		Avion a3 = AvionLiviano.crearAvionLivianoSimple(new Posicion(500,500), 10, 10, 0d, null);
		m.addAvion(a1);
		m.addAvion(a2);
		m.addAvion(a3);
		a2.seleccionar();
		m.desSeleccionarTodos();
		assertFalse(a1.estaSeleccionado());
		assertFalse(a2.estaSeleccionado());
		assertFalse(a3.estaSeleccionado());
	}
	
	@Test
	public void testAgregarPosicionAlAvionSelecionado(){
		Mapa m = Mapa.getInstance();
		m.resetMapa();
		Avion a1 = AvionLiviano.crearAvionLivianoSimple(new Posicion(2,2), 10, 10, 0d, null);
		Avion a2 = AvionLiviano.crearAvionLivianoSimple(new Posicion(200,5), 10, 10, 0d, null);
		Avion a3 = AvionLiviano.crearAvionLivianoSimple(new Posicion(500,500), 10, 10, 0d, null);
		m.addAvion(a1);
		m.addAvion(a2);
		m.addAvion(a3);
		a2.seleccionar();
		m.agregarPosicionAlAvionSeleccionado(new Posicion(30,30));
		assertNotNull(a2.getTrayectoria().getPosicionSiguiente());
	}
	
}
