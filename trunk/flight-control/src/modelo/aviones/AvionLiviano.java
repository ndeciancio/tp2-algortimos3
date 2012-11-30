package modelo.aviones;

import modelo.general.Posicion;
import modelo.juego.Juego;
import modelo.movimientos.Movimiento;
import modelo.movimientos.MovimientoInteligente;
import modelo.movimientos.MovimientoSimple;
import modelo.pistas.Pista;

public class AvionLiviano extends Avion {

	
	private AvionLiviano(Posicion posicion, Integer radio, Juego juego){
		super(posicion, radio, juego);
	}
	
	public static AvionLiviano crearAvionLivianoSimple(Posicion posicion,Integer radio, Integer velocidad, Double direccion, Juego juego){
		AvionLiviano avion = new AvionLiviano(posicion, radio, juego);
		Movimiento mov = new MovimientoSimple(velocidad, direccion);
		avion.setMovimiento(mov);
		return avion;
	}
	
	public static AvionLiviano crearAvionLivianoInteligente(Posicion posicion,Integer radio, Integer velocidad, Double direccion, Juego juego){
		AvionLiviano avion = new AvionLiviano(posicion, radio, juego);
		Movimiento mov = new MovimientoInteligente(velocidad, direccion);
		avion.setMovimiento(mov);
		return avion;
	}
	
	@Override
	public Boolean intentarAterrizar(Pista p){
		return p.puedeAterrizar(this);
	}
	
	
	
	

}
