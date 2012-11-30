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
	
	private Mapa(){
	}
	
	public static Mapa getInstance(){
		if(singleton == null){
			singleton = new Mapa();
		}
		return singleton;
	}
	
	public void addPista(Pista p){
		pistas.add(p);
	}
	
	public void addAvion(Avion a){
		aviones.add(a);
	}
	
	public void resetMapa(){
		singleton.aviones = new ArrayList<Avion>();
		singleton.pistas = new ArrayList<Pista>();
	}
	
	public List<Avion> getAvionesCercanos(Avion a){
		List<Avion> avionesCercanos = new ArrayList<Avion>();
		for(Avion avion : aviones){
			if(avion.estaCercaDe(a)){
				avionesCercanos.add(avion);
			}
		}
		return avionesCercanos;
	}
	
	public List<Pista> getPistas(){
		return pistas;
	}
	
	public Integer obtenerCantidadAviones(){
		return aviones.size();
	}

	public void sacarAvionesAterrizados() {
		
		Iterator <Avion> iteradorAviones = aviones.iterator();
		
		while (iteradorAviones.hasNext()){

			if (iteradorAviones.next().estaAterrizado())
			{
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




}
