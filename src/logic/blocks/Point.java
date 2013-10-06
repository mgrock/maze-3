package logic.blocks;

import logic.Enums;

public class Point implements Block {

    protected int x;
    protected int y;
    protected Enums.BLOCK_TYPE type;

    protected Point setX(int x) {

        this.x = x;
        return this;
    }

    protected Point setY(int y) {

        this.y = y;
        return this;
    }

    public Point setType(Enums.BLOCK_TYPE type) {

        this.type = type;
        return this;
    }

    @Override
    public Enums.BLOCK_TYPE getType() {

        return this.type;
    }

    @Override
    public int getX() {

        return this.x;
    }

    @Override
    public int getY() {

        return this.y;
    }

    public Point(int x, int y) {

        this.setX(x);
        this.setY(y);
    }
}
