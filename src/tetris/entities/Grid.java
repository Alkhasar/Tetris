package tetris.entities;

import tetris.main.Tetris;
import tetris.resources.ImageResource;
import tetris.tetrisScene.Game;

public class Grid {

	private static Grid INSTANCE;
	
	private int width;
	
	private int height;
	
	private BaseElement [][] grid;
	
	private Tetramino currentTetramino;
	
	private Grid(int w, int h) {
		width = w;
		height = h;
		grid = new BaseElement[h][w];
	}
	
	public static Grid getInstance(int w, int h) {
		if (INSTANCE == null) {
			INSTANCE = new Grid(w, h);
		}
		return INSTANCE;
	}
	
	private BaseElement getElement (int x, int y) {
		return grid[y][x];
	}
	
	public void insertElement (BaseElement e, int x, int y) {
		grid[y][x] = e;
	}
	
	//false se collide
	public void moveBaseElement (int x, int y) {
		BaseElement toMove = getElement(x, y);
		toMove.setY(toMove.getY()+32);
	}
	
	public void setTetramino (Tetramino t) {
		currentTetramino = t;
		//aggiunge BaseElement a grid
	}
	
	public BaseElement[] checkRow (int i) {
		BaseElement[] full = grid[i];
		for(int j = 0; j < width; j++) {
			if(grid[i][j] == null) {
				full = null;
				break;
			}
		}
		return full;
	}
	
	public void emptyRow (int i) {
		for(int j = 0; j < width; j++) {
			grid[i][j] = null;
		}
		shiftRow(i);
	}
	
	public void clear() {
		grid = null;
		grid = new BaseElement[height][width];
	}
	
	public void shiftRow (int i) {
		for(int j = i; j>0; j--) {
			grid[j] = grid[j-1];
			for(int k = 0; k<width; k++) {
				if(grid[j][k]!=null) {
					grid[j][k].setY(grid[j][k].getY()+32);
				}	
			}
		}
	}

	
	public boolean isFilled (int x, int y) {
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
			for(int x = 0; x < width; x++) {
				str += (isFilled(x, y))?0:1;
			}
			System.out.println(str);
		}
		System.out.println("\n");
	}
}