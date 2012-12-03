package vista;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

import modelo.aviones.Avion;
import modelo.pistas.Pista;
import fiuba.algo3.titiritero.dibujables.SuperficiePanel;
import fiuba.algo3.titiritero.modelo.ObjetoDibujable;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;
import fiuba.algo3.titiritero.modelo.SuperficieDeDibujo;

public class VistaPista implements ObjetoDibujable{

	private BufferedImage imagen;
	private ObjetoPosicionable posicionable;
	private AffineTransform tx;
	private AffineTransformOp op;
	
	public VistaPista(Pista p) {
		this.posicionable = p;
		String className = p.getClass().getSimpleName();
		try {
			URL dir = this.getClass().getResource(
					"/images/" + className + ".png");
			this.imagen = ImageIO.read(dir);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ObjetoPosicionable getPosicionable() {
		return this.posicionable;
	}
	
	@Override
	public void dibujar(SuperficieDeDibujo superficieDeDibujo) {
		Pista p = (Pista) this.getPosicionable();
		tx = new AffineTransform();
		tx.rotate(p.getDireccion(), p.getRadio()+5,
				imagen.getHeight() / 2);
		op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);
		BufferedImage imagen = op.filter( this.imagen, null);
		Graphics grafico = ((SuperficiePanel) superficieDeDibujo).getBuffer();
		grafico.drawImage(imagen, this.posicionable.getX()-(p.getRadio()+5),
				this.posicionable.getY()-(p.getRadio()+5),null);
		// grafico.drawImage(im, at,null);
	}
	
	

}
