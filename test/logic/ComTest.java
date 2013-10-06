
package logic;

import org.junit.Test;
import static org.junit.Assert.*;

public class ComTest {
    

    @Test
    public void testGenerateRandonOddInt(){
        
        for (int i = 0; i < 100; i++) {
            
            int odd = Com.generateOddInt(11);
            
            assertNotSame(2, odd);
            assertNotSame(4, odd);        
            assertNotSame(6, odd);
            assertNotSame(8, odd);
            assertNotSame(10, odd);
        }        
    }
}