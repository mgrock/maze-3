package logic.blocks.characters;

import logic.Enums;

public class Hero extends BaseCharacter implements Character_ {
    
    protected Enums.HERO_ARMED_STATE armedState;
    
    protected Enums.HERO_PET_STATE petState;
    
    protected Enums.CHARACTER_STATE aliveState;
    
    public boolean isArmed(){
        
        return this.armedState == Enums.HERO_ARMED_STATE.ARMED;                     
    }
    
    public boolean isAlive(){
        
        return this.aliveState == Enums.CHARACTER_STATE.ALIVE;
    }
    
    public boolean hasPet(){
        
        return this.petState == Enums.HERO_PET_STATE.WITH_PET;                     
    }
    
    public Enums.HERO_ARMED_STATE getArmedState(){
        
        return this.armedState;
    }
    
    public void arm(){
        
        this.armedState = Enums.HERO_ARMED_STATE.ARMED;
    }
    
    private void setArmedState(Enums.HERO_ARMED_STATE state) {
        
        this.armedState = state;
    }
    
    private void setAliveState(Enums.CHARACTER_STATE state) {
        
        this.aliveState = state;
    }
    
    public void die(){
        
        this.aliveState = Enums.CHARACTER_STATE.DEAD;
    }
    
    public Hero(int x, int y){
                
        super(x, y, Enums.BLOCK_TYPE.HERO);        
        this.setAliveState(Enums.CHARACTER_STATE.ALIVE);
        this.setArmedState(Enums.HERO_ARMED_STATE.UNARMED);
    }
}
