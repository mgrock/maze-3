package logic.blocks;

import logic.Enums;

public class Floor extends Point{
    
    public Floor(int x, int y){
        
        super(x, y);   
        super.setType(Enums.BLOCK_TYPE.FLOOR);
    }
}