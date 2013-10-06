package logic.blocks;

import logic.Enums;

public interface Block {
    
    public Enums.BLOCK_TYPE getType();
    
    public int getX();
    
    public int getY();
}
