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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tetris.resources.ResourceLoader;
//Project Imports
import tetris.tetrisScene.SceneHandler;
import tetris.tetrisScene.TetrisScene;

/**
 * @author Franco
 *
 */
public class Tetris extends Application {
	
	// Private Parameters
	
	/**
	 * Il primaryStage è la finestra che viene creata su windows/linux.
	 */
	private static Stage PRIMARYSTAGE;
	
	/**
	 * La Main scene è la scena che viene attacata alla finestra.
	 * NB: Il fatto che si chiami "scene" può creare confusione con lo
	 * "sceneHandler" => Sono due cose totalmente differenti.
	 * Lo sceneHandler si occupa di cambiare il nodo principiale (ROOT) alla
	 * scena corrente, inoltre ogni schermata es. "Menu" è denominta una scena.
	 * Attenzione a non confondere.
	 */
	private static Scene MAINSCENE;
	
	/**
	 * Gestore di scene, da non confondere con la scena principale.
	 */
	private static SceneHandler SCENEHANDLER;
	
	/**
	 * Caricatore di risorse, singleton che si occupa di caricare le risorse grafiche/audio
	 * in memoria.
	 */
	private static ResourceLoader RESOURCELOADER = ResourceLoader.getInstance();
	
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
		
		// Imposta le dimensioni della finestra
		PRIMARYSTAGE.setWidth(700);
		PRIMARYSTAGE.setHeight(737);
		
		// Caricamento delle risorse
		RESOURCELOADER.loadResources();
		
		// Gestore di scene
		SCENEHANDLER = SceneHandler.getInstance();

		
		// Impostazione della MAINSCENE per permettere di
		// legarla allo stage principale.
		MAINSCENE = new Scene(SCENEHANDLER.getScene("Game").getRoot(), Color.rgb(36, 38, 47));	
		SCENEHANDLER.setScene("Game");
		
		// Aggiunta del gestore eventi per tasti
		MAINSCENE.setOnKeyPressed(SCENEHANDLER.getScene("Game"));
		
		// Aggiunge la MAINSCENE al primarystage da questo punto la gestione
		// di quale nodo "ROOT" deve essere attacato alla mainscene è compito delle
		// scene stesse.
		PRIMARYSTAGE.setScene(MAINSCENE);
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
	public static void setScene(String name){
		
		// Ultimo ciclo
		SCENEHANDLER.getCurrentScene().exit();
		
		// Imposta il root node corrente
		MAINSCENE.setRoot(SCENEHANDLER.getScene(name).getRoot());
		
		// Cambia la scena
		SCENEHANDLER.setScene(name);
		
		// Aggiunta del gestore eventi per tasti
		MAINSCENE.setOnKeyPressed(SCENEHANDLER.getScene(name));
		
		// Cerca di mostrare tutto ciò che è visibile
		PRIMARYSTAGE.show();
	}
	
	/**
	 * Restituisce il signletono gestore di scene
	 * 
	 * @return the scenehandler
	 */
	public static SceneHandler getSceneHandler() {
		return SCENEHANDLER;
	}

	/**
	 * Restituisce il gestore di risorse.
	 * 
	 * @return il caricatore di risorse
	 */
	public static ResourceLoader getResourceLoader() {
		return RESOURCELOADER;
	}
	
	/**
	 * Restituisce lo stage primario.
	 * 
	 * @return
	 */
	public static Stage getPrimaryStage() {
		return PRIMARYSTAGE;
	}

	/**
	 * Restituisce la scena corrente
	 */
	public static TetrisScene getCurrentScene() {
		return SCENEHANDLER.getCurrentScene();
	}
	
	
}
