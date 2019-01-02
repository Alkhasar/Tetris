/**
 * Classe SpriteSheet
 * 
 * Usata per dividere le immagini provenienti da uno sprite sheet.
 */
package tetris.resources;

// Java Imports
import java.util.ArrayList;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpriteSheetResource extends Resource {
	
	/**
	 * SpriteSheet caricato
	 */
	private Image spriteSheet;
	
	/**
	 * Dimensioni di una singola immagine nel seguente ordine:
	 * {width, height, rows, columns}
	 */
	private int[] sizes;
	
	/**
	 * Numero di immagini immagazzinate
	 */
	private int numberOfImg;
	
	/**
	 * Vettore contenente i vari frame dello spritesheet.
	 */
	private ArrayList<Canvas> frames = new ArrayList<>(); 
	
	/**
	 * Costruttore dello spriteSheet, prende una risorsa, le dimensioni di una singola immagine
	 * il numero di righe e colonne nello spriteSheet e il numero di immagini.
	 * 
	 * @param lo spritesheet
	 * @param largezza di una singola immagine
	 * @param altezza di una singola immagine
	 * @param numero di immagini
	 * @param numero di righe
	 * @param numero di colonne
	 * @throws FileNotFoundException 
	 */
	public SpriteSheetResource(String path, int width, int height, int numberOfImg, int rows, int columns) throws FileNotFoundException {
		super(path);
		
		// Inizializzazione parametri
		this.sizes = new int[] {width, height, rows, columns};
		this.numberOfImg = numberOfImg;
		this.spriteSheet = new Image(new FileInputStream(file));
		
		// Ritaglio dello spritesheet principale in più canvas.
		for(int y = 0; y < rows; y++) {
			for(int x = 0; x < columns; x++) {
				if(x*y < numberOfImg) {
					Canvas c = new Canvas(width, height);
					c.getGraphicsContext2D().drawImage(this.spriteSheet, // Prende l'img e copia il rettangolo che va da
														x * width, 		//  (x*width)px
														y * height,		// 	(y*height)px di 
														width,			//  larghezza
														height,			//  e altezza, nel canvas c alle coordinate
														0,				//  x=0
														0,				//	y=0 scalando l'img alla 
														width,			//  larghezza
														height);		//  e all'altezza.
					frames.add(c);
				}
			}
		}
	}
	
	/**
	 * Getter per i frame dello spritesheet, il getter crea un nuovo canvas
	 * sul quale disegna una nuova immagine per evitare problemi riguardanti
	 * due oggetti che utilizzano la stessa immagine.
	 * 
	 * @param indice del frame
	 * @return
	 */
	public Canvas getFrame(int index) {
		// Crea una copia dell' elementobase
		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.RED);
		WritableImage tmpImg = frames.get(index).snapshot(params, null);
		
		// Crea un nuovo canvas vi copia l'immagine del tetramino e lo restituisce
		Canvas temp = new Canvas();
		temp.setWidth(frames.get(index).getWidth());
		temp.setHeight(frames.get(index).getHeight());
		temp.getGraphicsContext2D().drawImage(tmpImg, 0, 0);

		return temp;
	}
	
	/**
	 * @return the sizes
	 */
	public int[] getSizes() {
		return sizes;
	}

	/**
	 * @return the numberOfImg
	 */
	public int getNumberOfImg() {
		return numberOfImg;
	}
	
	
}
