/**
 * 
 */
package tetris.entities;

import javafx.scene.canvas.Canvas;
import tetris.resources.ImageResource;

public class Image extends Sprite {

	/**
	 * Costruttore nel caso sia passata solo un'immagine
	 * 
	 * @param texture
	 */
	public Image(ImageResource texture) {
		super(texture);
	}

	/**
	 * Costruttore nel caso sia passata un immagine e delle coordinate
	 * 
	 * @param x
	 * @param y
	 * @param texture
	 */
	public Image(int x, int y, ImageResource texture) {
		super(x, y, texture);
		setX(x);
		setY(y);
	}
	
	/**
	 * Getter per l'immagine
	 * 
	 * @return la risorsa immagine
	 */
	public Canvas getImage() {
		return ((ImageResource) texture).getImageCanvas();
	}
}
