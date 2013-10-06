package logic.blocks;

import logic.Enums;

public class Air extends Point{
    
    public Air(int x, int y){
        
        super(x, y);   
        super.setType(Enums.BLOCK_TYPE.AIR);
    }
}