/**
 * Classe Menu
 * Questa classe rappresenta il menu, ovvero la scena di default
 * che deve essere eseguita anche in caso di errori.
 */
package tetris.tetrisScene;

// Java Imports
import javafx.scene.Group;
import javafx.scene.Scene;
import tetris.main.Tetris;

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
	 * Costruttore privato del menu, verr� eseguito uno volta sola.
	 */
	private Menu() {	}

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
		Tetris.setScene(ROOT);
		System.out.println("EUREKA MENU FUNZIONA!!");
	}

	/**
	 * Codice che verr� eseguito continuamente
	 */
	@Override
	public void loop() {
		System.out.println("EUREKA SIAMO NEL LOOP DI MENU!!");		
	}

}
