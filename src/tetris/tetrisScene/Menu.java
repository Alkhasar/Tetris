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
import tetris.entities.Button;
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
	 * Gruppo nel quale saranno contenuti gli elementi statici della grafica del menu
	 */
	private final Group MenustaticNodes = new Group();
	
	
	/**
	 * Costruttore privato del menu, verr� eseguito uno volta sola.
	 */
	private Menu() {	
		ImageResource wallpaper2 = (ImageResource) Tetris.getResourceLoader().getResource("MenuWallpaper");
		
		Button playButton = new Button(200, 100, (SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton3"), new Runnable() {
			
			@Override
			public void run() {
				Tetris.setScene("Game");
				
			}
		});
		
		Button optionsButton = new Button(200, 300, (SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton4"), new Runnable() {
			
			@Override
			public void run() {
				Tetris.setScene("Options");
				
			}
		});
		
		Button leaderboardButton = new Button(200, 500, (SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton5"), new Runnable() {
			
			@Override
			public void run() {
				Tetris.setScene("LeaderBoard");
				
			}
		});
				
		
		//aggiungiamo a MenustaticNodes tutta la nostra grafica
		MenustaticNodes.getChildren().add(wallpaper2.getImageCanvas());
		MenustaticNodes.getChildren().add(playButton.getButtonImage());
		MenustaticNodes.getChildren().add(optionsButton.getButtonImage());
		MenustaticNodes.getChildren().add(leaderboardButton.getButtonImage());
		
		
		//Aggiunta dei nodi statici e dinamici a ROOT
		ROOT.getChildren().add(MenustaticNodes);
		
		
		
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
		// Cerca di mostrare tutto ci� che � visibile
		Tetris.getPrimaryStage().show();
		
		// Verifico il funzionamento dell'audio																			// RIGA ESEMPIO
//		AudioResource music = (AudioResource) Tetris.getResourceLoader().getResource("myAudio");						// RIGA ESEMPIO
//		music.loop();																									// RIGA ESEMPIO
//		music.play();					
	}

	/**
	 * Codice che verr� eseguito continuamente
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
