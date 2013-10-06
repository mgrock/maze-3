package logic.mazeBuilder;

import logic.Maze;

public interface MazeBuilder {
    
    public Maze getMaze();
    
    public void init(int size);  
    
    public void buildWalls();      
    
    public void buildHero();

    public void buildDragons(int dragonCount);

    public void buildSword();

    public void buildExit();

    public void buildEagle();
}
