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
	 * Funzione che verrà eseguita al click
	 */
	private Runnable fx;
		
	/**
	 * Questa funzione assegnerà a ogni pulsante la sua posizione, la sua immagine e le darà una sembianza di movimento
	 * 
	 * @param x posizione iniziale del bottone
	 * @param y posizione inizilae del bottone
	 * @param texture immagini del futuro bottone
	 * @param fx azione chverrà eseguita dal bottone
	 */
	public Button(int x, int y, SpriteSheetResource texture, Runnable fx) {
		super(x, y, texture);	
		this.fx = fx;
		ButtonImage.getChildren().add(texture.getFrame(0));
		setX(x);
		setY(y);
		
		// chiama il runnable che farà eseguire un comando alla funzione
		ButtonImage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				fx.run();
				System.out.println("Sono passato in mouse clicked");
			}
		});
		

		// quando il mouse entra nel perimetro del pulsante questo cambia immagine	
		ButtonImage.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				ButtonImage.getChildren().remove(0);
				ButtonImage.getChildren().add(texture.getFrame(1));
				System.out.println("Sono passato in mouse entered");
			}
		});
		
		// quando il mouse esce dal perimetro del pulsante ritorna l'immagine precedente 
		ButtonImage.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				ButtonImage.getChildren().remove(0);
				ButtonImage.getChildren().add(texture.getFrame(0));
				System.out.println("Sono passato in mouse exited");
			}
		});	
	}
	
	/**
	 * Getter per il gruppo nel quale è conservata l'immagine corrente
	 * del pulsante
	 * 
	 * @return il gruppo contenente l'immagine
	 */
	public Group getButtonImage() {
		return ButtonImage;
	}
	
	/**
	 * Setter per la posizione x iniziale del pulsante
	 */
	@Override
	public void setX(int x) {
		ButtonImage.setLayoutX(x);
		this.x = x;
	}
	
	/**
	 * Setter per la posizione y iniziale del pulsante
	 */
	@Override
	public void setY(int y) {
		ButtonImage.setLayoutY(y);
		this.y = y;
	}
		
	/**
	 * Metodo per eseguire l'update della grafica / meccaniche 
	 */
	@Override
	public void update() {}

}
