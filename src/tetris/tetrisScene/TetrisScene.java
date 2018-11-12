/**
 * Classe TetriscScene
 * 
 * Questa classe corrisponde ad una schermata di tetris, 
 * da tale classe astratta verrano inplementate le schermate
 * menu, gioco, opzioni, leaderboard.
 */
package tetris.tetrisScene;

import javafx.event.EventHandler;
//Java Imports
import javafx.scene.Group;
import javafx.scene.input.KeyEvent;

/**
 * @author Franco
 *
 */
public abstract class TetrisScene implements EventHandler<KeyEvent> {
	
	/**
	 * Gruppo principale legato alla scena dove verrano inseriti
	 * tutti i nodi che rappresenteranno la grafica. 
	 */
	protected Group ROOT = new Group();	

	/**
	 * Il metodo init corrisponde a tutte le azioni che vanno compiute una
	 * volta sola prima di iniziare il loop.
	 */
	abstract void init();
	
	
	/**
	 * Il metodo loop deve essere eseguito almeno ogni 1000/fps ms.
	 */
	abstract void loop(long now);
	
	/**
	 * Metodo chiamato ogni volta che si esce dalla scena
	 */
	public void exit() {};
	
	/**
	 * Getter per ROOT, usabile al difuori del paccheto tetrisScene
	 */
	public Group getRoot(){
		return ROOT;
	}
	
}

/**
 * OSSERVAZIONI:
 * 
 * Per implementare la gestione dei tasti / mouse consultare la seguente risorsa:
 * https://docs.oracle.com/javase/8/javafx/events-tutorial/handlers.htm
 * 
 */
