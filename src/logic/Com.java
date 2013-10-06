package logic;

import java.util.Random;

public class Com {
    
    public static int generateOddInt(int limit){
        
        Random rand = new Random();        
        return rand.nextInt(((limit - 3)/2)+1)*2+1;
    }
    
}
