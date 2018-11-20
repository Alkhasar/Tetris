/**
 * Classe Menu
 * 
 * Questa classe rappresenta il menu, ovvero la scena di default
 * che deve essere eseguita anche in caso di errori.
 */
package tetris.tetrisScene;

// Java Imports
import javafx.scene.Group;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

// Project Imports
import tetris.main.Tetris;
import tetris.resources.ImageResource;
import tetris.resources.SpriteSheetResource;
import tetris.entities.Button;
import tetris.entities.Image;
import tetris.entities.Sprite;

/**
 * @author Franco
 *
 */
public class LeaderBoard extends TetrisScene {
	
	/**
	 * Unica istanza del singleton
	 */
	private static TetrisScene INSTANCE;
	
	/**
	 * Array per tenere traccia del test
	 */
	private Text[][] textLines = new Text[10][2];
	
	/**
	 * Variabile finale contente il percorso ai punteggi
	 */
	private final String path = "./assets/scores.txt";
	
	/**
	 * Comparatore usato per ordinare l'array dei punteggi
	 */
	private Comparator<String[]> scoreSorter = new Comparator<String[]>() {
		@Override
		public int compare(String[] o1, String[] o2) {
			if(Integer.parseInt(o1[1]) == Integer.parseInt(o2[1])) {
				return 0;
			}
			return ((Integer.parseInt(o1[1]) < Integer.parseInt(o2[1]))) ? 1 : -1;
		}
	};
	
	/**
	 * HashMap conteneti nomi e punteggio
	 */
	//private HashMap<String, Long> scores = new HashMap<>();
	private ArrayList<String[]> scores = new ArrayList<>();
	
	/**
	 * ArrayList per tenere traccia degli oggetti da renderizare nella scena.
	 */
	private ArrayList<Sprite> sprites = new ArrayList<>();
	
	/**
	 * Gruppo nel quale saranno contenuti gli elementi statici della grafica delle opzioni
	 */
	private final Group staticNodes = new Group();
	
	/**
	 * Costruttore privato del menu, verrï¿½ eseguito uno volta sola.
	 */
	private LeaderBoard() {
		
		Image wallpaper = new Image((ImageResource) Tetris.getResourceLoader().getResource("LeaderboardWallpaper"));
		
		
		
		Button menuButton = new Button(200, 560, (SpriteSheetResource) Tetris.getResourceLoader().getResource("myButton9"), new Runnable() {
			
			@Override
			public void run() {
				Tetris.setScene("Menu");
				
			}
		});
		
		// Aggiunga elementi a static nodes
		staticNodes.getChildren().add(wallpaper.getImage());
		staticNodes.getChildren().add(menuButton.getButtonImage());
		
		// Aggiunta degli sprite all'array
		sprites.add(wallpaper);
		sprites.add(menuButton);
		
		for(int i = 0; i < 10; i++) {
			for(int k = 0; k<2; k++) {
				textLines[i][k] = new Text();
				textLines[i][k].setFont(new Font("Arial Black", 40));
				staticNodes.getChildren().add(textLines[i][k]);
				textLines[i][k].setX(190 + k*300);
				textLines[i][k].setY(50 + i*50);
				textLines[i][k].setStyle(
						"-fx-fill: #E6EF15;" + 
						"-fx-stroke: #003CE9;" + 
						"-fx-stroke-width: 1px;"
							);
			}
		}
		
		// Aggiunta static nodes a ROOT
		ROOT.getChildren().add(staticNodes);
		
		// Iniziallizazione valori base
		readTxt();
	}

	/**
	 * 
	 * @return unica istanza del menu.
	 */
	public static TetrisScene getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new LeaderBoard();
		}
		return INSTANCE;
	}
	
	/** 
	 * Codice iniziale per la preparazione del menu.
	 */
	@Override
	public void init() {
		// Lettura del testo dal file
		readTxt();
		System.out.println(scores.size());
		// Riordinamento dei punteggi
		sortScores(scores);
		System.out.println(scores.size());
		
		for(int i = 0; i < 10; i++) {
			textLines[i][0].setText(scores.get(i)[0]);
			textLines[i][1].setText(scores.get(i)[1]);
		}
	}

	/**
	 * Codice che verrï¿½ eseguito continuamente
	 */
	@Override
	public void loop(long now) {
		// Aggiornamento della grafica di ogni sprite
		for (Sprite sprite : sprites) {
			sprite.update(now);
		}
	}
	
	/**
	 * Funzione chiamata all'uscita
	 */
	@Override
	public void exit() {
		
	}
	
	/**
	 * Gestione dei tasti
	 */
	@Override
	public void handle(KeyEvent event) {}
	
	/**
	 * Funzione da eseguire per inserire un nuovo score
	 * @param Nome del gicoatore
	 * @param Punteggio
	 */
	public void newScore(String name, Integer score) {
		scores.add(new String[] {name, Integer.toString(score)});
		sortScores(scores);
		System.out.println(scores.size());
		scores.remove(scores.size() - 1);
		editTxt(scores);
	}
	
	/**
	 * Funzione per aggiornare i punteggi
	 */
	private void readTxt() {
		scores.clear();
		// Lo stream è automaticamente chiuso WITH-OPEN-CLOSE
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
		    String line;
		    while ((line = br.readLine()) != null) {
	    		String[] splitLine = line.split("-");
		    	scores.add(splitLine);
		    }
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Funzione per riscrivere i punteggi
	 * @param string
	 */
	private void editTxt(ArrayList<String[]> scores){   
		FileWriter fw = null;
        BufferedWriter bw = null;
		try{
			fw = new FileWriter(path, false);
			bw = new BufferedWriter(fw);
			for(int i=0; i<scores.size(); i++){
				if(scores.get(i) != null) {        
					bw.write(scores.get(i)[0] + "-" + scores.get(i)[1]);
				}
				if(i<scores.size()-1) {
					bw.write("\n");    
				}
			}
		} catch(IOException e){
			System.out.println(e);   
		} finally {
			try {
				bw.close();
				fw.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void sortScores(ArrayList<String[]> scores) {
		Collections.sort(scores, scoreSorter);
	}
}
