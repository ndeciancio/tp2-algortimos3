package vista;

import java.awt.Color;

import modelo.pistas.Pista;
import modelo.pistas.PistaHelipuerto;
import modelo.pistas.PistaLarga;
import fiuba.algo3.titiritero.dibujables.Cuadrado;

public class VistaPista extends Cuadrado {

	public VistaPista(Pista p) {
		super(p.getRadio()*2, p.getRadio()*2, p);
		if(p instanceof PistaLarga){
			this.setColor(Color.BLUE);
		}else if(p instanceof PistaHelipuerto){
			this.setColor(Color.GREEN);
		}else{
			this.setColor(Color.RED);
		}
	}
	
	

}
