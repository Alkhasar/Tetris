/**
 * Classe Menu
 * 
 * Questa classe rappresenta il menu, ovvero la scena di default
 * che deve essere eseguita anche in caso di errori.
 */
package tetris.tetrisScene;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
// Java Imports
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import tetris.entities.BaseElement;
import tetris.entities.Button;
import tetris.entities.Grid;
import tetris.entities.Tetramino;
// Project imports
import tetris.main.Tetris;
import tetris.resources.AudioResource;
import tetris.resources.ImageResource;
import tetris.resources.SpriteSheetResource;

/**
 * @author Franco
 * @author Stefan
 */
public class Game extends TetrisScene {
	
	private int[] delta = {0, 0};
	private boolean rotate = false;
	
	/**
	 * Costante di tempo
	 */
	private final long t = 1000000000;
	
	/**
	 * Griglia usata per tenere traccia degli elementi base //MODIFICARE 10, 20 NEL CODICE
	 */
	private Grid grid = Grid.getInstance(10, 20);
	
	/**
	 * Oggetto tetramino usato nel gioco
	 */
	private Tetramino tetramino = Tetramino.getInstance(grid); // MODIFICARE NEL CODICE
	
	/**
	 * Variabile per il tetramino da creare
	 */
	private int nextTetramino;
	
	/**
	 * Oggetto per creare numeri random
	 */
	private Random random = new Random();
	
	/**
	 * Numero di righe rimosse
	 */
	private long rows = 0;
	
	/**
	 * Long int per tenere traccia dell'ultimo frame
	 */
	private long last = 0;
	
	/**
	 * Gruppo nel quale saranno contenuti gli elementi statici della grafica
	 */
	private	final Group staticNodes = new Group();
	
	/**
	 * Gruppo nel quale saranno contenuti gli elementi dinamici della grafica
	 */
	private	Group dynamicNodes = new Group();
	
	/**
	 * Unica istanza del singleton
	 */
	private static TetrisScene INSTANCE;
	
	/**
	 * Brano corrente
	 */
	private AudioResource music;
	
	/**
	 * Costruttore privato del menu, verrà eseguito uno volta sola.
	 */
	private Game() {	
		
		// Immagine di sfondo
		ImageResource wallpaper1 = (ImageResource) Tetris.getResourceLoader().getResource("myWallpaper");
		
		// usiamo la classe pulsante per crearne due
		Button menuButton = new Button(20,20,(SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton"),new Runnable()  {
		// definiamo il nostro runnable, in pratica diciamo cosa deve fare il pulsante	
			@Override
			public void run() {
				Tetris.setScene("Menu");
				
			}
		} );
		
		Button leaderboardButton = new Button(20,90,(SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton2"),new Runnable()  {
				
			@Override
			public void run() {
				Tetris.setScene("LeaderBoard");
					
				}
			} );
		
		// aggiungiamo a staticNodes tutta la nostra grafica 		
		staticNodes.getChildren().add(wallpaper1.getImageCanvas());
		staticNodes.getChildren().add(menuButton.getButtonImage());
		staticNodes.getChildren().add(leaderboardButton.getButtonImage());
		
		// Aggiunta dei nodi statici e dinamici a ROOT
		ROOT.getChildren().add(dynamicNodes);
		ROOT.getChildren().add(staticNodes);
		
		
	}

	private void newTetramino(int type) {
		try {
			tetramino.newTetramino(type);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Inizializzatore del singleton
	 * 
	 * @return unica istanza del menu.
	 */
	public static TetrisScene getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new Game();
		}
		return INSTANCE;
	}
	
	/** 
	 * Codice iniziale per la preparazione del menu.
	 */
	@Override
	public void init() {
		newTetramino(random.nextInt(7));
		for (BaseElement baseElement : tetramino.getBaseElement()) {
			dynamicNodes.getChildren().add(baseElement.getImageCanvas());
		}
		nextTetramino = random.nextInt(7);
		
		// Verifico il funzionamento dell'audio																		
		music = (AudioResource) Tetris.getResourceLoader().getResource("myAudio");
		music.loop();
		music.play();
		
		// Cerca di mostrare tutto ciò che è visibile
		Tetris.getPrimaryStage().show();
	}

	/**
	 * Codice che verrà eseguito continuamente
	 */
	@Override
	public void loop(long now) {

		if(delta[0]!=0 || delta[1]!=0) {
			tetramino.moveTetramino(delta[0], delta[1]);
			delta[0] = delta[1] = 0;
		}else if(rotate) {
			tetramino.rotateDxTetramino();
			rotate = false;
		}
		
		// Verifica se è necessaria la creazione di un nuovo tetramino
		if(tetramino.isStopped()) {
			tetramino.addToGrid();
			grid.print();
			System.out.println("---------------------------------------");//DEBUG
			newTetramino(nextTetramino);//DEBUG
			nextTetramino = random.nextInt(7);
			for (BaseElement baseElement : tetramino.getBaseElement()) {
				dynamicNodes.getChildren().add(baseElement.getImageCanvas());
			}
		} 
		
		if((now - last) > t/((rows)/10+1)) {
			last = now;
			tetramino.moveTetramino(0, 1);
		}
		
		// Verifica se è necessario rimuovere una riga
		for(int y = 0; y < 20; y++) {	
			BaseElement[] row;
			if((row = grid.checkRow(y)) != null) {
				for (BaseElement baseElement : row) {
					removeNodes(baseElement.getImageCanvas());
					rows++;
				}
				grid.emptyRow(y);
				music.setFeedRate(music.getCurrentrate()+0.03);
			}
		}

	}

	/**
	 * Codice eseguito all'uscita
	 */
	@Override
	public void exit() {
		// Distruggere tutto cio che è creato nell'init
		dynamicNodes.getChildren().clear();
		grid.clear();
		tetramino.clear();
		music.stopLoop();
		music.stop();
		music = null;
		
		// Interruzione del loop corrente per sicurezza
		Tetris.getSceneHandler().stopCurrentLoop();
	}

	/**
	 * Questo metodo viene usato per gestire gli eventi della tastiera
	 */
	@Override
	public void handle(KeyEvent event) {		
		switch (event.getCode()) {
			case DOWN:
				delta[1] = 1;
				break;
				
			case LEFT:
				delta[0] = -1;
				break;
				
			case RIGHT:
				delta[0] = 1;
				break;
				
			case UP:
				rotate = true;
				break;
			default:
				break;
		}
		
	}
	
	/**
	 * Metodo per rimuovere un nodo specifico da dynamicNodes
	 * 
	 * @param il nodo da rimuovere
	 */
	public void removeNodes(Node n) {
		dynamicNodes.getChildren().remove(n);
	}
}
