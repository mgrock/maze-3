
package logic.blocks;

import logic.Enums;

public class Sword extends Point implements Block {

    public Sword(int x, int y) {
        
        super(x, y);
        super.setType(Enums.BLOCK_TYPE.SWORD);
    }
    
}
