
package logic.blocks.characters;

import logic.Enums;
import logic.blocks.Block;
import logic.blocks.Point;

public abstract class BaseCharacter extends Point implements Block {
    
    private Enums.CHARACTER_STATE state;
    
    public void translate(int dx, int dy){
        
        this.setX(this.getX() + dx);
        this.setY(this.getY() + dy);
    }
    
    protected void setState(Enums.CHARACTER_STATE state){
        
        this.state = state;
    }
    
    public BaseCharacter(int x, int y, Enums.BLOCK_TYPE type) {
        
        super(x, y);
        super.setType(type);
        this.setState(Enums.CHARACTER_STATE.ALIVE);
    }
}
