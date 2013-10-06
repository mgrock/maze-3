package logic.blocks;

import logic.Enums;

public class Wall extends Point{
    
    public Wall(int x, int y){
        
        super(x, y);   
        super.setType(Enums.BLOCK_TYPE.WALL);
    }
}