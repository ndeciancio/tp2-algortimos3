import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Trayectoria {

	private Queue<Posicion> posiciones;
	
	public Trayectoria(List<Posicion> posiciones){
		this.posiciones = new LinkedList<Posicion>();
		this.posiciones.addAll(posiciones);
	}
	
	public Posicion getPosicionSiguiente(){
			return posiciones.peek();
	}
	
	public void eliminarPosicion(){
		posiciones.poll();
	}
	
}
