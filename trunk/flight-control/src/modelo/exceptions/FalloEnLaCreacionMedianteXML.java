package modelo.exceptions;


public class FalloEnLaCreacionMedianteXML extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FalloEnLaCreacionMedianteXML (String textoException){
		super (textoException);
	}
}
