package logic;

import logic.blocks.Sword;
import logic.blocks.Exit;
import logic.blocks.characters.Hero;
import logic.blocks.Block;
import logic.blocks.Floor;
import logic.blocks.characters.Dragon;
import logic.blocks.Wall;
import java.util.ArrayList;
import java.util.Random;
import logic.blocks.Air;
import logic.blocks.characters.Character_;
import logic.blocks.characters.Eagle;

public class Maze {
    
    protected int WIDTH;
    protected int HEIGHT;
    
    // TODO Refactor into Layer Class
    
    protected Block[][] ground;
    protected Block[][] air;
    protected Block[][] item;
    
    private ArrayList<Block> tmpWallList;
    private ArrayList<Block> refBlockList;
    
    private ArrayList<Block> floorList;
    private ArrayList<Block> wallList;
    private ArrayList<Block> possibleExitWalls;
    
    protected Hero  hero;       
    protected Eagle eagle;   
    protected Sword sword;   
    protected Exit  exit;   
    
    protected boolean reachedExit;
    
    protected ArrayList<Dragon> dragonList;   
    
    public Maze(int n){
        
        if(n > 0) {
            n = n%2 == 0 ? n+1 : n;
            n = n > 4 ? n : 5;
        }
                            
        this.init(n);
        
        this.tmpWallList  = new ArrayList();
        this.refBlockList = new ArrayList();
        
        this.floorList    = new ArrayList();
        this.wallList     = new ArrayList();
        this.dragonList   = new ArrayList();
        
        this.possibleExitWalls = new ArrayList();
    }
    
    private void init(int n){
        
        this.WIDTH = n;
        this.HEIGHT = n;       
        
        this.ground = new Block[n][n];
        this.air    = new Block[n][n];
        this.item   = new Block[n][n];
    } 
    
    public int getWidth(){
        
        return this.WIDTH;
    }
    
    public int getHeight(){
        
        return this.HEIGHT;
    }
    
    private void setReachedExit(boolean reached){
        
        this.reachedExit = reached;
    }
    
    public boolean hearHasReachedExit(){
        
        return this.reachedExit;
    }
    
    public Hero getHero(){
        
        return this.hero;
    }
    
    public ArrayList<Dragon> getDragons(){
        
        return this.dragonList;
    }
        
    
    public Block[][] getGrid(){
        
        Block[][] block = new Block[this.WIDTH][this.HEIGHT];
        return block;
    }
    
    public Block[][] getGround(){
        
        return this.ground;
    }
    
    public Block[][] getAir(){
        
        return this.air;
    }
    
     public Block[][] getItem(){
        
        return this.item;
    }
    
    
    public Block getGroundBlock(int x, int y){
        
        return this.ground[x][y];
    }
    
    public void fillWithWalls(){
        
        for (int x = 0; x < this.WIDTH; x++) {
                                    
            for (int y = 0; y < this.HEIGHT; y++) {
                
                this.ground[x][y] = new Wall(x,y);
                
            }
        }
    }
    
    public void fillWithAir(){
        
        for (int x = 0; x < this.air.length; x++) {
            
            for (int y = 0; y < this.air[x].length; y++) {
                                
                this.air[x][y]  = new Air(x,y);
                this.item[x][y] = new Air(x,y);
            }
        }
    }
        
    public boolean isInnerPosition(int x, int y){
                        
        if(x <= 0 || x >= this.WIDTH)
            return false;
        
        if(y <= 0 || y >= this.HEIGHT)
            return false;
        
        return true;
    }
    
    public boolean isValidPosition(int x, int y){
                        
        if(x < 0 || x > this.WIDTH - 1)
            return false;
        
        if(y < 0 || y > this.HEIGHT - 1)
            return false;
        
        return true;
    }        
    
    public Block getBlockAbove(Block refBlock){
                
        return this.getGroundBlock(refBlock.getX(), refBlock.getY() - 1);
    }
    
    public Block getBlockBelow(Block refBlock){
        
        return this.getGroundBlock(refBlock.getX(), refBlock.getY() + 1);
    }
    
    public Block getBlockToTheLeft(Block refBlock){
        
        return this.getGroundBlock(refBlock.getX() - 1, refBlock.getY());
    }
    
    public Block getBlockToTheRight(Block refBlock){
        
        return this.getGroundBlock(refBlock.getX() + 1, refBlock.getY());
    }
    
