/**
 *  Classe Risorsa
 *  
 *  Questa classe è la risorsa base, tiene solo traccia del file da
 *  caricare in memoria.
 */
package tetris.resources;

// Java Imports
import java.io.File;

/**
 * @author Franco
 *
 */
public abstract class Resource {
	
	/**
	 * File risorsa
	 */
	protected final File file;
	
	/**
	 * Costruttore della Risorsa
	 * 
	 * @param percorso della risorsa
	 */
	protected Resource(String path) {
		file = new File(path);
	}
}
