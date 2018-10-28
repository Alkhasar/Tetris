/**
 * Classe Risorsa immagine
 * 
 * Classe che si occupa di tenere traccia di una immagine.
 */
package tetris.resourceLoader;

// Java Imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author Franco
 *
 */
public class ImageResource extends Resource {
	
	private final int width;
	private final int height;
	protected final Image img;
	protected final Canvas imgCanvas;
	protected GraphicsContext imgGc;
	
	/**
	 * Questa classe è responsabile del mantenimento in memoria di una singola immagine,
	 * che verrà immediatamente inserita in un canvas per utilizzi futuri.
	 * 
	 * @param {int} width
	 * @param {int} height
	 * @param {String} path
	 * @throws FileNotFoundException
	 */
	public ImageResource(int width, int height, String path) throws FileNotFoundException {
		// Costruttore di Resource
		super(path);
		
		// Assegnazione dei parametri iniziali
		this.width = width;
		this.height = height;
		
		// Inizializzazione dell'input stream
		img = new Image(new FileInputStream(this.file));
		imgCanvas = new Canvas(this.width, this.height);
		imgGc = imgCanvas.getGraphicsContext2D();
		imgGc.drawImage(this.img, 0, 0);
	}

	/**
	 * @return la larghezza
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return l'altezza
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Getter per l'utilizzo dell'immagine al di fuori di resourceLoader
	 * 
	 * @return il canvas su cui è disegnata l'img
	 */
	public Canvas getResource() {
		return this.imgCanvas;
	}
	
	
}
