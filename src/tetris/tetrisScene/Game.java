/**
 * Classe Menu
 * 
 * Questa classe rappresenta il menu, ovvero la scena di default
 * che deve essere eseguita anche in caso di errori.
 */
package tetris.tetrisScene;


import java.util.ArrayList;
// Java Imports
import java.util.Random;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

// Project imports
import tetris.main.Tetris;
import tetris.resources.AudioResource;
import tetris.resources.ImageResource;
import tetris.resources.SpriteSheetResource;
import tetris.entities.BaseElement;
import tetris.entities.Button;
import tetris.entities.Grid;
import tetris.entities.Image;
import tetris.entities.Sprite;
import tetris.entities.TText;
import tetris.entities.Tetramino;

/**
 * @author Franco
 * @author Stefan
 */
public class Game extends TetrisScene {
	
	/**
	 * Versore direzione
	 */
	private int[] delta = {0, 0};
	
	/**
	 * Condizione che indica se il tetramino corrente è da ruotare
	 */
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
	private Tetramino tetramino = Tetramino.getInstance(grid);
	
	/**
	 * Variabile per il tetramino da creare
	 */
	private int nextTetramino;
	
	/**
	 * Oggetto per creare numeri random
	 */
	private Random random = new Random();
	
	/**
	 * Variabile booleana per sapere se il gioco è fermo o meno
	 */
	private boolean paused = false;
	
	/**
	 * Numero di righe rimosse
	 */
	private int rows = 0;
	
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
	 * Nodi usati se viene cliccato il pulsante pausa
	 */
	private Group pauseNodes = new Group();
	
	/**
	 * Nodi usati quando si perde, per inserire il proprio nome
	 */
	private Group scoreNodes = new Group();
	
	/**
	 * Unica istanza del singleton
	 */
	private static TetrisScene INSTANCE;
	
	/**
	 * Brano corrente
	 */
	private AudioResource music;
	
	/**
	 * ArrayList per tenere traccia degli oggetti da renderizare nella scena.
	 */
	private ArrayList<Sprite> sprites = new ArrayList<>();  
	
	/**
	 * Array per contenere le immagini dei tetramino
	 */
	private ArrayList<Image> tetraminoImages = new ArrayList<>();  
	
