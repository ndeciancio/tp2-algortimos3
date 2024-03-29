package modelo.general;

import java.util.Random;

import org.jdom.Attribute;
import org.jdom.Element;

public class Posicion {
	private static Random r = new Random();

	private Integer x;
	private Integer y;

	public Posicion(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	public void moverEnX(Integer x) {
		this.x += x;
	}

	public void moverEnY(Integer y) {
		this.y += y;
	}

	public Boolean estaCercaDe(Posicion pos, Integer radio) {
		Integer distanciaX = (pos.getX() - this.getX());
		distanciaX = distanciaX * distanciaX;
		Integer distanciaY = (pos.getY() - this.getY());
		distanciaY = distanciaY * distanciaY;
		Integer distanciaCuadrada = distanciaX + distanciaY;
		return Math.sqrt(distanciaCuadrada) < radio;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posicion other = (Posicion) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}

	public static Posicion crearPosicionSobreBorde(Integer bordeX,
			Integer bordeY) {
		Integer posicionX = 0;
		Integer posicionY = 0;
		Integer borde = r.nextInt(4);
		switch (borde) {
		case 0:
			posicionX = 0;
			posicionY = r.nextInt(bordeY);
			break;
		case 1:
			posicionX = bordeX;
			posicionY = r.nextInt(bordeY);
			break;
		case 2:
			posicionY = bordeY;
			posicionX = r.nextInt(bordeX);
			break;
		case 3:
			posicionY = 0;
			posicionX = r.nextInt(bordeX);
			break;
		}

		return new Posicion(posicionX, posicionY);
	}

	public static Posicion crearPrimerDestino(Integer bordeX,
			Integer bordeY) {

		Integer posicionX = r.nextInt(bordeX);
		Integer posicionY = r.nextInt(bordeY);
		Posicion posicionPrimerDestino = new Posicion(posicionX, posicionY);

		return posicionPrimerDestino;

	}
	
	public static Double crearPrimerDireccion(Posicion actualPos, Integer bordeX, Integer bordeY){
		Posicion siguiente = crearPrimerDestino(bordeX, bordeY);
		Integer desfasajeX = siguiente.getX() - actualPos.getX();
		Integer desfasajeY = siguiente.getY() - actualPos.getY();
		return Math.atan2(desfasajeY, desfasajeX); 
	}


	public Element serializarXML() {
		Element element = new Element("Posicion");
		Attribute x = new Attribute("x",this.x.toString());
		Attribute y = new Attribute("y",this.y.toString());
		element.setAttribute(x);
		element.setAttribute(y);
		return element;

	}



	public static Posicion cargarDesdeXML(Element elementoXML) {
		int x = Integer.parseInt(elementoXML.getAttributeValue("x"));
		int y = Integer.parseInt(elementoXML.getAttributeValue("y"));

		return new Posicion (x,y);
	}
}
