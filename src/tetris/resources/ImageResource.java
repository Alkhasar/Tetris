/**
 * Classe Risorsa immagine
 * 
 * Classe che si occupa di tenere traccia di una immagine.
 */
package tetris.resources;

// Java Imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

//Same Package imports
import tetris.resources.Resource;


/**
 * @author Franco
 *
 */
public class ImageResource extends Resource {
	
	/**
	 * Larghezza ed altezza dell'immagine (la larghezza di un'immagine non varierà nel tempo
	 * pertato la dichiariamo final.
	 */
	private final int width;
	private final int height;
	
	/**
	 * Conservo in memoria l'immagine, il canvas sulla quale viene disegnata e il graphicContext.
	 * Assumo che l'immagine e il canvas non vengono modificati, mentre il GC sì.
	 */
	protected final Image img;
	protected final Canvas imgCanvas;
	protected  GraphicsContext imgGc;
	
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
		
		// Creazione del canvas sul quale viene disegnata l'img
		imgCanvas = new Canvas(this.width, this.height);
		imgGc = imgCanvas.getGraphicsContext2D();
		
		// Disegno dell'immagine sul canvas
		imgGc.drawImage(this.img, 0, 0);
	}
	
	
	/**
	 * Costruttore nel caso l'immagine provenga da uno spriteSheet e
	 * si voglia visualizzare solo un'immgaine
	 * 
	 * @param larghezza dell'immagine
	 * @param altezza dell'immagine
	 * @param spriteSheet corrispondente
	 * @param indice dell'immagine
	 */
	public ImageResource(int width, int height, SpriteSheetResource spriteSheet, int index) {
		// Costruttore di resourc
		super(spriteSheet.path);
		
		// Assegnazione dei parametri iniziali
		this.width = width;
		this.height = height;
		
		// Impostazione dell'img to null poiche proviene da uno spritesheet
		img = null;
		
		// Impostazioned del canvas corrente
		imgCanvas = spriteSheet.getFrame(index);
		// Impostazione del graphic context
		imgGc = imgCanvas.getGraphicsContext2D();
	}
	
	/**
	 * Costruttore nel caso l'immagine sia gia salvata su un canvas
	 * 
	 * @param largehzza del canvas
	 * @param altezza del canvas
	 * @param il canvas
	 */
	public ImageResource(int width, int height, Canvas canvas) {
		// Costruttore di resource
		super(null);
		
		// Assegnazione dei parametri iniziali
		this.width = width;
		this.height = height;
		
		// Impostazione dell'img to null poiche proviene da uno spritesheet
		img = null;
				
		// Impostazioned del canvas corrente
		imgCanvas = canvas;
		
		// Impostazione del graphic context
		imgGc = imgCanvas.getGraphicsContext2D();
		
	}

	/**
	 * Getter per la larghezza dell'immagine caricata.
	 * 
	 * @return la larghezza
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Getter per l'altezza dell immagine caricata.
	 * 
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
	public Canvas getImageCanvas() {
		return this.imgCanvas;
	}
	
	
}
