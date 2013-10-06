package logic;

import logic.blocks.Point;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PointTest {

    public Point point;

    @Before
    public void setUp() {

        this.point = new Point(1, 1);
    }
    
    @Test
    public void testGetX() {

        int expResult = 1;
        int result = this.point.getX();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetY() {
        
        int expResult = 1;
        int result = this.point.getY();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetType() {
                
        Enums.BLOCK_TYPE expResult = null;
        Enums.BLOCK_TYPE result = this.point.getType();
        assertEquals(expResult, result);
        
        this.point.setType(Enums.BLOCK_TYPE.WALL);
        
        expResult = Enums.BLOCK_TYPE.WALL;        
        result = this.point.getType();
        assertEquals(expResult, result);        
    }
    
}