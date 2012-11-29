package modelo.aviones;

import modelo.general.Posicion;
import modelo.movimientos.Movimiento;
import modelo.movimientos.MovimientoSimple;
import modelo.pistas.Pista;

public class AvionHelicoptero extends Avion {

	private boolean senalDeAlto = false; 
	
	private AvionHelicoptero(Posicion posicion, Integer radio) {
		super(posicion, radio);

	} 
	public static AvionHelicoptero crearAvionHelicopteroSimple(Posicion posicion,Integer radio, Integer velocidad, Double direccion){
		AvionHelicoptero avion = new AvionHelicoptero(posicion, radio);
		Movimiento mov = new MovimientoSimple(velocidad, direccion);
		avion.setMovimiento(mov);
		return avion;
	}
	
	public void vivir() throws Exception{
		if (!senalDeAlto) {
			this.realizarElMovimiento();
		}
		else {
			paraAvion();
		}
		
	}
	
	
	private void paraAvion()
	{
		Movimiento movimientoParado = new MovimientoSimple (0,this.getMovimiento().getDireccion());
		this.setMovimiento(movimientoParado);
	}
	
	
	public void setOnSenalDeFreno()
	{
		senalDeAlto= true;
	}

	public void setOffSenalDeFreno()
	{
		senalDeAlto= false;
	}
	

	public Boolean intentarAterrizar(Pista pista) {

		return pista.puedeAterrizar(this);
	}

}