    public boolean checkBlockType(Block block, Enums.BLOCK_TYPE type){
        
        return block.getType() == type;
    }
    
    public ArrayList<Block> getAdjacentBlocks(Block refBlock){
        
        int x = refBlock.getX();
        int y = refBlock.getY();
        
        ArrayList<Block> blocks = new ArrayList();
                        
        if(this.isInnerPosition(x + 1, y)){
            
            blocks.add(this.getBlockToTheRight(refBlock));
        }
                    
        if(this.isInnerPosition(x - 1, y)){
            
            blocks.add(this.getBlockToTheLeft(refBlock));    
        }            
        
        if(this.isInnerPosition(x, y - 1)){
            
            blocks.add(this.getBlockAbove(refBlock));       
        }            
        
        if(this.isInnerPosition(x, y + 1)){
            
            blocks.add(this.getBlockBelow(refBlock));
        }
        
        return blocks;
    }
    
    public void addAdjacentWalls(Block refBlock, ArrayList list){
        
        int x = refBlock.getX();
        int y = refBlock.getY();
        
        Enums.BLOCK_TYPE wall = Enums.BLOCK_TYPE.WALL;
        
        if(this.isInnerPosition(x + 1, y)
                && this.checkBlockType(refBlock, wall)){
            
            list.add(this.getBlockToTheRight(refBlock));
            this.refBlockList.add(refBlock);
        }
                    
        if(this.isInnerPosition(x - 1, y)
                && this.checkBlockType(refBlock, wall)){
            
            list.add(this.getBlockToTheLeft(refBlock));
            this.refBlockList.add(refBlock);
        }            
        
        if(this.isInnerPosition(x, y - 1)
                && this.checkBlockType(refBlock, wall)){
            
            list.add(this.getBlockAbove(refBlock));
            this.refBlockList.add(refBlock);
        }            
        
        if(this.isInnerPosition(x, y + 1)
                && this.checkBlockType(refBlock, wall)){
            
            list.add(this.getBlockBelow(refBlock));
            this.refBlockList.add(refBlock);
        }

//        This should work...
        
//        ArrayList<Block> blocks = getAdjacentBlocks(refBlock);
//        
//        Enums.BLOCK_TYPE wall = Enums.BLOCK_TYPE.WALL;
//        
//        for(int i = 0; i < blocks.size(); i++) {
//            
//            if(this.checkBlockType(blocks.get(i), wall)){
//                
//                list.add(blocks.get(i));
//                this.refBlockList.add(refBlock);
//            }
//        } 
            
    }       
    
    public Block calcOppositeBlock(Block curBlock, Block refBlock){
        
        int x, y;        
        Block oppositeBlock;
        
        if(curBlock.getX() == refBlock.getX()){            
            x = curBlock.getX();
        } else {                        
            x = curBlock.getX() + curBlock.getX() - refBlock.getX();
        }
        
        if(curBlock.getY() == refBlock.getY()){          
            y = curBlock.getY();
        } else {            
            y = curBlock.getY() + curBlock.getY() - refBlock.getY();
        }
        
        return new Wall(x, y);
    }
    
    public void mapGroundBlocks(){
        
        Enums.BLOCK_TYPE FLOOR = Enums.BLOCK_TYPE.FLOOR;
        
        for (int x = 0; x < this.ground.length; x++) {
            
            for (int y = 0; y < this.ground[x].length; y++) {
                
                if(this.checkBlockType(this.ground[x][y], Enums.BLOCK_TYPE.FLOOR)) {
                    
                    this.floorList.add(this.ground[x][y]);
                    
                } else if(this.checkBlockType(this.ground[x][y], Enums.BLOCK_TYPE.WALL)) {
                    
                    this.wallList.add(this.ground[x][y]);
                    
                    // Check that the wall is not on the edge
                    if (x == 0 || x == this.WIDTH  - 1
                    ||  y == 0 || y == this.HEIGHT - 1){
                               
                        if(x == 0){
                            
                            if(this.checkBlockType(this.ground[x + 1][y], FLOOR)){
                                this.possibleExitWalls.add(this.ground[x][y]);                                
                            }                                                                
                        
                        } else if(x == this.WIDTH - 1){
                            
                            if(this.checkBlockType(this.ground[x - 1][y], FLOOR)){
                                this.possibleExitWalls.add(this.ground[x][y]);                                                                        
                            }                                    
                        
                        } else if (y == 0){
                            
                            if(this.checkBlockType(this.ground[x][y + 1], FLOOR)){
                                this.possibleExitWalls.add(this.ground[x][y]);                                                                        
                            }
                        
                        } else if (y == this.HEIGHT - 1){
                            
                            if(this.checkBlockType(this.ground[x][y - 1], FLOOR)){
                                this.possibleExitWalls.add(this.ground[x][y]);                                
                            }
                        }                                                                                                                     
                    }                    
                }
            }
        }
    }
    
