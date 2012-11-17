package modelo.aviones;

import modelo.general.Posicion;
import modelo.movimientos.Movimiento;
import modelo.movimientos.MovimientoSimple;
import modelo.pistas.Pista;

public class AvionPesado extends Avion {

	private AvionPesado(Posicion posicion, Integer radio) {
		super(posicion, radio);
	}

	public static AvionPesado crearAvionPesadoSimple(Posicion posicion,Integer radio, Integer velocidad, Double direccion){
		AvionPesado avion = new AvionPesado(posicion, radio);
		Movimiento mov = new MovimientoSimple(velocidad, direccion);
		avion.setMovimiento(mov);
		return avion;
	}
	
	@Override
	public Boolean intentarAterrizar(Pista p) {
		return p.puedeAterrizar(this);
	}

}
