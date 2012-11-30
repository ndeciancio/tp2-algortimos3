package modelo.general;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Trayectoria {

	private Queue<Posicion> posiciones = new LinkedList<Posicion>();;
	
	public Trayectoria(List<Posicion> posiciones){
		this.posiciones.addAll(posiciones);
	}
	
	public Trayectoria(){
	}
	
	public Posicion getPosicionSiguiente(){
			return posiciones.peek();
	}
	
	public void eliminarPosicion(){
		posiciones.poll();
	}
	
	public void agregarPosicion(Posicion pos){
		posiciones.add(pos);
	}
	
}
