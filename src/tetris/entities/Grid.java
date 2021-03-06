package tetris.entities;

public class Grid {

	private static Grid INSTANCE;  
	
	private int width;
	
	private int height;
	
	private BaseElement [][] grid;
	
	private Tetramino currentTetramino;
	
	private Grid(int w, int h) {                             //definizione griglia
		width = w;
		height = h;
		grid = new BaseElement[h][w];
	}
	
	public static Grid getInstance(int w, int h) {           //creazione unica grid
		if (INSTANCE == null) {
			INSTANCE = new Grid(w, h);
		}
		return INSTANCE;
	}
	
	private BaseElement getElement (int x, int y) {         //restituisce il BaseElement in coord. x, y
		return grid[y][x];
	}
	
	public void insertElement (BaseElement e, int x, int y) {      //inserisce BaseElement
		grid[y][x] = e;
	}
	
	
	public void moveBaseElement (int x, int y) {         //muove BaseElement
		BaseElement toMove = getElement(x, y);
		toMove.setY(toMove.getY()+32);
	}
	
	public BaseElement[] checkRow (int i) {            //controlla riga se piena
		BaseElement[] full = grid[i];
		for(int j = 0; j < width; j++) {
			if(grid[i][j] == null) {
				full = null;
				break;
			}
		}
		return full;
	}
	
	public void emptyRow (int i) {                  //svuota riga
		for(int j = 0; j < width; j++) {
			grid[i][j] = null;
		}
		shiftRow(i);
	}
	
	public void clear() {                      
		grid = null;
		grid = new BaseElement[height][width];
	}
	
	public void shiftRow (int i) {              //sposta intera colonna in giù 
		for(int j = i; j>0; j--) {
			grid[j] = grid[j-1];

			
			for(int k = 0; k<width; k++) {
				if(grid[j][k]!=null) {
					grid[j][k].setY(grid[j][k].getY()+32);
				}	
			}
		}
	}

	
	public boolean isFilled (int x, int y) {                    //verifica che x, y in grid sia pieno
		if(x >=0 && y >= 0 && y<height && x < width) {
			return grid[y][x] != null;
		} else {
			return false;
		}
	}
	
	public void print() {
		String str;
		for(int y = 0; y < height; y++) {
			str = "" + y + ": ";
			if(y<=9) {
				str += " ";
			}
			for(int x = 0; x < width; x++) {
				str += (isFilled(x, y))? "X": ".";
				str += " ";
			}
			System.out.println(str);
		}
		System.out.println("\n");
	}
}
