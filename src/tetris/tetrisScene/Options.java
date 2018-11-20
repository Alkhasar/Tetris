/**
 * Classe Menu
 * 
 * Questa classe rappresenta il menu, ovvero la scena di default
 * che deve essere eseguita anche in caso di errori.
 */
package tetris.tetrisScene;

import java.util.ArrayList;

// Java Imports
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import tetris.entities.Button;
import tetris.entities.Image;
import tetris.entities.Sprite;
// Project Imports
import tetris.main.Tetris;
import tetris.resources.ImageResource;
import tetris.resources.SpriteSheetResource;

/**
 * @author Franco
 *
 */
public class Options extends TetrisScene {
	
	/**
	 * Unica istanza del singleton
	 */
	private static TetrisScene INSTANCE;
	
	/**
	 * Gruppo nel quale saranno contenuti gli elementi statici della grafica delle opzioni
	 */
	private final Group staticNodes = new Group();
	
	/**
	 * ArrayList per tenere traccia degli oggetti da renderizare nella scena.
	 */
	private ArrayList<Sprite> sprites = new ArrayList<>();  
	
	/**
	 * Costruttore privato del menu, verrï¿½ eseguito uno volta sola.
	 */
	private Options() {	
		
		// Immagine di sfondo
		Image wallpaper = new Image((ImageResource) Tetris.getResourceLoader().getResource("OptionsWallpaper"));
		
		// Pulsante difficoltà
		Button difficultyButton = new Button(200, 60, (SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton6"), new Runnable() {
			
			@Override
			public void run() {
				// DIFFICOLTA
				
			}
		}); 
		
		// Pulsante volume
		Button volumeButton = new Button(200, 260, (SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton7"), new Runnable() {
			
			@Override
			public void run() {
				// VOLUME
				
			}
		}); 
		
		// Pulsante regole
		Button rulesButton = new Button(200, 410, (SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton8"), new Runnable() {
			
			@Override
			public void run() {
				// VISUALIZZAZIONE DELLE REGOLE
				
			}
		}); 
		
		// Pulsante menu
		Button menuButton = new Button(200, 560, (SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton9"), new Runnable() {
			
			@Override
			public void run() {
				Tetris.setScene("Menu");
				
			}
		}); 
		
		
		// Aggiunte degli elementi a staticnodes
		staticNodes.getChildren().add(wallpaper.getImage());
		staticNodes.getChildren().add(difficultyButton.getButtonImage());
		staticNodes.getChildren().add(volumeButton.getButtonImage());
		staticNodes.getChildren().add(rulesButton.getButtonImage());
		staticNodes.getChildren().add(menuButton.getButtonImage());
		
		// Aggiunta di ogni sprite all array
		sprites.add(wallpaper);
		sprites.add(difficultyButton);
		sprites.add(volumeButton);
		sprites.add(rulesButton);
		sprites.add(menuButton);
		
		// Aggiunta di static nodes a root
		ROOT.getChildren().add(staticNodes);	
	}

	/**
	 * 
	 * @return unica istanza del menu.
	 */
	public static TetrisScene getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new Options();
		}
		return INSTANCE;
	}
	
	/** 
	 * Codice iniziale per la preparazione del menu.
	 */
	@Override
	public void init() {

	}

	/**
	 * Codice che verrï¿½ eseguito continuamente
	 */
	@Override
	public void loop(long now) {
		for (Sprite sprite : sprites) {
			sprite.update(now);
		}
	}

	/**
	 * Gestione dei tasti
	 */
	@Override
	public void handle(KeyEvent event) {}

}