	/**
	 * Costruttore privato del menu, verrà eseguito uno volta sola.
	 */
	private Game() {	
		
		// Immagine di sfondo
		Image wallpaper = new Image((ImageResource) Tetris.getResourceLoader().getResource("myWallpaper"));//(ImageResource) Tetris.getResourceLoader().getResource("myWallpaper");
		
		// usiamo la classe pulsante per crearne due
		Button pauseButton = new Button(20,20,(SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton"),new Runnable()  {
		// definiamo il nostro runnable, in pratica diciamo cosa deve fare il pulsante	
			@Override
			public void run() {
				if(pauseNodes.isVisible()) {
					Tetris.getSceneHandler().startLoop();;
					pauseNodes.setVisible(false);
					paused = false;
				}else {
					Tetris.getSceneHandler().stopCurrentLoop();
					pauseNodes.setVisible(true);
					paused = true;
				}
			}
		} );
		
		// Pulsante per passare alla leaderboard
		Button leaderboardButton = new Button(275,325,(SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton2"),new Runnable()  {
				
			@Override
			public void run() {

				music.play();
				Tetris.setScene("LeaderBoard");
				}
			} );
		
		// Creazione della pausa
		Button menuButton = new Button(275,250,(SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton10"),new Runnable()  {
		// definiamo il nostro runnable, in pratica diciamo cosa deve fare il pulsante	
			@Override
			public void run() {
				Tetris.setScene("Menu");
			}
		} );
		
		// Creazione della casella di testo
		TextField playerName = new TextField("Anonimous");
		playerName.setLayoutX(275);
		playerName.setLayoutY(400);
		
		// Creazione del pulsante ritorno al menu
		Button returnToMenu = new Button(275, 325,(SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton10"),new Runnable()  {
		// definiamo il nostro runnable, in pratica diciamo cosa deve fare il pulsante	
			@Override
			public void run() {
				Tetris.newScore(playerName.getText(), rows);
				Tetris.setScene("Menu");
			}
		} );
		
		// Creazione degli sprite dinamici
		Image T0 = new Image(533 + 40, 312 + 8, (ImageResource) Tetris.getResourceLoader().getResource("0"));
		Image T1 = new Image(517 + 40, 318, (ImageResource) Tetris.getResourceLoader().getResource("1"));
		Image T2 = new Image(517 + 40, 318, (ImageResource) Tetris.getResourceLoader().getResource("2"));
		Image T3 = new Image(517 + 40, 318, (ImageResource) Tetris.getResourceLoader().getResource("3"));
		Image T4 = new Image(517 + 40, 318, (ImageResource) Tetris.getResourceLoader().getResource("4"));
		Image T5 = new Image(517 + 40, 318, (ImageResource) Tetris.getResourceLoader().getResource("5"));
		Image T6 = new Image(501 + 40, 334 + 8, (ImageResource) Tetris.getResourceLoader().getResource("6"));
		
		// Aggiunta delle immagini tetramino all array
		tetraminoImages.add(T0);
		tetraminoImages.add(T1);
		tetraminoImages.add(T2);
		tetraminoImages.add(T3);
		tetraminoImages.add(T4);
		tetraminoImages.add(T5);
		tetraminoImages.add(T6);
		
		// aggiungiamo a staticNodes tutta la nostra grafica 		
		staticNodes.getChildren().add(wallpaper.getImage());
		staticNodes.getChildren().add(pauseButton.getButtonImage());
		
		// aggiunta degli elementi di pause nodes
		pauseNodes.getChildren().add(menuButton.getButtonImage());
		pauseNodes.getChildren().add(leaderboardButton.getButtonImage());
		
		// Aggiunta degli elementi per il punteggio
		scoreNodes.getChildren().add(returnToMenu.getButtonImage());
		scoreNodes.getChildren().add(playerName);
		
		// Aggiunta degli sprite all'array
		sprites.add(wallpaper);
		sprites.add(pauseButton);
		sprites.add(leaderboardButton);
		sprites.add(menuButton);
		sprites.add(returnToMenu);
		sprites.addAll(tetraminoImages);
		
		// Impostazione delle visibilità
		dynamicNodes.setVisible(true);
		staticNodes.setVisible(true);
		pauseNodes.setVisible(false);
		scoreNodes.setVisible(false);
		
		// Aggiunta dei nodi statici e dinamici a ROOT
		ROOT.getChildren().add(dynamicNodes);
		ROOT.getChildren().add(staticNodes);
		ROOT.getChildren().add(pauseNodes);
		ROOT.getChildren().add(scoreNodes);
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
		// Creazione del primo tetramino
		newTetramino(random.nextInt(7));
		for (BaseElement baseElement : tetramino.getBaseElement()) {
			dynamicNodes.getChildren().add(baseElement.getImageCanvas());
			sprites.add(baseElement);
		}
		nextTetramino = random.nextInt(7);
		addNode(tetraminoImages.get(nextTetramino).getImage());
		sprites.add(tetraminoImages.get(nextTetramino));
		
		// Viene fatto partire l'audio																		
		music = (AudioResource) Tetris.getResourceLoader().getResource("myAudio");
		music.loop();
		music.play();
		
		// Viene impostato il volume dell'audio in base alle opzioni
		music.setVolume(((Options) Options.getInstance()).getVolume());
		
		// Cerca di mostrare tutto ciò che è visibile
		Tetris.getPrimaryStage().show();
	}

