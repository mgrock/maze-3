package logic.mazeBuilder;

public class DefaultMazeBuilder extends BaseMazeBuilder implements MazeBuilder{
    
    @Override
    public void buildWalls(){
        
        this.maze.setDefaultWalls(); 
        this.maze.fillWithAir();
        this.maze.mapGroundBlocks();
    }
    
    @Override
    public void buildHero(){
        
        this.maze.setHero(0);
    }
    
    @Override
    public void buildDragons(int dragonCount){
        
        this.maze.setDragon(11);        
    }
    
    @Override
    public void buildSword(){
        
        this.maze.setSword(34);
    }
    
    @Override
    public void buildExit(){
        
        this.maze.setExit(17);
    }
    
    @Override
    public void buildEagle(){
        
        this.maze.setEagle();
    }
}
