package control;
import entity.Engine;

public class Jeu2048 implements IControl {

    private Engine engine;
    private static final int GRID_WIDTH = 4;
    private static final int GRID_HEIGHT = 4;


    @Override
    public void init(){
        this.engine = new Engine(GRID_WIDTH, GRID_HEIGHT);
        engine.addNewCell();
        engine.addNewCell();
    }

    
    @Override
	public int[][] getGrid(){
        int[][] grid = new int[GRID_WIDTH][GRID_HEIGHT];

        for (int i = 0; i < GRID_WIDTH; i++) {
			for (int j = 0; j < GRID_HEIGHT; j++) {
				grid[i][j] = engine.getCell(i, j);
			}
		}
        return grid;
    }


    @Override
	public void right(){
        moveRight();
        fuseRight();
        moveRight();
        engine.addNewCell();
    }


    @Override
	public void up(){
        rotate();
        right();
        rotate();
        rotate();
        rotate();
    }


    @Override
	public void down(){
        rotate();
        rotate();
        rotate();
        right();
        rotate();
    }


    @Override
	public void left(){
        rotate();
        rotate();
        right();
        rotate();
        rotate();
    }


    private void rotate(){
        int[][] grid = getGrid();

        for (int i = 0; i < GRID_HEIGHT; i++) {
			for (int j = 0; j < GRID_WIDTH; j++) {
                engine.setCell(i, j, grid[GRID_HEIGHT - j - 1][i]);
			}
		}

    }

    
    private void moveRight() {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            int target = GRID_WIDTH - 1;
            
            for (int j = GRID_WIDTH - 1; j >= 0; j--) { 
                if (engine.getCell(i, j) != 0) {
                    if (j != target) { 
                        engine.setCell(i, target, engine.getCell(i, j));
                        engine.setCell(i, j, 0); 
                    }
                    target--;
                }
            }
        }
    }
    

    private void fuseRight(){
        for (int i = 0; i < GRID_HEIGHT; i++) {
            
            for (int j = GRID_WIDTH - 2; j >= 0; j--) { 
                if (engine.getCell(i, j) == engine.getCell(i, j+1)) { 
                    engine.setCell(i, j+1, engine.getCell(i, j) * 2);
                    engine.setCell(i, j, 0); 
                }
            }
        }
    } 


    @Override
	public boolean isOver(){
            if (!engine.getEmptyCells().isEmpty()) return false;
        
            for (int i = 0; i < GRID_HEIGHT; i++) {
                for (int j = 0; j < GRID_WIDTH; j++) {
                    if (j < GRID_WIDTH - 1 && engine.getCell(i ,j) == engine.getCell(i, j + 1)) 
                        return false;
                    
                    if (i < GRID_HEIGHT - 1 && engine.getCell(i, j) == engine.getCell(i + 1, j)) 
                        return false;
                }
            }

            return true;
    }

    
    @Override
	public int score(){
        int max = 0;

        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (getGrid()[i][j] > max) 
                    max = getGrid()[i][j];
            }
        }
         return max;
    }




    public Jeu2048(){
        init();
    }
    
}


