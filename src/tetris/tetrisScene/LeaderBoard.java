/**
 * Classe Menu
 * 
 * Questa classe rappresenta il menu, ovvero la scena di default
 * che deve essere eseguita anche in caso di errori.
 */
package tetris.tetrisScene;

// Java Imports
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import tetris.entities.Button;
// Project Imports
import tetris.main.Tetris;
import tetris.resources.ImageResource;
import tetris.resources.SpriteSheetResource;

/**
 * @author Franco
 *
 */
public class LeaderBoard extends TetrisScene {
	
	/**
	 * Unica istanza del singleton
	 */
	private static TetrisScene INSTANCE;
	
	
	/**
	 * Gruppo nel quale saranno contenuti gli elementi statici della grafica delle opzioni
	 */
	private final Group LeaderboardstaticNodes = new Group();
	
	
	/**
	 * Costruttore privato del menu, verr� eseguito uno volta sola.
	 */
	private LeaderBoard() {
		
		ImageResource wallpaper4 = (ImageResource) Tetris.getResourceLoader().getResource("LeaderboardWallpaper");
		
		
		
		Button menuButton = new Button(200, 560, (SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton9"), new Runnable() {
			
			@Override
			public void run() {
				Tetris.setScene("Menu");
				
			}
		});
		
		
		
		LeaderboardstaticNodes.getChildren().add(wallpaper4.getImageCanvas());
		LeaderboardstaticNodes.getChildren().add(menuButton.getButtonImage());
		
		ROOT.getChildren().add(LeaderboardstaticNodes);
	}

	/**
	 * 
	 * @return unica istanza del menu.
	 */
	public static TetrisScene getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new LeaderBoard();
		}
		return INSTANCE;
	}
	
	/** 
	 * Codice iniziale per la preparazione del menu.
	 */
	@Override
	public void init() {
		System.out.println("EUREKA LEADERBOARD FUNZIONA!!");

	}

	/**
	 * Codice che verr� eseguito continuamente
	 */
	@Override
	public void loop(long now) {
		System.out.println("EUREKA SIAMO NEL LOOP DI LEADERBOARD!!");
	}

	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

}
