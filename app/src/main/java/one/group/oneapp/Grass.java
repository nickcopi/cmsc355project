package one.group.oneapp;

import java.io.Serializable;

public class Grass extends Harvestable implements Serializable {
    static final int width = 20;
    static final int height = 20;
    public Grass(int x,int y){
        super(x,y,width,height);
    }
    public Grass(){
        super();
    }
}
