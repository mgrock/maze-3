package logic.mazeBuilder;

public class RandomMazeBuilder extends BaseMazeBuilder implements MazeBuilder{
            
    @Override
    public void buildWalls(){               
        this.maze.setRandomWalls();    
        this.maze.mapGroundBlocks();
    }
    
    @Override
    public void buildHero(){
        
        this.maze.setHero();
    }
    
    @Override
    public void buildDragons(int dragonCount){
        
        for (int i = 0; i < dragonCount; i++) {
            
            this.maze.setDragon();       
        }         
    }
    
    @Override
    public void buildSword(){
        
        this.maze.setSword();
    }
    
    @Override
    public void buildExit(){
        
        this.maze.setExit();
    }
    
    @Override
    public void buildEagle(){
        
        this.maze.setEagle();
    }

}
