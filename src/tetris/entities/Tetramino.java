/**
 * 
 */
package tetris.entities;

import java.util.ArrayList;
import java.util.Random;

import tetris.main.Tetris;
import tetris.resources.Resource;
import tetris.resources.SpriteSheetResource;
import tetris.tetrisScene.Game;

/**
 * @author Marco Pellegrino
 *
 */
public class Tetramino {
	
	/**
	 * Unica istanza del Tetramino
	 */
	private static Tetramino INSTANCE;
	
	/**
	 * Copia locale della griglia creata
	 */
	private Grid grid;
	
	/**
	 * Tiene il valore del tipo di tetramino corrente
	 */
	private int currentType;
	
	/**
	 * Variabile che indica se si è in fase di gameOver
	 */
	private boolean gameOver = false;
	
	/**
	 * Variabile che indica se è possibile muovere ancora il tetramino
	 */
	private boolean stopped = false;
	
	/**
	 * Instanziamento di un'oggetto Random, per ottenere numeri randomici
	 */
	private Random random = new Random();
	
	/**
	 * Risorsa spriteSheet usata per gli elementi base
	 */
	private SpriteSheetResource res = (SpriteSheetResource) Tetris.getResourceLoader().getResource("mySpriteSheet");
	
	/**
	 * Lista contenente tutti gli elementi base del tetramino corrente
	 */
	private ArrayList<BaseElement> currentTetramino = new ArrayList<>();
	
	
	/**
	 * Enum per tenere traccia del tipo di collisione 
	 */
	public enum collision {
		NONE, LEFT, RIGHT, BOTTOM, GRID_Y, GRID_X, GRID_XY, LOST
	}
	
	/**
	 * Costruttore privato del tetramino
	 */
	private Tetramino(Grid grid) {
		this.grid = grid;
	}
	
	/**
	 * Costruttore del singleton
	 * 
	 * @return l'unica istanza del singleton
	 */
	public static Tetramino getInstance(Grid grid){
		if(INSTANCE==null){
			INSTANCE=new Tetramino(grid);
		}
		return INSTANCE;
	}
	
	/**
	 * Genera un nuovo tetramino alla data posizione
	 * 
	 * @param tipo del tetramino da generare
	 * @throws un'eccezione se viene inserito un tipo differente da quelli esistenti
	 */
	public void newTetramino(int type) throws Exception{	
		currentType = type;
		currentTetramino.clear();
		// Posizioni inizale
		int x = 64 + random.nextInt(6)*32 + 190;
		int y = -2*32 + 30;
		
		switch(type){
		
			//creazione dei tetramini utilizzando le diverse disposizioni degli elementi base e la il relativo sprite
			
			case 0:
				currentTetramino.add(new BaseElement(x, y, res, 0, 				0, 0));
				currentTetramino.add(new BaseElement(x + 32, y, res, 0, 		1, 0));
				currentTetramino.add(new BaseElement(x, y - 32, res, 0, 		0, -1));
				currentTetramino.add(new BaseElement(x + 32, y - 32, res, 0, 	1, -1));
			break;
			
			case 1:
				currentTetramino.add(new BaseElement(x - 64, y, res, 1, 		-1, 0));
				currentTetramino.add(new BaseElement(x - 32, y, res, 1, 		 0, 0));
				currentTetramino.add(new BaseElement(x, y , res, 1, 			 1, 0));
				currentTetramino.add(new BaseElement(x , y - 32, res, 1, 		 1, -1));
			break;
			
			case 2:
				currentTetramino.add(new BaseElement(x, y, res, 2, 				 -1, 0));
				currentTetramino.add(new BaseElement(x, y - 32, res, 2,			 -1, -1));
				currentTetramino.add(new BaseElement(x + 32, y, res, 2,			 0, 0));
				currentTetramino.add(new BaseElement(x + 64, y, res, 2,			 1, 0));
			break;
			
			case 3:
				currentTetramino.add(new BaseElement(x - 32, y ,res, 3,			 -1, 0));
				currentTetramino.add(new BaseElement(x , y, res, 3, 			 0, 0));
				currentTetramino.add(new BaseElement(x, y - 32, res, 3, 		 0, -1));
				currentTetramino.add(new BaseElement(x + 32, y, res, 3, 		 1, 0));
			break;	
				
			case 4:
				currentTetramino.add(new BaseElement(x, y, res, 4,				 0, -1));
				currentTetramino.add(new BaseElement(x - 32, y, res, 4,			 -1, -1));
				currentTetramino.add(new BaseElement(x, y + 32, res, 4,			 0, 0));
				currentTetramino.add(new BaseElement(x+32, y+32, res, 4,		 1, 0));
			break;	
				
			case 5:
				currentTetramino.add(new BaseElement(x, y, res, 5, 				 0, 0));
				currentTetramino.add(new BaseElement(x - 32, y, res, 5, 		-1, 0));
				currentTetramino.add(new BaseElement(x, y - 32, res, 5, 		0, -1));
				currentTetramino.add(new BaseElement(x + 32, y - 32, res, 5, 	1, -1));
			break;	
			
			case 6:
				currentTetramino.add(new BaseElement(x, y, res, 6, 				0, 0));
				currentTetramino.add(new BaseElement(x - 32, y, res, 6, 		-1, 0));
				currentTetramino.add(new BaseElement(x + 64, y, res, 6, 		2, 0));
				currentTetramino.add(new BaseElement(x + 32, y, res, 6, 		1, 0));
			break;	
			
			default:
				throw new Exception("Exception: The given time does not exist.");
		}
		stopped = false;
	}
	