    /**
     * MAZE CREATION ALGORYTHM
     * 
     * Start with a grid full of walls.    
     * 
     * Pick a cell, mark it as part of the maze.     
     * 
     * Add the walls of the cell to the wall list. 
     * 
     * While there are walls in the list: 
     * 
     * Pick a random wall from the list. 
     * 
     * If the cell on the opposite side isn't in the maze yet:
     * 
     * Make the wall a passage and mark the cell on the opposite side as part of the maze.
     * 
     * Add the neighboring walls of the cell to the wall list.
     * 
     * If the cell on the opposite side already was in the maze,
     * remove the wall from the list.
     */
    
    // METHODS FOR BUILDER TO USE
    
    public void setRandomWalls(){
        
        Random rand = new Random();
        
        int startX = Com.generateOddInt(this.WIDTH);
        int startY = Com.generateOddInt(this.HEIGHT);
        
        Block referenceBlock = this.getGroundBlock(startX, startY);
        Block selectedBlock;
        Block oppositePoint;
        Block oppositeBlock;
        
        this.addAdjacentWalls(referenceBlock, this.tmpWallList);                
        
        while (this.tmpWallList.size() > 0) {
            
            // Pick a random index from the blockList
            // Select the block on the random position
            int blockIndex = rand.nextInt(this.tmpWallList.size());                       
            selectedBlock  = this.tmpWallList.get(blockIndex);
            referenceBlock = this.refBlockList.get(blockIndex);      
            
            oppositePoint  = this.calcOppositeBlock(selectedBlock, referenceBlock);
            
            if(this.isInnerPosition(oppositePoint.getX(), oppositePoint.getY())){
                
                oppositeBlock =
                        this.getGroundBlock(oppositePoint.getX(), oppositePoint.getY());
                
                if(this.checkBlockType(oppositeBlock, Enums.BLOCK_TYPE.WALL)){
                    
                    int x = oppositeBlock.getX();
                    int y = oppositeBlock.getY();                    
                    this.ground[x][y] = new Floor(x,y);
                    
                    x = selectedBlock.getX();
                    y = selectedBlock.getY();
                    this.ground[x][y] = new Floor(x,y);
                }                    
                
                this.addAdjacentWalls(oppositeBlock, this.tmpWallList);                               
            }
            
            this.tmpWallList.remove(blockIndex);
            this.refBlockList.remove(blockIndex);            
        }
        
    }
    
    public void setDefaultWalls(){
        
        this.init(10);
        
        char[][] defaultMaze = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 1, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
        
        for (int x = 0; x < defaultMaze.length; x++) {
            
            for (int y = 0; y < defaultMaze[x].length; y++) {
                
                int type = defaultMaze[x][y];
                
                switch(type){
                    
                    case 0:
                        this.ground[x][y] = new Floor(x,y);
                        break;
                                                           
                    case 1:
                        this.ground[x][y] = new Wall(x,y);
                        break;
                }                                
            }            
        }
    }        
    
    public int calcRandomFloorIndex(){
        
        Random rand = new Random();
        int index = rand.nextInt(this.floorList.size());
        return index;
    }
    
    public int calcPossibleExitWallIndex(){
        
        Random rand = new Random();        
        int index = rand.nextInt(this.possibleExitWalls.size());
        return index;
    }
    
    public void setHero(){
                
        this.setHero(this.calcRandomFloorIndex());        
    }
    
    // Hero must be set before the Dragon
    // TODO: Should be reflexive programming!
    
