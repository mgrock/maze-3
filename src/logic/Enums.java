package logic;

public class Enums {
    
    public static enum BLOCK_TYPE {
        WALL, FLOOR, HERO, DRAGON, SWORD, EXIT, EAGLE, AIR
    }
    
    public static enum DRAGON_STATE {
        ASLEEP, AWAKE 
    }
    
    public static enum HERO_ARMED_STATE {
        UNARMED, ARMED
    }
    
    public static enum HERO_PET_STATE {
        WITHOUT_PET, WITH_PET
    }
        
    public static enum PET_CARRY_STATE {
        CARRIES_SWORD, CARRIES_NOTHING
    }
    
    public static enum CHARACTER_STATE {
        ALIVE, DEAD
    }
    
}
