package one.group.oneapp;

public class PhysicsManager {
    public static boolean collides(Collidable o1, Collidable o2){
        return (o1.getX() + o1.getWidth() > o2.getX() && o1.getX() < o2.getX()+o2.getWidth() &&
                o1.getY() + o1.getHeight() > o2.getY() && o1.getY() < o2.getY()+o2.getHeight());
    }
}
