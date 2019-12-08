package one.group.oneapp;

import java.io.Serializable;

public abstract class Harvestable implements Collidable, Serializable {
    private int x;
    private int y;
    private int width;
    private int height;

    public Harvestable(int x,int y,int width,int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public  Harvestable(){

    }
    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