    public void setHero(int i){
        
        Block floor = this.floorList.get(i);
        
        int x = floor.getX();
        int y = floor.getY();
        
        this.hero = new Hero(x,y);
        this.ground[x][y] = this.hero;
        this.floorList.remove(i);
        
        ArrayList<Block> blocks = getAdjacentBlocks(this.hero);
        
        for (int j = 0; j < blocks.size(); j++) {
            
            Block curBlock = blocks.get(j);
            int _x1 = curBlock.getX();
            int _y1 = curBlock.getY();
            
            if(curBlock.getType() == Enums.BLOCK_TYPE.FLOOR) {
                
                // This is bad design...                
                for (int k = 0; k < this.floorList.size(); k++) {
                    
                    Block listBlock = this.floorList.get(k);
                    
                    int _x2 = listBlock.getX();
                    int _y2 = listBlock.getY();
                    
                    if(_x1 == _x2 && _y1 == _y2) {
                        this.floorList.remove(k);
                    }
                    
                    
                }                
            }            
        }
    }
    
    public void setSword(){   
        
        this.setSword(this.calcRandomFloorIndex());          
    }
            
    public void setSword(int i){
        
        Block floor = this.floorList.get(i);
        
        int x = floor.getX();
        int y = floor.getY();
        
        this.sword = new Sword(x,y);
        this.item[x][y] = this.sword;
        this.floorList.remove(i); 
    }
    
    public void setDragon(){
        
        this.setDragon(this.calcRandomFloorIndex());   
    }
    
    public void setDragon(int i){
        
        Block floor = this.floorList.get(i);
        
        int x = floor.getX();
        int y = floor.getY();
        
        Dragon dragon = new Dragon(x,y);
        
        this.dragonList.add(dragon);
        this.ground[x][y] = dragon;
        this.floorList.remove(i);
    }
    
    public void setEagle(){      
        
        int x = this.hero.getX();
        int y = this.hero.getY();
        
        this.eagle = new Eagle(x,y);
        this.air[x][y] = this.eagle;
    }        
    
    public void setExit(){
        
        this.setExit(this.calcPossibleExitWallIndex());
    }
    
    public void setExit(int i){
                
        Block wall = this.possibleExitWalls.get(i);
        
        int x = wall.getX();
        int y = wall.getY();
                                        
        this.exit = new Exit(x,y);
        this.item[x][y]   = this.exit;
        this.ground[x][y] = new Floor(x,y);
        this.possibleExitWalls.remove(i);
    }
           
    public void moveCharacter(int dx, int dy, Character_ character){
        
        int x = character.getX();
        int y = character.getY();
        
        if(this.isValidPosition(x+dx, y+dy)){
            
            Block nextBlock = this.ground[x+dx][y+dy];
            Enums.BLOCK_TYPE type = nextBlock.getType();
            
            if(type != Enums.BLOCK_TYPE.WALL
//            && type != Enums.BLOCK_TYPE.DRAGON
            && type != Enums.BLOCK_TYPE.HERO ){
                
                character.translate(dx, dy);
                this.ground[x][y] = new Floor(x, y);
                this.ground[x+dx][y+dy] = character;
            }
        }
    }    
    
    public void updateHeroStatus(){
        
        int x = this.hero.getX();
        int y = this.hero.getY();
        
        // Arm Hero    
        if(this.item[x][y].getType() == Enums.BLOCK_TYPE.SWORD){
            
            this.hero.arm();
            this.item[x][y] = new Air(x,y);
        }
        
        // Won the game
        if(this.item[x][y].getType() == Enums.BLOCK_TYPE.EXIT){
            
            this.setReachedExit(true);
        }
                
        ArrayList<Block> blocks = getAdjacentBlocks(this.hero);
        
        for (int i = 0; i < blocks.size(); i++) {
            
            Block block = blocks.get(i);
            
            // Actions for the Dragon
            
            if(blocks.get(i).getType() == Enums.BLOCK_TYPE.DRAGON) {
                                                
                x = block.getX();
                y = block.getY();
                
                Dragon dragon = new Dragon(1,1);
                int dragonIndex = 0;
                        
                for (int j = 0; j < this.dragonList.size(); j++) {
                                                            
                    int x1 = this.dragonList.get(j).getX();
                    int y1 = this.dragonList.get(j).getY();

                    if(x == x1 && y == y1){                       
                        dragon = this.dragonList.get(j);
                        dragonIndex = j;
                    }
                }
                                                    
                if(hero.isArmed()){

                    this.ground[x][y] = new Floor(x, y);
                    this.dragonList.remove(dragonIndex);                    

                } else {

                    if(!dragon.isAsleep())
                        hero.die();
                }
                
            }                        
        }                       
    }
}
