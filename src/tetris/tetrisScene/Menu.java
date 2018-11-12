/**
 * Classe Menu
 * Questa classe rappresenta il menu, ovvero la scena di default
 * che deve essere eseguita anche in caso di errori.
 */
package tetris.tetrisScene;


import javafx.event.EventHandler;
// Java Imports
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
		sprites.add(test);	
		
		// Aggiunge ogni sprite al ROOT node																			// RIGA ESEMPIO														
		for (Sprite sprite : sprites) {																					// RIGA ESEMPIO
			ROOT.getChildren().add((Canvas) ((ImageResource) sprite.getTexture()).getImageCanvas());					// RIGA ESEMPIO
		}																												// RIGA ESEMPIO
																														// RIGA ESEMPIO																// RIGA ESEMPIO
		// Verifico che si riesca a caricare uno spriteSheet															// RIGA ESEMPIO
		SpriteSheetResource tempe = (SpriteSheetResource) Tetris.getResourceLoader().getResource("mySpriteSheet");		// RIGA ESEMPIO
		
		// Per ogni immagine dello spriteSheet, aggiungila al ROOT node
		for (int i = 0; i < tempe.getNumberOfImg(); i++){																// RIGA ESEMPIO
			Canvas temp2 = tempe.getFrame(i);																			// RIGA ESEMPIO
			temp2.setLayoutY(i*100);																					// RIGA ESEMPIO
			temp2.setLayoutX(i*32);																						// RIGA ESEMPIO
			ROOT.getChildren().add(temp2);																				// RIGA ESEMPIO
		}
		
		ROOT.getChildren().get(3).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Tetris.setScene("Game");
				
			}
		});
		
		
		ROOT.getChildren().get(2).addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				double CurrentX = ROOT.getChildren().get(3).getLayoutX();
				if (CurrentX> 700) {
					ROOT.getChildren().get(3).setLayoutX(0);						
				}
				else {
					ROOT.getChildren().get(3).setLayoutX(CurrentX+5);
				}
				
				
				
			}
		});
		
		
	}

	/**
	 * 
	 * @return unica istanza del menu.
	 */
	public static TetrisScene getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new Menu();
		}
		return INSTANCE;
	}
	
	/** 
	 * Codice iniziale per la preparazione del menu.
	 */
	@Override
	public void init() {		
		// Cerca di mostrare tutto ciò che è visibile
		Tetris.getPrimaryStage().show();
		
		// Verifico il funzionamento dell'audio																			// RIGA ESEMPIO
//		AudioResource music = (AudioResource) Tetris.getResourceLoader().getResource("myAudio");						// RIGA ESEMPIO
//		music.loop();																									// RIGA ESEMPIO
//		music.play();					
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

	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

}
