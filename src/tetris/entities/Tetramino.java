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
	 * Variabile che indica se è possibile muovere ancora il tetramino
	 */
	private boolean stopped = false;
	
	/**
	 * Lista contenente tutti gli elementi base del tetramino corrente
	 */
	private ArrayList<BaseElement> currentTetramino = new ArrayList<>();
	
	/**
	 * Instanziamento di un'oggetto Random, per ottenere numeri randomici
	 */
	private Random random = new Random();
	
	/**
	 * Risorsa spriteSheet usata per gli elementi base
	 */
	private SpriteSheetResource res = (SpriteSheetResource) Tetris.getResourceLoader().getResource("mySpriteSheet");
	
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
				currentTetramino.add(new BaseElement(x - 64, y, res, 1, 		-2, 0));
				currentTetramino.add(new BaseElement(x - 32, y, res, 1, 		-1, 0));
				currentTetramino.add(new BaseElement(x, y , res, 1, 			 0, 0));
				currentTetramino.add(new BaseElement(x , y - 32, res, 1, 		0, -1));
			break;
			
			case 2:
				currentTetramino.add(new BaseElement(x, y, res, 2, 				 0, 0));
				currentTetramino.add(new BaseElement(x, y - 32, res, 2,			 0, -1));
				currentTetramino.add(new BaseElement(x + 32, y, res, 2,			 1, 0));
				currentTetramino.add(new BaseElement(x + 64, y, res, 2,			 2, 0));
			break;
			
			case 3:
				currentTetramino.add(new BaseElement(x - 32, y ,res, 3,			 -1, 0));
				currentTetramino.add(new BaseElement(x , y, res, 3, 			 0, 0));
				currentTetramino.add(new BaseElement(x, y - 32, res, 3, 		 0, -1));
				currentTetramino.add(new BaseElement(x + 32, y, res, 3, 		 1, 0));
			break;	
				
			case 4:
				currentTetramino.add(new BaseElement(x, y, res, 4,				 0, 0));
				currentTetramino.add(new BaseElement(x - 32, y, res, 4,			 -1, 0));
				currentTetramino.add(new BaseElement(x, y + 32, res, 4,			 0, 1));
				currentTetramino.add(new BaseElement(x+32, y+32, res, 4,		 1, 1));
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
	public void moveTetramino(int x,int y){
		if(!(checkCollision(x, y))){
			for  (BaseElement baseElement : currentTetramino) {
				baseElement.setY(baseElement.getY() + y*32);
				baseElement.setX(baseElement.getX() + x*32);
			}
		}
	}
	
	/** 
	 * Metodo che ruota i tetramini a Dx (orario)
	 * 
	 * @param la coordinata x del tetramino da ruotare
	 * @param la coordinata y del tetramino da ruotare
	 */
	public void rotateDxTetramino() {
			boolean temp = true;
			for (BaseElement baseElement: currentTetramino) {
				// Referenze locali per evitare errori
				int X = ((baseElement.getX() + baseElement.getRelY()*32 - baseElement.getRelX()*32)-190)/32;
				int Y = ((baseElement.getY() - baseElement.getRelX()*32 - baseElement.getRelY()*32)-30)/32;
				
				if((Y*32) < 30  || (checkCollision(baseElement.getRelY()-baseElement.getRelX(), -baseElement.getRelX()-baseElement.getRelY()))){
					System.out.println("COOOOLISION");
					temp = false;
				}
			}
			if(temp) {
				for (BaseElement baseElement: currentTetramino) {
					// Referenze locali per evitare errori
					int relY = baseElement.getRelY();
					int relX = baseElement.getRelX();
					
					// Cambio di coordinata
					baseElement.setRelX(relY);
					baseElement.setRelY(-relX);
				}
			}
	}
	
	/** RIVEDERE
	 * Metodo che verifica se elementi adiacenti della griglia sono gi� occupati 
	 * 
	 * @param la coordinata x del versore
	 * @param la coordinata y del versore
	 * @return True se avviene una collisione
	 */
	private boolean checkCollision(int x, int y){
		for(BaseElement baseElement:currentTetramino) {
			if(grid.isFilled(((baseElement.getX() - 190)/32), ((baseElement.getY() + y*32 - 30)/32))){
				stopped = true;
				if(baseElement.getY() <= 30) {
					System.out.println("GAME OVER");
					Tetris.setScene("Menu");
					//System.exit(0);
					
					
				}
				return 	true;		
			}
			
			if(grid.isFilled(((baseElement.getX() + x*32 - 190)/32), ((baseElement.getY() + y*32 - 30)/32))){
				return 	true;		
			}
		
		
			if((baseElement.getX() + x*32) > 478 || (baseElement.getX() + x*32) < 190){
				return true;
			} 
		
			
			if((baseElement.getY() + y*32) > 640){ 
				stopped = true;
				return true;
			} 
				
		}
			
		return false;
	}	

	/** RIVEDERE
	 * Aggiunge gli elementi che formano i tetramini alla griglia una volta che sono atterrati 
	 * 
	 * @param coordinata x a cui aggiungere il tetramino
	 * @param coordinata y a cui aggiugnere il tetramino
	 */
	public void addToGrid() {
		for(BaseElement baseElement:currentTetramino){	
			grid.insertElement(baseElement, (int) ((baseElement.getX()-190)/32), (int) ((baseElement.getY()-20)/32));		
			}
		currentTetramino.clear();
	}

	/**
	 * @return se il tetramino è fermo
	 */
	public boolean isStopped() {
		return stopped;
	}
	
	public ArrayList<BaseElement> getBaseElement() {
		return currentTetramino;
	}
	
	public void clear() {
		currentTetramino.clear();
		System.out.println("CLEARED");
		for (BaseElement baseElement : currentTetramino) {
			System.out.println("T: " + baseElement);
		}
	}
}
