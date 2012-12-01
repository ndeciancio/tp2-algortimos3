package vista;

import java.awt.Color;

import modelo.aviones.Avion;
import modelo.aviones.AvionLiviano;
import modelo.aviones.AvionPesado;
import fiuba.algo3.titiritero.dibujables.Circulo;

public class VistaAvion extends Circulo{

	public Integer i;
	
	public VistaAvion(Avion arg1) {
		super(arg1.getRadio()*2, arg1);
		if(arg1 instanceof AvionLiviano){
			this.setColor(Color.RED);
		}else if(arg1 instanceof AvionPesado){
			this.setColor(Color.BLUE);
		}else{
			this.setColor(Color.GREEN);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VistaAvion other = (VistaAvion) obj;
		Avion a = (Avion) this.getPosicionable();
		Avion otherA = (Avion) other.getPosicionable();
		return a.equals(otherA);
	}
	
	
	

}
