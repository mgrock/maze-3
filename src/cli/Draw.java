package cli;

import java.util.ArrayList;
import logic.blocks.Block;
import logic.*;
import static logic.Enums.DRAGON_STATE.ASLEEP;
import static logic.Enums.DRAGON_STATE.AWAKE;
import logic.blocks.characters.Dragon;
import logic.blocks.characters.Hero;

public class Draw {
    
    private static char[][] result;
    
    private static char resolveBlockType(Enums.BLOCK_TYPE g_type,
            Enums.BLOCK_TYPE a_type, Enums.BLOCK_TYPE i_type) {

        char block;

        switch (g_type) {

            default:
            case WALL:      
                
                block = 'X';                                                
                if(a_type == Enums.BLOCK_TYPE.EAGLE)
                    block = 'O';  
                                                
                break;

            case FLOOR:
                
                block = ' ';
                                
                if(i_type == Enums.BLOCK_TYPE.SWORD)
                    block = 'E';                                 
                
                if(a_type == Enums.BLOCK_TYPE.EAGLE)
                    block = 'O';
                
                if(a_type == Enums.BLOCK_TYPE.EAGLE
                && i_type == Enums.BLOCK_TYPE.SWORD)
                    block = 'Q';
                
                if(i_type == Enums.BLOCK_TYPE.EXIT)
                    block = 'S';
                
                break;
            
            // NOTE: Also resolved on the Dragon State Pass
            case DRAGON:                
                block = 'D';                                                
                break;

            case HERO:                
                block = 'H';                
                break;

        }

        return block;
    }
    
    private static char resolveDragonStates(Dragon dragon, Block item){
        
        char block;
        
        switch(dragon.getSleepState()){
            
            case ASLEEP:
                block = 'd';
                if(item.getType() == Enums.BLOCK_TYPE.SWORD)
                    block = 'f';
                break;
            
            default:
            case AWAKE:
                block = 'D';
                if(item.getType() == Enums.BLOCK_TYPE.SWORD)
                    block = 'F';
                break;
        }
        
        return block;
    }
    
    private static char resolveHeroStates(Hero hero){
        
        char block;
        
        switch(hero.getArmedState()){
            
            case ARMED:
                block = 'A';                
                break;
            
            default:
            case UNARMED:
                block = 'H';                
                break;
        }
        
        return block;
        
    }

    public static void maze(Maze maze) {

        Block[][] mazeMap = maze.getGrid();

        int w = maze.getWidth();
        int h = maze.getHeight();
        
        Block[][] airLayer    = maze.getAir();
        Block[][] itemLayer   = maze.getItem();
        Block[][] groundLayer = maze.getGround();
                
        result = new char[w][h];
        
        // 1st pass : Resolve Block Types
        
        for (int x = 0; x < w; x++) {

            for (int y = 0; y < h; y++) {
                
                Enums.BLOCK_TYPE a_type = airLayer[x][y].getType();
                Enums.BLOCK_TYPE g_type = groundLayer[x][y].getType();                
                Enums.BLOCK_TYPE i_type = itemLayer[x][y].getType();                
                
                result[x][y] = resolveBlockType(g_type, a_type, i_type);
            }            
        }
        
        // 2nd pass : Resolve Dragon States
        
        ArrayList<Dragon> dragons = maze.getDragons();
        
        for (int i = 0; i < dragons.size(); i++) {
            
            int x = dragons.get(i).getX();
            int y = dragons.get(i).getY();
            Block _item = itemLayer[x][y];
            
            result[x][y] = resolveDragonStates(dragons.get(i), _item);
                        
        }
        
        // 3rd pass : Resolve Hero States
        
        Hero hero = maze.getHero();
                            
        int x = hero.getX();
        int y = hero.getY();        

        result[x][y] = resolveHeroStates(hero);
                       
        // Last pass : Print to console
        
        for (x = 0; x < w; x++) {

            for (y = 0; y < h; y++) {
                
                System.out.print(result[x][y]);
            }
            System.out.println();
        }
        
    }    
}