import java.util.ArrayList;
import java.util.List;


public class Mapa {

	private static Mapa singleton;
	
	private List<Pista> pistas;
	private List<Avion> aviones;
	
	private Mapa(){
	}
	
	public static Mapa getInstance(){
		if(singleton == null){
			singleton = new Mapa();
		}
		return singleton;
	}
	
	public void addPista(Pista p){
		if (pistas == null){
			pistas = new ArrayList<Pista>();
		}
		pistas.add(p);
	}
	
	public void addAvion(Avion a){
		if(aviones == null){
			aviones = new ArrayList<Avion>();
		}
		aviones.add(a);
	}
	
	public void resetMapa(){
		singleton = new Mapa();
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
	
}
