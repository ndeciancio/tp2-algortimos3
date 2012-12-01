package modelo.general;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.jdom.Element;


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

	@SuppressWarnings("unchecked")
	public Element serializarXML() {
		Element trayectoriaSerializada = new Element ("Trayectoria");
		Iterator<Posicion> iteradorTrayectoria = this.posiciones.iterator();
		while (iteradorTrayectoria.hasNext()){
			Element posicionTrayectoria = iteradorTrayectoria.next().serializarXML();
			trayectoriaSerializada.getChildren().add(posicionTrayectoria);
		}
		return trayectoriaSerializada;
	}

	@SuppressWarnings("unchecked")
	public static Trayectoria cargarDesdeXML(Element elementoXML) {
		Trayectoria trayectoriaXML = new Trayectoria ();
		Iterator<Element> iteradorPosiciones = elementoXML.getChildren().iterator();
		while (iteradorPosiciones.hasNext()){
			Posicion posicionXML= Posicion.cargarDesdeXML(iteradorPosiciones.next());
			trayectoriaXML.agregarPosicion(posicionXML);
		}
		return trayectoriaXML;
	}
	
}
