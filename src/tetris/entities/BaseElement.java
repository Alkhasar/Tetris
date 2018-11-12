/**
 * 
 */
package tetris.entities;

// Java Imports
import javafx.scene.canvas.Canvas;

// Project imports
import tetris.resources.Resource;
import tetris.resources.SpriteSheetResource;
import tetris.resources.ImageResource;

/**
 * @author Franco Mostardi
 *
 */
public class BaseElement extends Sprite {
	
	/**
	 * Tipo di tetramino corrente
	 */
	private final int type;
	
	/**
	 * Coordinate rlative
	 */
	private int relX;
	private int relY;
	
	/**
	 * Costruttore dell'elemento base
	 * 
	 * @param coordinata x
	 * @param coordinata y
	 * @param la texture dell'elemento base
	 */
	public BaseElement(int x, int y, SpriteSheetResource resource, int type, int relX, int relY) {
		super(x, y, new ImageResource(32, 32, resource, type));
		super.setX(x);
		super.setY(y);
		
		// Settagio posizioni relative
		this.relX = relX;
		this.relY = relY;
		
		this.type = type;
	}

	/**
	 * Metodo Update, non implementato per l'elemento base.
	 */
	@Override
	public void update() {}

	/**
	 * @return the relX
	 */
	public int getRelX() {
		return relX;
	}

	/**
	 * @param relX the relX to set
	 */
	public void setRelX(int relX) {
		setX(this.getX() + relX*32 - getRelX()*32);
		this.relX = relX;
	}

	/**
	 * @return the relY
	 */
	public int getRelY() {
		return relY;
	}

	/**
	 * @param relY the relY to set
	 */
	public void setRelY(int relY) {
		setY(this.getY() + relY*32 - getRelY()*32);
		this.relY = relY;
	}
	
	/**
	 * Metodo per ottenere il canvas senza troppi giri
	 * 
	 * @return canvas su cui è disegnato il tetramino
	 */
	public Canvas getImageCanvas() {
		return ((ImageResource) getTexture()).getImageCanvas();
	}
	
}
