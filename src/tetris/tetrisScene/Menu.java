/**
 * Classe Menu
 * Questa classe rappresenta il menu, ovvero la scena di default
 * che deve essere eseguita anche in caso di errori.
 */
package tetris.tetrisScene;


// Java Imports
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.Date;

// Project Imports
import tetris.main.Tetris;
import tetris.resources.AudioResource;
import tetris.resources.ImageResource;
import tetris.resources.Resource;
import tetris.resources.SpriteSheetResource;
import tetris.entities.Sprite;

/**
 * @author Franco
 *
 */
public class Menu extends TetrisScene {
	
	/**
	 * Unica istanza del singleton
	 */
	private static TetrisScene INSTANCE;
	
	/**
	 * ArrayList per tenere traccia degli oggetti da renderizare nella scena.
	 */
	private ArrayList<Sprite> sprites = new ArrayList<>();  
	
	/**
	 * Long int per tenere traccia dell'ultimo frame
	 */
	private long last = 0;
	
	/**
	 * Costruttore privato del menu, verrà eseguito uno volta sola.
	 */
	private Menu() {	
		
		// Verifico il funzionamento degli sprite											// RIGA ESEMPIO
		Sprite test = new Sprite(0, 0, Tetris.getResourceLoader().getResource("myImage")) {	// RIGA ESEMPIO
																							// RIGA ESEMPIO
			@Override																		// RIGA ESEMPIO
			public void update() {															// RIGA ESEMPIO
				this.setY(this.getY() + 50);												// RIGA ESEMPIO
				if(this.getY() > 700) {														// RIGA ESEMPIO
					this.setY(0);															// RIGA ESEMPIO
				}																			// RIGA ESEMPIO	
			}																				// RIGA ESEMPIO
																							// RIGA ESEMPIO
		};																					// RIGA ESEMPIO
																							// RIGA ESEMPIO
		// Aggiungo lo sprite all'arrayList													// RIGA ESEMPIO
		sprites.add(test);																	// RIGA ESEMPIO
	}

	/**
	 * 
	 * @return unica istanza del menu.
	 */
	public static TetrisScene getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new Menu();
		}
		System.out.println(INSTANCE);
		return INSTANCE;
	}
	
	/** 
	 * Codice iniziale per la preparazione del menu.
	 */
	@Override
	public void init() {		
		// Aggiunge ogni sprite al ROOT node																			// RIGA ESEMPIO														
		for (Sprite sprite : sprites) {																					// RIGA ESEMPIO
			ROOT.getChildren().add((Canvas) ((ImageResource) sprite.getTexture()).getImageCanvas());					// RIGA ESEMPIO
		}																												// RIGA ESEMPIO
																														// RIGA ESEMPIO
		System.out.println("EUREKA MENU FUNZIONA!!");																	// RIGA ESEMPIO
		// Verifico che si riesca a caricare uno spriteSheet															// RIGA ESEMPIO
		SpriteSheetResource tempe = (SpriteSheetResource) Tetris.getResourceLoader().getResource("mySpriteSheet");		// RIGA ESEMPIO
		
		// Per ogni immagine dello spriteSheet, aggiungila al ROOT node
		for (int i = 0; i < tempe.getNumberOfImg(); i++){																// RIGA ESEMPIO
			Canvas temp2 = tempe.getFrame(i);																			// RIGA ESEMPIO
			temp2.setLayoutY(i*100);																					// RIGA ESEMPIO
			temp2.setLayoutX(i*32);																						// RIGA ESEMPIO
			ROOT.getChildren().add(temp2);																				// RIGA ESEMPIO
		}
		
		

		// Verifico il funzionamento dell'audio																			// RIGA ESEMPIO
		AudioResource music = (AudioResource) Tetris.getResourceLoader().getResource("myAudio");						// RIGA ESEMPIO
		music.loop();																									// RIGA ESEMPIO
		music.play();																									// RIGA ESEMPIO
		
		// Cerca di mostrare tutto ciò che è visibile
		Tetris.getPrimaryStage().show();
	}

	/**
	 * Codice che verrà eseguito continuamente
	 */
	@Override
	public void loop(long now) {
		if((now - last)/1000000000 > 0) {			// RIGA ESEMPIO
			last = now;								// RIGA ESEMPIO
			for (Sprite sprite : sprites) 			// RIGA ESEMPIO
				sprite.update();					// RIGA ESEMPIO
		}											// RIGA ESEMPIO
	}												// RIGA ESEMPIO

}
