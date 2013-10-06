
package logic.blocks;

import logic.Enums;

public class Exit extends Point implements Block {

    public Exit(int x, int y) {
        
        super(x, y);
        super.setType(Enums.BLOCK_TYPE.EXIT);
    }
    
}
