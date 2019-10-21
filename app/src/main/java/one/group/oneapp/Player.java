package one.group.oneapp;

//import android.view.GestureDetector;
//import android.view.MotionEvent;
//import android.widget.ImageView;

public class Player implements Collidable //GestureDetector.OnGestureListener
{
//    //No clue what the GestureDetector line does
//    private GestureDetector gestureDetector = new GestureDetector(this);
//    ImageView testImage;
//
//    //Image Dimensions
//    int imageX;
//    int imageY;
//
//    //Screen Dimensions
//    private int screenWidth;
//    private int screenHeight;

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

//    //Methods needed by GestureDetector
//    @Override
//    public boolean onDown(MotionEvent e) {
//        return false;
//    }
//
//    @Override
//    public void onShowPress(MotionEvent e) {
//
//    }
//
//    @Override
//    public boolean onSingleTapUp(MotionEvent e) {
//        return false;
//    }
//
//    @Override
//    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//        return false;
//    }
//
//    @Override
//    public void onLongPress(MotionEvent e) {
//
//    }
//
//    @Override
//    public boolean onFling(MotionEvent pressDown, MotionEvent moveFinger, float xAxis, float yAxis)
//    {
//        float xDistance = moveFinger.getX() - pressDown.getX(); //Distance of swipe in X direction
//        float yDistance = moveFinger.getY() - pressDown.getY(); //Distance of swipe in Y direction
//
//        if(Math.abs(xDistance) > Math.abs(yDistance)) //If swiped farther in X direction then Y
//        {
//            if(Math.abs(xDistance) > 50 && Math.abs(xAxis) > 50)//Threshold for how far user has to swipe and how fast
//            {
//                if(xDistance > 0) //Swiped right
//                {
//                    moveImage("Right");
//                    return true;
//                }
//                else //Swiped left
//                {
//                    moveImage("Left");
//                    return true;
//                }
//            }
//        }
//        else
//        {
//            if(Math.abs(yDistance) > 50 && Math.abs(yAxis) > 50)
//            {
//                if(yDistance < 0) //Swiped up, Up is considered the negative direction????
//                {
//                    moveImage("Up");
//                    return true;
//                }
//                else //Swiped down
//                {
//                    moveImage("Down");
//                    return true;
//                }
//            }
//        }
//
//        return false;//Only reached if user didnt swipe correctly
//    }
//
//    //Moves image 100 pixels in swiped Direction
//    private void moveImage(String Direction)
//    {
//        switch(Direction)
//        {
//            case "Up":
//            {
//                imageY = imageY - 100;
//
//                if(imageY < 0)
//                {
//                    imageY = 0;
//                    testImage.setY(imageY);
//                }
//                else
//                {
//                    testImage.setY(imageY);
//                }
//
//                break;
//            }
//            case "Down":
//            {
//                imageY = imageY + 100;
//
//                if(imageY > screenHeight)//If move will put past ceiling, just move picture to wall
//                {
//                    imageY = screenHeight;
//                    testImage.setY(imageY);
//                }
//                else
//                {
//                    testImage.setY(imageY);
//                }
//
//                break;
//            }
//            case "Left":
//            {
//                imageX = imageX - 100;
//
//                if(imageX < 0)
//                {
//                    imageX = 0;
//                    testImage.setX(imageX);
//                }
//                else
//                {
//                    testImage.setX(imageX);
//                }
//
//                break;
//            }
//            case "Right":
//            {
//                imageX = imageX + 100;
//
//                if(imageX > screenWidth)
//                {
//                    imageX = screenWidth;
//                    testImage.setX(imageX);
//                }
//                else
//                {
//                    testImage.setX(imageX);
//                }
//
//                break;
//            }
//        }
//
//        System.out.println("Direction moved: " + Direction + " x coordinates: " + imageX + " y coordinates: " + imageY);
//    }


}
