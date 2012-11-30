package modelo.general;

import java.util.Random;

public class Posicion {
	private static Random r = new Random(2048);

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
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
}
