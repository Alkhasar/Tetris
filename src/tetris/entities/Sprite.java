/**
 * Classe Sprite
 * 
 * Classe astratta sprite, dalla quale verrano create tutte le entità che verranno
 * renderizzatie nel gioco.
 * 
 */
package tetris.entities;

// Project Imports
import tetris.resources.ImageResource;
import tetris.resources.Resource;

/**
 * @author Franco Mostardi
 *
 */
public abstract class Sprite {
	
	/**
	 * Risorsa immagine dello sprite, una volta assegnata
	 * non può essere modificata.
	 */
	protected final Resource texture;
	
	/**
	 * Coordinata x.
	 */
	protected int x;
	
	/**
	 * Coordinata y.
	 */
	protected int y;
	
	/**
	 * Costruttore che prende come argomenti solo la risorsa immagine che 
	 * verrà usata per lo sprite.
	 * 
	 * @param immagine da caricare
	 */
	public Sprite(Resource texture) {
		// Inizializzazione parametri iniziali
		this.texture = texture;
	}
	
	/**
	 * Costruttore che prende, oltre alla risorsa immagine, anche la posizione
	 * x e la posizione y dell'angolo in alto a sx. Notare che le coordinate dello
	 * schermo hanno origine nell'angolo in alto a sx dello schermo.
	 * 
	 * @param coordinata x
	 * @param coordinata y
	 * @param immagine da caricare.
	 */
	public Sprite(int x, int y, Resource texture) {
		// Inizializzazione parametri iniziali
		this.x = x;
		this.y = y;
		this.texture = texture;
	}

	/**
	 * Il metodo update va eseguito ad ogni ciclo e deve essere
	 * sviluppato per ogni sottoclasse si Sprite.
	 */
	abstract public void update();
	
	/**
	 * Restituisce la coordinata x dello sprite.
	 * 
	 * @return coordinata x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Setter per la coordinata x.
	 * 
	 * @param la coordinata x da settare
	 */
	public void setX(int x) {
		ImageResource temp = (ImageResource) this.texture;
		temp.getImageCanvas().setLayoutX(x);
		this.x = x;
	}

	/**
	 * Restituisce la coordinata y dello sprite.
	 * 
	 * @return coordinata y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Setter per la coordinata y.
	 * 
	 * @param la coordinata y da settare.
	 */
	public void setY(int y) {
		ImageResource temp = (ImageResource) this.texture;
		temp.getImageCanvas().setLayoutY(y);
		this.y = y;
	}

	/**
	 * Getter per accedere alla texture dello sprite.
	 * 
	 * @return la texture dello sprite.
	 */
	public Resource getTexture() {
		return texture;
	}
	
}
