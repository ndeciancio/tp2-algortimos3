package modelo.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.aviones.Avion;
import modelo.pistas.Pista;

public class Mapa {

	private static Mapa singleton;

	private List<Pista> pistas = new ArrayList<Pista>();
	private List<Avion> aviones = new ArrayList<Avion>();
	private Integer dimensionEnX = 800;
	private Integer dimensionEnY = 600;

	private Mapa() {
	}

	public static Mapa getInstance() {
		if (singleton == null) {
			singleton = new Mapa();
		}
		return singleton;
	}

	public void addPista(Pista p) {
		pistas.add(p);
	}

	public void addAvion(Avion a) {
		synchronized (aviones) {
			aviones.add(a);
		}
	}

	public void resetMapa() {
		singleton.aviones = new ArrayList<Avion>();
		singleton.pistas = new ArrayList<Pista>();
	}

	public List<Avion> getAvionesCercanos(Avion a) {
		List<Avion> avionesCercanos = new ArrayList<Avion>();
		synchronized (aviones) {
			Iterator<Avion> it = aviones.iterator();
			while (it.hasNext()) {
				Avion avion = it.next();
				if (avion.estaCercaDe(a)) {
					avionesCercanos.add(avion);
				}
			}
		}
		return avionesCercanos;
	}

	public List<Pista> getPistas() {
		return pistas;
	}

	public Integer obtenerCantidadAviones() {
		return aviones.size();
	}

	public void sacarAvionesAterrizados() {

		Iterator<Avion> iteradorAviones = aviones.iterator();

		while (iteradorAviones.hasNext()) {

			if (iteradorAviones.next().estaAterrizado()) {
				iteradorAviones.remove();
			}
		}

	}

	public Integer getBordeX() {
		return dimensionEnX;
	}

	public Integer getBordeY() {
		return dimensionEnY;
	}

	public Boolean seleccionarAvion(Posicion pos) {
		Iterator<Avion> it = aviones.iterator();
		while (it.hasNext()) {
			Avion a = it.next();
			if (pos.estaCercaDe(a.getPosicion(), a.getRadio())) {
				System.out.println("Seleccionado");
				desSeleccionarTodos();
				a.seleccionar();
				return true;
			}
		}

		return false;
	}

	public void desSeleccionarTodos() {
		Iterator<Avion> it = aviones.iterator();
		while (it.hasNext()) {
			Avion a = it.next();
			a.desSeleccionar();
		}
	}

	public void agregarPosicionAlAvionSeleccionado(Posicion pos) {
		Iterator<Avion> it = aviones.iterator();
		while (it.hasNext()) {
			Avion a = it.next();
			if (a.estaSeleccionado()) {
				System.out.println("Agregando Posicion");
				a.agregarPosicionATrayectoria(pos);
			}
		}

	}

}
