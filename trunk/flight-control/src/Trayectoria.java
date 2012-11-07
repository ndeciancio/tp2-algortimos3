import java.util.List;
import java.util.Stack;


public class Trayectoria {

	private Stack<Posicion> posiciones;
	
	public Trayectoria(List<Posicion> posiciones){
		this.posiciones = new Stack<Posicion>();
		this.posiciones.addAll(posiciones);
	}
	
	public Posicion getPosicionSiguiente(){
		return posiciones.pop();
	}
	
}
