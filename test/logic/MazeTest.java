
package logic;

import logic.blocks.Block;
import logic.blocks.Floor;
import logic.blocks.Wall;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


public class MazeTest {
    
    protected Maze maze;
    
    @Before
    public void setUp(){
        
        maze = new Maze(11);
    }
    
    @Test
    public void testBuild() {
        
        maze.fillWithWalls();
        Block[][] array = maze.getGround();
        
        int xLength = array.length;
        assertEquals(11, xLength);
        
        for (int x = 0; x < xLength; x++) {
            
            int yLength = array[x].length;
            assertEquals(11, yLength);
            
            for (int y = 0; y < yLength; y++) {
                
                Block block = array[x][y];
                assertEquals(Enums.BLOCK_TYPE.WALL, block.getType());
            }         
        }
    }

    @Test
    public void testGetWidth() {
        
        assertEquals(11, maze.getWidth());
    }

    @Test
    public void testGetHeight() {
        
        assertEquals(11, maze.getHeight());
    }

    @Test
    public void testGetMaze() {
        
        Block[][] array = maze.getGrid();
        Block[][] dummy = new Block[11][11];
        
        assertEquals(dummy, array);
    }
    
    @Test
    public void testCheckBlockType(){
        
        assertEquals(true, maze.checkBlockType(new Wall(1,1), Enums.BLOCK_TYPE.WALL));
        assertEquals(true, maze.checkBlockType(new Floor(1,1), Enums.BLOCK_TYPE.FLOOR));
    }
    
    @Test
    public void testAddAdjacentWall(){
        
        maze.fillWithWalls();
        Block refBlock = maze.getGroundBlock(2, 2);
        
        ArrayList<Block> wallList = new ArrayList();
        
        maze.addAdjacentWalls(refBlock, wallList);
        assertEquals(maze.getGroundBlock(3, 2), wallList.get(0));
        assertEquals(maze.getGroundBlock(1, 2), wallList.get(1));
        assertEquals(maze.getGroundBlock(2, 1), wallList.get(2));
        assertEquals(maze.getGroundBlock(2, 3), wallList.get(3));
    }
}