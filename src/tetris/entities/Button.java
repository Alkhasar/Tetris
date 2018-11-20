/**
 * Classe Button
 * 
 * Questa classe si occupa di generare pulsanti, tenendo conto
 * della funzione da eseguire al click.
 */
package tetris.entities;

// Java Imports
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

// Project Imports
import tetris.resources.SpriteSheetResource;

/**
 * @author Stefan 
 *
 */
public class Button extends Sprite {

	/**
	 * Gruppo contenente l'immagine attuale del pulsante
	 */
	private Group ButtonImage = new Group();  
	
	/**
	 * Funzione che verr� eseguita al click
	 */
	private Runnable fx;
		
	/**
	 * Questa funzione assegner� a ogni pulsante la sua posizione, la sua immagine e le dar� una sembianza di movimento
	 * 
	 * @param x posizione iniziale del bottone
	 * @param y posizione inizilae del bottone
	 * @param texture immagini del futuro bottone
	 * @param fx azione chverr� eseguita dal bottone
	 */
	public Button(int x, int y, SpriteSheetResource texture, Runnable fx) {
		super(x, y, texture);	
		this.fx = fx;
		ButtonImage.getChildren().add(texture.getFrame(0));
		setX(x);
		setY(y);
		
		// chiama il runnable che far� eseguire un comando alla funzione
		ButtonImage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				fx.run();
			}
		});
		

		// quando il mouse entra nel perimetro del pulsante questo cambia immagine	
		ButtonImage.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				ButtonImage.getChildren().remove(0);
				ButtonImage.getChildren().add(texture.getFrame(1));
			}
		});
		
		// quando il mouse esce dal perimetro del pulsante ritorna l'immagine precedente 
		ButtonImage.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				ButtonImage.getChildren().remove(0);
				ButtonImage.getChildren().add(texture.getFrame(0));
			}
		});	
	}
	
	/**
	 * Getter per il gruppo nel quale � conservata l'immagine corrente
	 * del pulsante
	 * 
	 * @return il gruppo contenente l'immagine
	 */
	public Group getButtonImage() {
		return ButtonImage;
	}
	
	/**
	 * Setter per la posizione x iniziale del pulsante
	 * 
	 * @param coordinata x
	 */
	@Override
	public void setX(int x) {
		ButtonImage.setLayoutX(x);
		this.x = x;
	}
	
	/**
	 * Setter per la posizione y iniziale del pulsante
	 * 
	 * @param coordinata y
	 */
	@Override
	public void setY(int y) {
		ButtonImage.setLayoutY(y);
		this.y = y;
	}

}
