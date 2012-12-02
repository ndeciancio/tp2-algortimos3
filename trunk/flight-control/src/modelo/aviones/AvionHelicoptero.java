package modelo.aviones;

import modelo.general.Posicion;
import modelo.juego.Escenario;
import modelo.movimientos.Movimiento;
import modelo.movimientos.MovimientoHelicoptero;
import modelo.pistas.Pista;

public class AvionHelicoptero extends Avion {

	private AvionHelicoptero(Posicion posicion, Integer radio, Escenario escenario) {
		super(posicion, radio, escenario);

	}

	public static AvionHelicoptero crearAvionHelicopteroSimple(
			Posicion posicion, Integer radio, Integer velocidad,
			Double direccion, Escenario escenario) {
		AvionHelicoptero avion = new AvionHelicoptero(posicion, radio, escenario);
		Movimiento mov = new MovimientoHelicoptero(velocidad, direccion);
		avion.setMovimiento(mov);
		return avion;
	}

	public Boolean intentarAterrizar(Pista pista) {
		return pista.puedeAterrizar(this);
	}

	@Override
	public Boolean puedoAterrizarEnEstaPista(Pista p) {
		return p.esLaPista(this);
	}

}