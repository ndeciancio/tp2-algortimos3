package modelo.aviones;

import modelo.general.Posicion;
import modelo.juego.Juego;
import modelo.movimientos.Movimiento;
import modelo.movimientos.MovimientoHelicoptero;
import modelo.pistas.Pista;

public class AvionHelicoptero extends Avion {

	private AvionHelicoptero(Posicion posicion, Integer radio, Juego juego) {
		super(posicion, radio, juego);

	}

	public static AvionHelicoptero crearAvionHelicopteroSimple(
			Posicion posicion, Integer radio, Integer velocidad,
			Double direccion, Juego juego) {
		AvionHelicoptero avion = new AvionHelicoptero(posicion, radio, juego);
		Movimiento mov = new MovimientoHelicoptero(velocidad, direccion);
		avion.setMovimiento(mov);
		return avion;
	}

	public Boolean intentarAterrizar(Pista pista) {
		return pista.puedeAterrizar(this);
	}

}