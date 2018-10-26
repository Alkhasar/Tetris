/**
 * Classe Tetris
 * 
 * E' la classe principale dalla quale partirà la finestra nella quale 
 * verrà eseguito il gioco. Il sistema passerà in automatico il primary
 * stage, ovvero la finestra nel quale eseguire l'applicazione.
 */
package tetris.main;

//Java Imports
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Project Imports
import tetris.tetrisScene.SceneHandler;
import tetris.tetrisScene.TetrisScene;

/**
 * @author Franco
 *
 */
public class Tetris extends Application {
	
	// Public Parameters
	
	// Private Parameters
	private static Stage PRIMARYSTAGE;
	private static Scene MAINSCENE;
	private static SceneHandler SCENEHANDLER;
	
	/**
	 * Metodo main, se lanciato da console prende gli argomenti e li inserisce in un array di stringhe.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(); // Questa riga serve a far partire l'applicazione
	}
	
	/** 
	 * Il metodo start è eseguito dopo il launch.
	 * 
	 * @param {Stage} Prende in automatico il parametro stage passatogli 
	 * 				  dal sistema.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		PRIMARYSTAGE = primaryStage;
		SCENEHANDLER = SceneHandler.getInstance();
		
		// Setting the scene to allow instantiation
		// of main scene
		MAINSCENE = new Scene(SCENEHANDLER.getScene("Menu").getRoot());
	}

	/**
	 * Questo metodo restituisce il nodo madre root al quale saranno
	 * attaccati tutti i nodi successivi.
	 * 
	 * @return la varibile statica root
	 */
	public static Scene getMainScene() {
		return MAINSCENE;
	}

	/**
	 * Setter per la scena corrente. Con questo metodo si puo cambiare
	 * quale scena deve essere renderizzata.
	 */
	public static void setScene(Group root){
		MAINSCENE.setRoot(root);
	}
	
	/**
	 * Returns the scene handler singleton.
	 * 
	 * @return the scenehandler
	 */
	public static SceneHandler getSceneHandler() {
		return SCENEHANDLER;
	}

}
