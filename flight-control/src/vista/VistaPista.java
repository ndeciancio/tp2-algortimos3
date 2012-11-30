package vista;

import java.awt.Color;

import modelo.pistas.Pista;
import fiuba.algo3.titiritero.dibujables.Cuadrado;

public class VistaPista extends Cuadrado {

	public VistaPista(Pista p) {
		super(p.getRadio(), p.getRadio(), p);
		this.setColor(Color.BLUE);
	}
	
	

}
