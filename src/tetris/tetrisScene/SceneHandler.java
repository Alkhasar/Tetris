/**
 * Classe StageHandler
 * Questa classe è responsibile di gestire e conservare le varie schermate.
 * E' sviluppata seguendo la logica del Singleton, ovvero una classe della quale
 * può esistere una sola istanza.
 */
package tetris.tetrisScene;

// Java Imports
import java.util.Hashtable;
import javafx.animation.AnimationTimer;

// Same Package import
import tetris.tetrisScene.TetrisScene;
import tetris.tetrisScene.Menu;
import tetris.tetrisScene.Options;
import tetris.tetrisScene.Game;
import tetris.tetrisScene.LeaderBoard;

/**
 * @author Franco
 *
 */
public class SceneHandler {
	 
	/**
	 * Hashtable nella quale sono contenute le scene identificate
	 * per nome.
	 */
	private final Hashtable<String, TetrisScene> scenes = new Hashtable<>();
	
	/** 
	 * Tiene il nome della scena corrente
	 */
	private String currentScene = "";
	
	/**
	 * Unica istanza creabile di SceneHandler
	 */
	private static SceneHandler INSTANCE;
	
	/** 
	 * Crea un'istanza dell'animation timer e lo imposta per eseguire
	 * il loop della scena corrente
	 */
	private AnimationTimer TIMER = new AnimationTimer() {
		
		@Override
		public void handle(long now) {
			scenes.get(getCurrentSceneName()).loop();
		}
	};;
	
	/**
	 * Costruttore di scene handler, verrà instanziato solo una volta
	 */
	private SceneHandler() {
		this.scenes.put("Menu", Menu.getInstance());
		this.scenes.put("Opzioni", Options.getInstance());
		this.scenes.put("Gioco", Game.getInstance());
		this.scenes.put("LeaderBoard", LeaderBoard.getInstance());
	}
	
	private TetrisScene getCurrentScene() { 
		return scenes.get(getCurrentSceneName());
	}
	
	/**
	 * Questo è il metodo che dovrebbe essere chiamato per istanziare lo sceneHandler
	 * 
	 * @return la singola istanza dei sceneHandler.
	 */
	public static SceneHandler getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new SceneHandler();
		}
		return INSTANCE;
	}
	
	/**
	 * Metodo per impostare la scena corrente.
	 * 
	 * @param nome della scena da impostare
	 */
	public void setScene(String sceneName) {
		currentScene = sceneName;
		if(currentScene != "") {
			// Interrompe il loop della scena corrente
			stopCurrentLoop();
		}
		getCurrentScene().init();
		startLoop();
	}

	
	public void startLoop() {
		TIMER.start();
	}

	public void stopCurrentLoop() {
		TIMER.stop();
	}
	
	public String getCurrentSceneName() {
		return currentScene;
	}
	
	public TetrisScene getScene (String sceneName){
		return scenes.get(sceneName);
	}
}
