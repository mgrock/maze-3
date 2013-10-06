package logic.mazeBuilder;

import logic.Maze;

public abstract class BaseMazeBuilder implements MazeBuilder {
    
    protected Maze maze;
    
    @Override
    public Maze getMaze(){
        
        return maze;
    }
    
    @Override
    public void init(int size){
        
        this.maze = new Maze(size);
        
        this.maze.fillWithWalls();
        this.maze.fillWithAir();        
    }
    
}

