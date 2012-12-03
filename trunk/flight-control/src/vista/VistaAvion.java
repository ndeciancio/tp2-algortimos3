package vista;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

import modelo.aviones.Avion;
import fiuba.algo3.titiritero.dibujables.SuperficiePanel;
import fiuba.algo3.titiritero.modelo.ObjetoDibujable;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;
import fiuba.algo3.titiritero.modelo.SuperficieDeDibujo;

public class VistaAvion implements ObjetoDibujable {

	private BufferedImage imagen;
	private ObjetoPosicionable posicionable;
	private AffineTransform tx;
	private AffineTransformOp op;

	public VistaAvion(Avion avion) {
		this.posicionable = avion;
		String className = avion.getClass().getSimpleName();
		try {
			URL dir = this.getClass().getResource(
					"/images/" + className + ".png");
			this.imagen = ImageIO.read(dir);
		} catch (Exception e) {
			e.printStackTrace();
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

	public ObjetoPosicionable getPosicionable() {
		return this.posicionable;
	}

	@Override
	public void dibujar(SuperficieDeDibujo superficieDeDibujo) {
		Avion a = (Avion) this.getPosicionable();
		tx = new AffineTransform();
		tx.rotate(a.getDireccion(), imagen.getWidth() / 2,
				imagen.getHeight() / 2);
		op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);
		BufferedImage imagen=null;
		if(a.estaSeleccionado()){
			String className = a.getClass().getSimpleName();
			try {
				URL dir = this.getClass().getResource(
						"/images/" + className + "Selected.png");
				BufferedImage imgTemp = ImageIO.read(dir);
				imagen = op.filter(imgTemp, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			imagen = op.filter( this.imagen, null);
		}
		Graphics grafico = ((SuperficiePanel) superficieDeDibujo).getBuffer();
		grafico.drawImage(imagen, this.posicionable.getX()-a.getRadio(),
				this.posicionable.getY()-a.getRadio(),null);
	}

}
