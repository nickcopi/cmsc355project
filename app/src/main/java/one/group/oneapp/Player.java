package one.group.oneapp;

public class Player implements Collidable{
    private int x,y,width,height;
    private double speed;
    private Directions direction;
    public enum Directions{
        LEFT,
        RIGHT,
        UP,
        DOWN
    }
    public Player(){
        this.x = 250;
        this.y = 400;
        this.height = 100;
        this.width = 100;
        this.speed = 5;
        this.direction = Directions.LEFT;
    }
    public void move(){
        switch(this.direction){
            case LEFT:
                this.x += this.speed * -1;
                break;
            case RIGHT:
                this.x += this.speed;
                break;
            case UP:
                this.y += this.speed * -1;
                break;
            case DOWN:
                this.y += this.speed;
                break;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }
}
