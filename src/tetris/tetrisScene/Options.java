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
public class Options extends TetrisScene {
	
	/**
	 * Unica istanza del singleton
	 */
	private static TetrisScene INSTANCE;
	
	
	
	private final Group OptionsstaticNodes = new Group();
	
	
	/**
	 * Costruttore privato del menu, verr� eseguito uno volta sola.
	 */
	private Options() {	
		
		ImageResource wallpaper3 = (ImageResource) Tetris.getResourceLoader().getResource("OptionsWallpaper");
		
		
		Button difficultyButton = new Button(200, 60, (SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton6"), new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		}); 
		
		Button volumeButton = new Button(200, 260, (SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton7"), new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		}); 
		
		Button rulesButton = new Button(200, 410, (SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton8"), new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		}); 
		
		Button menuButton = new Button(200, 560, (SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton9"), new Runnable() {
			
			@Override
			public void run() {
				Tetris.setScene("Menu");
				
			}
		}); 
		
		
		
		OptionsstaticNodes.getChildren().add(wallpaper3.getImageCanvas());
		OptionsstaticNodes.getChildren().add(difficultyButton.getButtonImage());
		OptionsstaticNodes.getChildren().add(volumeButton.getButtonImage());
		OptionsstaticNodes.getChildren().add(rulesButton.getButtonImage());
		OptionsstaticNodes.getChildren().add(menuButton.getButtonImage());
		
		
		
		ROOT.getChildren().add(OptionsstaticNodes);
		
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
		System.out.println("EUREKA OPZIONI FUNZIONA!!");

	}

	/**
	 * Codice che verr� eseguito continuamente
	 */
	@Override
	public void loop(long now) {
		System.out.println("EUREKA SIAMO NEL LOOP DI OPZIONI!!");
	}

	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

}