	/**
	 * Codice che verrà eseguito continuamente
	 */
	@Override
	public void loop(long now) {
		if(!(tetramino.isGameOver())) {

			System.out.println((now - last) + " - " + t/(Math.round((1-Math.pow(2.71, -(rows+1))))));
			if((now - last) > t/(Math.round((1-Math.pow(2.71, -(rows+1)))))) {

				last = now;
				tetramino.moveTetramino(0, 1);
			} else {
				
				// Verifica se è necessaria la creazione di un nuovo tetramino
				if(tetramino.isStopped()) {
//					System.out.println(tetramino.getBaseElement().size());
					tetramino.addToGrid();
//					grid.print();//DEBUG
//					System.out.println("---------------------------------------");	//DEBUG
					newTetramino(nextTetramino);
					swapTetramino(random.nextInt(7));
					for (BaseElement baseElement : tetramino.getBaseElement()) {
						addNode(baseElement.getImageCanvas());
						sprites.add(baseElement);
					}
				}
	
				// Verifica se è stata richiesta una rotazione o un movimento
				if(delta[0]!=0 || delta[1]!=0) {
					tetramino.moveTetramino(delta[0], delta[1]);
					delta[0] = delta[1] = 0;
				}else if(rotate) {
					tetramino.rotateDxTetramino();
					rotate = false;
				}
	
				// Verifica se è necessario rimuovere una riga
				for(int y = 0; y < 20; y++) {	
					BaseElement[] row;
					if((row = grid.checkRow(y)) != null) {
						for (BaseElement baseElement : row) {
							removeNode(baseElement.getImageCanvas());
							sprites.remove(baseElement);
						}
						rows++;
						grid.emptyRow(y);
						music.setFeedRate(music.getCurrentrate()+0.03);
					}
				}
			}
			
			// Aggiornamento della grafica di ogni sprite
			for (Sprite sprite : sprites) {
				sprite.update(now);
			}
		} else {
			dynamicNodes.setVisible(false);
			staticNodes.setVisible(false);
			scoreNodes.setVisible(true);
			Tetris.getSceneHandler().stopCurrentLoop();
		}
	}

	/**
	 * Codice eseguito all'uscita
	 */
	@Override
	public void exit() {
		System.out.println("HIGHSCORE: " + rows);
		
		// Distruggere tutto cio che è creato nell'init
		dynamicNodes.setVisible(true);
		staticNodes.setVisible(true);
		pauseNodes.setVisible(false);
		scoreNodes.setVisible(false);
		rows = 0;
		dynamicNodes.getChildren().clear();
		grid.clear();
		tetramino.clear();
		music.stopLoop();
		music.stop();
		music.setFeedRate(1.0);
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
			case ESCAPE:
				if (paused) {
					Tetris.getSceneHandler().startLoop();;
					pauseNodes.setVisible(false);
					paused = false;
				} else {
					Tetris.getSceneHandler().stopCurrentLoop();
					pauseNodes.setVisible(true);
					paused = true;
				}
				if (tetramino.isGameOver()) {
					Tetris.setScene("Menu");
				}
			default:
				break;
		}
		
	}
		
	/**
	 * Funzione usata per generare un nuovo tetramino
	 * 
	 * @param tipo di tetramino espresso come numero da 0-6
	 */
	private void newTetramino(int type) {
		// Gestisce l'eccezione di un numero non valido
		try {
			tetramino.newTetramino(type);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo per rimuovere un nodo specifico da dynamicNodes
	 * 
	 * @param il nodo da rimuovere
	 */
	public void removeNode(Node n) {
		dynamicNodes.getChildren().remove(n);
	}
	
	/**
	 * Metodo per aggiungere un elemento di grafica dinamica all'array
	 * 
	 * @param nodo da aggiungere
	 */
	private void addNode(Node n) {
		dynamicNodes.getChildren().add(n);
	}
	
	/**
	 * Funzione usata per cambiare immagine laterale del tetramino
	 * 
	 * @param indice del tetramino
	 */
	private void swapTetramino(int index) {
		removeNode(tetraminoImages.get(nextTetramino).getImage());
		addNode(tetraminoImages.get(index).getImage());
		sprites.add(tetraminoImages.get(index));
		sprites.remove(tetraminoImages.get(nextTetramino));
		nextTetramino = index;
	}

}
