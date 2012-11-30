package vista;

import java.awt.Color;

import modelo.aviones.Avion;
import fiuba.algo3.titiritero.dibujables.Circulo;

public class VistaAvion extends Circulo{

	public VistaAvion(Avion arg1) {
		super(arg1.getRadio(), arg1);
		this.setColor(Color.RED);
	}


}
