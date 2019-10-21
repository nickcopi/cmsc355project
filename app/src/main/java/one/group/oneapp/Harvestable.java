package one.group.oneapp;

public abstract class Harvestable implements Collidable {
    private int x;
    private int y;
    private int width;
    private int height;

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
