package modelo.factories;

import java.util.Random;

import modelo.aviones.Avion;
import modelo.aviones.AvionPesado;
import modelo.general.Mapa;
import modelo.general.Posicion;
import modelo.juego.Juego;

public class FactoryAvionPesado implements FactoryAvion {

	private static Random r = new Random();
	
	@Override
	public Avion fabricarAvion(Mapa mapa, Juego juego) {
		Integer velocidad = 4;
		Integer radio = 20;
		Posicion posicionInicial = Posicion.crearPosicionSobreBorde(mapa
				.getBordeX(), mapa.getBordeY());
		Double direccion = Posicion.crearPrimerDireccion(
				posicionInicial, mapa.getBordeX(), mapa.getBordeY());
		Integer x = r.nextInt(2000);
		Avion avionPesado =null;
		if(x>1000){
			avionPesado = AvionPesado.crearAvionPesadoSimple(
					posicionInicial, radio, velocidad, direccion, juego);
		}else{
			avionPesado = AvionPesado.crearAvionPesadoInteligente(
					posicionInicial, radio, velocidad, direccion, juego);
		}
		return avionPesado;
	}

}
