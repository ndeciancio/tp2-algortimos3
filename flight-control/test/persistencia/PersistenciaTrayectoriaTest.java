package persistencia;

import static org.junit.Assert.assertTrue;

import java.io.FileWriter;
import java.io.IOException;

import modelo.general.Posicion;
import modelo.general.Trayectoria;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.junit.Test;

public class PersistenciaTrayectoriaTest {
	@Test
	public void testUnaTrayectoriaPodriaPoderGuardarSuEstadoEnUnArchivoXML() throws IOException
	{
		String pathArchivo = "XML\\PersitenciaTrayectoriaPrueba.xml";
		Posicion posicionAGuardar1 = new Posicion (1,2);
		Posicion posicionAGuardar2 = new Posicion (2,2);
		Trayectoria trayectoriaPrueba = new Trayectoria ();
		
		trayectoriaPrueba.agregarPosicion(posicionAGuardar1);
		trayectoriaPrueba.agregarPosicion(posicionAGuardar2);
        
		
        
		Element elementoApersistir = trayectoriaPrueba.serializarXML();
		Document document = new Document(elementoApersistir);
		
        XMLOutputter outputter = new XMLOutputter();
        outputter.setFormat(Format.getPrettyFormat());
        
        FileWriter writer = new FileWriter(pathArchivo);
        outputter.output(document,writer);
        writer.close();
	}
	
	@Test
	public void testUnaTrayectoriaSePodriaCrearApartirDeUnArchivoXML() throws IOException
	{
		Posicion posicionVerificar1 = new Posicion (1,2);
		Posicion posicionVerificar2 = new Posicion (2,2);
		Posicion posicionPrueba;
		Trayectoria trayectoriaXML;
		
		try {
			String pathArchivo = "XML\\PersitenciaTrayectoriaPrueba.xml";
	        SAXBuilder builder = new SAXBuilder();
	        Document document = builder.build(pathArchivo);
	        
	        trayectoriaXML = Trayectoria.cargarDesdeXML(document.getRootElement());
	        
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
		posicionPrueba = trayectoriaXML.getPosicionSiguiente();
		
		assertTrue (posicionVerificar1.getX() == posicionPrueba.getX());
		assertTrue (posicionVerificar1.getY() == posicionPrueba.getY());
		
		trayectoriaXML.eliminarPosicion();
		posicionPrueba = trayectoriaXML.getPosicionSiguiente();
		
		assertTrue ((posicionVerificar2.getX() == posicionPrueba.getX()));
		assertTrue ((posicionVerificar2.getY() == posicionPrueba.getY()));
	}
}
