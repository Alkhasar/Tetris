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
// Project Imports
import tetris.main.Tetris;

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
	 * Costruttore privato del menu, verrà eseguito uno volta sola.
	 */
	private Options() {	}

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
	 * Codice che verrà eseguito continuamente
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
