/**
 * Classe Menu
 * 
 * Questa classe rappresenta il menu, ovvero la scena di default
 * che deve essere eseguita anche in caso di errori.
 */
package tetris.tetrisScene;

import java.util.ArrayList;

// Java Imports
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import tetris.entities.Button;
import tetris.entities.TText;
import tetris.entities.Image;
import tetris.entities.Sprite;
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
	
	/**
	 * Gruppo nel quale saranno contenuti gli elementi statici della grafica delle opzioni
	 */
	private final Group staticNodes = new Group();
	
	/**
	 * ArrayList per tenere traccia degli oggetti da renderizare nella scena.
	 */
	private ArrayList<Sprite> sprites = new ArrayList<>();  
	
	/**
	 * Variabile per tenere traccia del volume corrente
	 */
	private double volume = 0.5;
	
	/**
	 * Variabile per tenere traccia della defficoltà impostata
	 */
	private int difficulty = 2;
	
	private String DifficultyText = "Normale";
	
	/**
	 * Costruttore privato del menu, verrï¿½ eseguito uno volta sola.
	 */
	private Options() {	
		
		// Immagine di sfondo
		Image wallpaper = new Image((ImageResource) Tetris.getResourceLoader().getResource("OptionsWallpaper"));
		
		//Uso la classe TText per aggiungere tutti i testi
		TText Title = new TText(150, 50, "IMPOSTAZIONI", 50);
		TText VolumeValue = new TText(300,330,"Volume: " + getInt(getVolume()*100) + "%",35);
		TText Volume = new TText(190,295,"REGOLAZIONE AUDIO",35);
		TText DifficultyValue = new TText(300,180, tellDifficulty(difficulty, DifficultyText) ,35);
		TText Difficulty = new TText(190,145,"DIFFICOLTA' GIOCO",35);
		 		
		// Pulsante per diminuire la difficoltÃ 
		Button difficultyButtonMinus = new Button(200, 150, (SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton12"), new Runnable() {
					
			@Override
			public void run() {
				// DIFFICOLTA'
				setDifficulty(getDifficulty()-1);
				DifficultyValue.setnewText(tellDifficulty(getDifficulty(), DifficultyText));
			}
		});
				
		// Pulsante per aumentare la difficoltÃ 
		Button difficultyButtonPlus = new Button(240, 150, (SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton11"), new Runnable() {
					
			@Override
			public void run() {				
				// DIFFICOLTA'
				setDifficulty(getDifficulty()+1);
				DifficultyValue.setnewText(tellDifficulty(getDifficulty(), DifficultyText));		
			}
		});
						
		// Pulsante per diminuire il volume
		Button volumeButtonMinus = new Button(200, 300, (SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton12"), new Runnable() {
			
			@Override
			public void run() {
				// VOLUME
				setVolume(getVolume()-0.05);
				VolumeValue.setnewText("Volume: " + getInt(getVolume()*100) + "%");
			}
		});
		
		// Pulsante per aumentare il volume
		Button volumeButtonPlus = new Button(240, 300, (SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton11"), new Runnable() {
			
			@Override
			public void run() {
				// VOLUME
				setVolume(getVolume()+0.05);
				VolumeValue.setnewText("Volume: " + getInt(getVolume()*100) + "%");
			}
		});
		
		// Pulsante regole
		Button rulesButton = new Button(200, 410, (SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton8"), new Runnable() {
			
			@Override
			public void run() {
				// VISUALIZZAZIONE DELLE REGOLE
				
			}
		}); 
		
		// Pulsante menu
		Button menuButton = new Button(200, 560, (SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton9"), new Runnable() {
			
			@Override
			public void run() {
				Tetris.setScene("Menu");
				
			}
		}); 
		
		
		
		
		
		
		
		// Aggiunte degli elementi a staticnodes
		staticNodes.getChildren().add(wallpaper.getImage());
		staticNodes.getChildren().add(difficultyButtonMinus.getButtonImage());
		staticNodes.getChildren().add(difficultyButtonPlus.getButtonImage());
		staticNodes.getChildren().add(volumeButtonMinus.getButtonImage());
		staticNodes.getChildren().add(volumeButtonPlus.getButtonImage());
		staticNodes.getChildren().add(rulesButton.getButtonImage());
		staticNodes.getChildren().add(menuButton.getButtonImage());
		
		// Aggiunta di ogni sprite all array
		sprites.add(wallpaper);
		sprites.add(difficultyButtonPlus);
		sprites.add(difficultyButtonMinus);
		sprites.add(volumeButtonPlus);
		sprites.add(volumeButtonMinus);
		sprites.add(rulesButton);
		sprites.add(menuButton);
				
		// Aggiunta di tutti i testi 
		staticNodes.getChildren().add(Difficulty.getText());
		staticNodes.getChildren().add(DifficultyValue.getText());
		staticNodes.getChildren().add(Title.getText());
		staticNodes.getChildren().add(VolumeValue.getText());
		staticNodes.getChildren().add(Volume.getText());
		
		// Aggiunta di static nodes a root
		ROOT.getChildren().add(staticNodes);		
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

	}

	/**
	 * Codice che verrï¿½ eseguito continuamente
	 */
	@Override
	public void loop(long now) {
		for (Sprite sprite : sprites) {
			sprite.update(now);
		}
	}

	/**
	 * Gestione dei tasti
	 */
	@Override
	public void handle(KeyEvent event) {}
	
	/**
	 * Getter per il volume corrente
	 */
	public double getVolume() {
		return volume;
	}
	
	/**
	 * Setter per il volume
	 * 
	 * @param v Volume impostato
	 */
	private void setVolume(double v) {
		if(v > 1.0) {
			v = 1.0;
		}
		if(v < 0.0) {
			v = 0.0;
		}
		volume = v;
	}

	/**
	 * Getter per il valore della difficoltà
	 */
	public int getDifficulty() {
		return difficulty;
	}
	
	/**
	 * Setter per il valore della difficoltà
	 * 
	 * @param d Difficolà a cui si vuole impostare il gioco
	 */
	private void setDifficulty(int d) {
		if(d > 3) {
			d = 3;
		}
		if(d < 1) {
			d = 1;
		}
		difficulty = d;
	}
	
	// converte un valore intero per la difficolta in una parola
	private String tellDifficulty(int d, String Dif) {	
		if(d==2) {
			Dif="Normale";			
		}
		if(d==1) {
			Dif="Facile";
		}
		if(d==3){
			Dif="Estremo";
		}		
		return Dif;
	}
	
	
	private int getInt(double d) {
		int i = (int) d;
		if(d-i>0.6) {
			i++;
		}
		return i;
	}
}
