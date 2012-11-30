package modelo.aviones;

import modelo.general.Posicion;
import modelo.juego.Juego;
import modelo.movimientos.Movimiento;
import modelo.movimientos.MovimientoInteligente;
import modelo.movimientos.MovimientoSimple;
import modelo.pistas.Pista;

public class AvionPesado extends Avion {

	private AvionPesado(Posicion posicion, Integer radio, Juego juego) {
		super(posicion, radio, juego);
	}

	public static AvionPesado crearAvionPesadoSimple(Posicion posicion,Integer radio, Integer velocidad, Double direccion, Juego juego){
		AvionPesado avion = new AvionPesado(posicion, radio, juego);
		Movimiento mov = new MovimientoSimple(velocidad, direccion);
		avion.setMovimiento(mov);
		return avion;
	}
	
	public static AvionPesado crearAvionPesadoInteligente(Posicion posicion,Integer radio, Integer velocidad, Double direccion, Juego juego){
		AvionPesado avion = new AvionPesado(posicion, radio, juego);
		Movimiento mov = new MovimientoInteligente(velocidad, direccion);
		avion.setMovimiento(mov);
		return avion;
	}
	
	@Override
	public Boolean intentarAterrizar(Pista p) {
		return p.puedeAterrizar(this);
	}

}
