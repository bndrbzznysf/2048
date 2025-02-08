package entity;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

public class Engine {
	private Dimension dimension;
	private Cell grid[][];
	
	public Engine(int l, int c) {
		this.dimension = new Dimension(l,c);
		this.grid = new Cell[l][c];
		
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < c; j++) {
				this.grid[i][j] = new Cell(0);
			}
		}
	}

	public Engine(int tab[][]){
		int l = tab.length;
		int c = tab[0].length;

		this.dimension = new Dimension(l, c);		
		this.grid = new Cell[l][c];
		
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < c; j++) {
				this.grid[i][j] = new Cell(tab[i][j]);
			}
		}
	}
	

	public int getWidth() {
		return (int) this.dimension.getWidth();
	}
	
	public int getHeight() {
		return (int) this.dimension.getHeight();
	}
	
	public int getCell(int i, int j) {
		return this.grid[i][j].getContent();
	}
	
	public void setCell(int i, int j, int v) {
		this.grid[i][j].setContent(v);
	}

	public ArrayList<Cell> getEmptyCells(){
		ArrayList<Cell> emptyCells = new ArrayList<>();
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (getCell(i, j) == 0){
					emptyCells.add(grid[i][j]);
				}
			}
		}
		return emptyCells;
	}


	public void addNewCell(){
		if (!getEmptyCells().isEmpty()) {
			Random r = new Random();
			double random = r.nextDouble();
			int value = (random < 0.8 ? 2 : 4);

			Cell newCell = getEmptyCells().get(r.nextInt(getEmptyCells().size()));
			newCell.setContent(value);
		}
	}

}
