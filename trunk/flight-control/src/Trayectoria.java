import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;


public class Trayectoria {

	private Stack<Posicion> posiciones;
	
	public Trayectoria(List<Posicion> posiciones){
		this.posiciones = new Stack<Posicion>();
		this.posiciones.addAll(posiciones);
	}
	
	public Posicion getPosicionSiguiente(){
		try{
			return posiciones.peek();
		}catch(EmptyStackException e){
			return null;
		}
	}
	
	public void eliminarPosicion(){
		posiciones.pop();
	}
	
}
