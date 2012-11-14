
public class AvionLiviano extends Avion {

	
	private AvionLiviano(Posicion posicion, Integer radio){
		super(posicion, radio);
	}
	
	public static AvionLiviano crearAvionLivianoSimple(Posicion posicion,Integer radio, Integer velocidad, Double direccion){
		AvionLiviano avion = new AvionLiviano(posicion, radio);
		Movimiento mov = new MovimientoSimple(velocidad, direccion);
		avion.setMovimiento(mov);
		return avion;
	}
	
	@Override
	public Boolean intentarAterrizar(Pista p){
		return p.puedeAterrizar(this);
	}
	
	
	
	

}
