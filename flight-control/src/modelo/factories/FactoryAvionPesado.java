package modelo.factories;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.aviones.Avion;
import modelo.aviones.AvionPesado;
import modelo.general.Mapa;
import modelo.general.Posicion;
import modelo.general.Trayectoria;
import modelo.juego.Juego;

public class FactoryAvionPesado implements FactoryAvion {

	@Override
	public Avion fabricarAvion(Mapa mapa, Juego juego) {
		Double direccion = 0d;
		Integer velocidad = 2;
		Integer radio = 20;
		Posicion posicionInicial = Posicion.crearPosicionSobreBorde(mapa
				.getBordeX(), mapa.getBordeY());
		Posicion primerMovimiento = Posicion.crearPrimerDestino(mapa
				.getBordeX(), mapa.getBordeY());
		List<Posicion> listaPrimerMovimiento = new ArrayList<Posicion>();
		listaPrimerMovimiento.add(primerMovimiento);
		Trayectoria trayectoriaAvionNuevo = new Trayectoria(
				listaPrimerMovimiento);
		Random r = new Random(1024);
		Integer x = r.nextInt(2);
		Avion avionPesado =null;
		if(x>0){
			avionPesado = AvionPesado.crearAvionPesadoSimple(
					posicionInicial, radio, velocidad, direccion, juego);
		}else{
			avionPesado = AvionPesado.crearAvionPesadoInteligente(
					posicionInicial, radio, velocidad, direccion, juego);
		}
		avionPesado.setTrayectoria(trayectoriaAvionNuevo);
		return avionPesado;
	}

}
