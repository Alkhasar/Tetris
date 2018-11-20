/**
 * Classe Menu
 * Questa classe rappresenta il menu, ovvero la scena di default
 * che deve essere eseguita anche in caso di errori.
 */
package tetris.tetrisScene;


// Java Imports
import javafx.scene.Group;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;

// Project Imports
import tetris.main.Tetris;
import tetris.resources.ImageResource;
import tetris.resources.SpriteSheetResource;
import tetris.entities.Button;
import tetris.entities.Image;
import tetris.entities.Sprite;

/**
 * @author Franco
 * @author Stefan
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
	private final Group staticNodes = new Group();
	
	
	/**
	 * Costruttore privato del menu, verr� eseguito uno volta sola.
	 */
	private Menu() {
		// Immagine di sfondo
		Image wallpaper = new Image((ImageResource) Tetris.getResourceLoader().getResource("MenuWallpaper")); // (ImageResource) Tetris.getResourceLoader().getResource("MenuWallpaper");
		
		// Pulsante gioca
		Button playButton = new Button(200, 100, (SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton3"), new Runnable() {
			
			@Override
			public void run() {
				Tetris.setScene("Game");
				
			}
		});
		
		// Pulsante opzioni
		Button optionsButton = new Button(200, 300, (SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton4"), new Runnable() {
			
			@Override
			public void run() {
				Tetris.setScene("Options");
				
			}
		});
		
		// Pulsante leaderboard
		Button leaderboardButton = new Button(200, 500, (SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton5"), new Runnable() {
			
			@Override
			public void run() {
				Tetris.setScene("LeaderBoard");
				
			}
		});
				
		
		//aggiungiamo a MenustaticNodes tutta la nostra grafica
		staticNodes.getChildren().add(wallpaper.getImage());
		staticNodes.getChildren().add(playButton.getButtonImage());
		staticNodes.getChildren().add(optionsButton.getButtonImage());
		staticNodes.getChildren().add(leaderboardButton.getButtonImage());
		
		// Aggiungiamo tutti gli sprite all'array per tenerne traccia
		sprites.add(wallpaper);
		sprites.add(playButton);
		sprites.add(optionsButton);
		sprites.add(leaderboardButton);
		
		//Aggiunta dei nodi statici e dinamici a ROOT
		ROOT.getChildren().add(staticNodes);
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
	}

	/**
	 * Codice che verr� eseguito continuamente
	 */
	@Override
	public void loop(long now) {
		// Aggiornamento dell' ultimo frame
		last = now;
		
		// Aggiornamento di ogni sprite
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
