package modelo.general;

import java.util.Random;


public class Posicion {

	private Integer x;
	private Integer y;
	
	public Posicion(Integer x, Integer y){
		this.x = x;
		this.y = y;
	}

	
	
	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}


	public void moverEnX(Integer x){
		this.x += x; 
	}
	
	public void moverEnY(Integer y){
		this.y += y;
	}

	public Boolean estaCercaDe(Posicion pos, Integer radio){
		Integer distanciaX = (pos.getX()-this.getX());
		distanciaX = distanciaX*distanciaX;
		Integer distanciaY = (pos.getY()-this.getY());
		distanciaY = distanciaY*distanciaY;
		Integer distanciaCuadrada = distanciaX + distanciaY;
		return Math.sqrt(distanciaCuadrada) <= radio;
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



	public static Posicion crearPosicionSobreBordeX(Integer bordeX, Integer bordeY) {
		Random randomX = new Random ();
		Random randomY = new Random ();
		Integer posicionX = randomX.nextInt(bordeX);
		Integer posicionY = randomY.nextInt(bordeY);
		
				
		if (posicionY >= (bordeY / 2))
		{
			posicionY = bordeY;
		}
		else{
			posicionY =0;
		}
		
		
		return new Posicion(posicionX, posicionY);
	}
	public static Posicion crearPosicionSobreBordeY(Integer bordeX, Integer bordeY) {
		Random randomX = new Random ();
		Random randomY = new Random ();
		Integer posicionX = randomX.nextInt(bordeX);
		Integer posicionY = randomY.nextInt(bordeY);
		
				
		if (posicionX >= (bordeX / 2))
		{
			posicionX = bordeX;
		}
		else{
			posicionX =0;
		}
		
		
		return new Posicion(posicionX, posicionY);
	}



	public boolean verificarIgualdad(Posicion posicionAComprobar) {

		return ( this.x == posicionAComprobar.x ) && (this.y == posicionAComprobar.y) ;
	}
}