	/**
	 * Metodo chemuove i tetramini lungo x e y
	 * 
	 * @param la coordinata x del versore
	 * @param la coordinata y del versore
	 */
	public boolean moveTetramino(int x,int y){
		collision border = checkBorderCollision(x, y);
		collision grid = checkGridCollision(x, y);
		if(border == collision.NONE && grid == collision.NONE){
			for  (BaseElement baseElement : currentTetramino) {
				baseElement.setY(baseElement.getY() + y*32);
				baseElement.setX(baseElement.getX() + x*32);
			}
			return true;
		}
		if(border == collision.BOTTOM || grid == collision.GRID_Y || grid == collision.GRID_XY) {
			stop();
		}
		return false;
	}
	
	/** 
	 * Metodo che ruota i tetramini a Dx (orario), prima di ruotare il tetramino corrente
	 * si accerta che sia possibile effetuare una rotazione
	 */
	public void rotateDxTetramino() {
			boolean canRotate = true;
			if(currentType != 0 && currentType != 6) {
				for (BaseElement baseElement: currentTetramino) {
					collision left  = checkLeftBorderCollision(baseElement.getRelY(), -baseElement.getRelX());
					collision right  = checkRightBorderCollision(baseElement.getRelY(), -baseElement.getRelX());
					collision bottom  = checkBottomBorderCollision(baseElement.getRelY(), -baseElement.getRelX());
					collision gridCollision = checkGridCollision(baseElement.getRelY(), -baseElement.getRelX());
					if((baseElement.getY()*32) < 30  || left != collision.NONE || right != collision.NONE || bottom != collision.NONE || gridCollision != collision.NONE){
						canRotate = false;
					}
					
				}
				if(canRotate) {
					for (BaseElement baseElement: currentTetramino) {
						// Referenze locali per evitare errori
						int relY = baseElement.getRelY();
						int relX = baseElement.getRelX();
						
						// Cambio di coordinata
						baseElement.setRelX(relY);
						baseElement.setRelY(-relX);
					}
				}
			} else if(currentType == 6) {
				for (BaseElement baseElement : currentTetramino) {
					baseElement.swapRelCoordinates();
				}
			}
	}
	
	private collision checkBorderCollision(int x, int y) {
		collision left = checkLeftBorderCollision(x, y);
		collision right = checkRightBorderCollision(x, y);
		collision bottom = checkBottomBorderCollision(x, y);
		return (left!=collision.NONE)?left:((right!=collision.NONE)?right:bottom);
	}
	
	private collision checkLeftBorderCollision(int x, int y) {
		for(BaseElement baseElement:currentTetramino) {
			if((baseElement.getX() + x*32) < 190){
				return collision.LEFT;
			} 
		}
		return collision.NONE;
	}
	private collision checkRightBorderCollision(int x, int y) {
		for(BaseElement baseElement:currentTetramino) {
			if((baseElement.getX() + x*32) > 478){
				return collision.RIGHT;
			} 
		}
		return collision.NONE;
	}
	private collision checkBottomBorderCollision(int x, int y) {
		for(BaseElement baseElement:currentTetramino) {
			if((baseElement.getY() + y*32) > 640){
				return collision.BOTTOM;
			} 
		}
		return collision.NONE;
	}
	
	private collision checkGridCollision(int x, int y) {
		for(BaseElement baseElement:currentTetramino) {
			if(grid.isFilled(((baseElement.getX() + x*32 - 190)/32), ((baseElement.getY() + y*32 - 30)/32))){
			if(x==0) {
				if(baseElement.getY() <= 30) {
					System.out.println("GAME OVER");
					gameOver = true;
					return collision.LOST;
					//System.exit(0);
				}
				return collision.GRID_Y;
			}else if(y==0) {
				return collision.GRID_X;
			}else {
				return 	collision.GRID_XY;		
			}
		}
	
		}
		return collision.NONE;
	}
	
	/** 
	 * Aggiunge gli elementi che formano i tetramini alla griglia una volta che sono atterrati 
	 */
	public void addToGrid() {
		for(BaseElement baseElement:currentTetramino){	
			grid.insertElement(baseElement, (int) ((baseElement.getX()-190)/32), (int) ((baseElement.getY()-20)/32));		
			}
		currentTetramino.clear();
	}

	/**
	 * Getter per la fariabile isStopped
	 * 
	 * @return se il tetramino è fermo
	 */
	public boolean isStopped() {
		return stopped;
	}
	
	/**
	 * Setter per impostare stopped a true
	 */
	public void stop() {
		stopped = true;
	}
	
	/**
	 * Metodo per ottenere la lista di elementi base che compongo il tetramino
	 * 
	 * @return la lista di elementi base
	 */
	public ArrayList<BaseElement> getBaseElement() {
		return currentTetramino;
	}
	
	/**
	 * Metodo chiamato quando il tetramino va ripulito, per essere portato allo stato
	 * iniziale
	 */
	public void clear() {
		gameOver = false;
		currentTetramino.clear();
	}
	
	/**
	 * Variabile che fa partire il gameOver
	 * 
	 * @return se è gameOver o meno
	 */
	public boolean isGameOver() {
		return gameOver;
	}
}
