package modelo.aviones;

import modelo.general.Posicion;
import modelo.juego.Escenario;
import modelo.movimientos.Movimiento;
import modelo.movimientos.MovimientoInteligente;
import modelo.movimientos.MovimientoSimple;
import modelo.pistas.Pista;

public class AvionPesado extends Avion {

	private AvionPesado(Posicion posicion, Integer radio, Escenario escenario) {
		super(posicion, radio, escenario);
	}

	public static AvionPesado crearAvionPesadoSimple(Posicion posicion,Integer radio, Integer velocidad, Double direccion, Escenario escenario){
		AvionPesado avion = new AvionPesado(posicion, radio, escenario);
		Movimiento mov = new MovimientoSimple(velocidad, direccion);
		avion.setMovimiento(mov);
		return avion;
	}
	
	public static AvionPesado crearAvionPesadoInteligente(Posicion posicion,Integer radio, Integer velocidad, Double direccion, Escenario escenario){
		AvionPesado avion = new AvionPesado(posicion, radio, escenario);
		Movimiento mov = new MovimientoInteligente(velocidad, direccion);
		avion.setMovimiento(mov);
		mov.setAvion(avion);
		return avion;
	}
	
	@Override
	public Boolean intentarAterrizar(Pista p) {
		return p.puedeAterrizar(this);
	}

	@Override
	public Boolean puedoAterrizarEnEstaPista(Pista p) {
		return p.esLaPista(this);
	}
	
}
