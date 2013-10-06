
package logic.blocks.characters;

import logic.Enums;

public class Dragon extends BaseCharacter implements Character_ {
    
    protected Enums.DRAGON_STATE asleep;
    
    public boolean isAsleep(){
        
        return this.asleep == Enums.DRAGON_STATE.ASLEEP;                     
    }
    
    public void sleep(){
        
        this.asleep = Enums.DRAGON_STATE.ASLEEP;
    }
    
    public void wake(){
        
        this.asleep = Enums.DRAGON_STATE.AWAKE;
    }
    
    public Enums.DRAGON_STATE getSleepState(){
      
        return this.asleep;
    }
    
    private void setSleepState(Enums.DRAGON_STATE state){
        
        this.asleep = state;
    } 
    
    public Dragon(int x, int y) {
        
        super(x, y, Enums.BLOCK_TYPE.DRAGON);
        this.setSleepState(Enums.DRAGON_STATE.AWAKE);
    }
    
}
