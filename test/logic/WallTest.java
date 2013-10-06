package logic;

import logic.blocks.Wall;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class WallTest {
    
    public Wall wall;
    
    @Before
    public void setUp(){
        
        this.wall = new Wall(1,2);
    }

    @Test
    public void testWallOk() {
        
        assertEquals(1, this.wall.getX());
        assertEquals(2, this.wall.getY());
        assertEquals(Enums.BLOCK_TYPE.WALL, this.wall.getType());
    }
}