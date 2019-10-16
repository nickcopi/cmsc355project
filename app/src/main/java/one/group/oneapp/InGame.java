package one.group.oneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;


public class InGame extends Activity implements SurfaceHolder.Callback{
    private final int UPGRADE_MENU = 1;

    private myThread thread;
    public Paint black;
    private int height = 480, width = 480;  //defaults incase not set yet.
    float scale;
    Player player = new Player();

    SurfaceView mSurfaceView;
    String TAG = "AllinOneActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_in_game);
        //setup everything needed.
        scale = getResources().getDisplayMetrics().density; //this gives me the scale value for a mdpi baseline of 1.

        black = new Paint();  //default is black and we really are not using it.  need it to draw the alien.


        //get a generic surface and all our callbacks to it, with a touchlistener.
        mSurfaceView = findViewById(R.id.surfaceView); //new SurfaceView(this);
        mSurfaceView.getHolder().addCallback(this);
        mSurfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                // Retrieve the new x and y touch positions
                int touchx = (int) event.getX();
                int touchy = (int) event.getY();
                Log.w(TAG,"X: " + touchx + " Y: " + touchy);
                int xDiff = Math.abs(touchx - player.getX());
                int yDiff = Math.abs(touchy - player.getY());
                if (xDiff > yDiff) {
                    if (touchx > player.getX()) {
                        player.setDirection(Player.Directions.RIGHT);
                    } else {
                        player.setDirection(Player.Directions.LEFT);
                    }
                } else {
                    if (touchy > player.getY()) {
                        player.setDirection(Player.Directions.DOWN);
                    } else {
                        player.setDirection(Player.Directions.UP);
                    }
                }
                return true;
            }

        });
    }
    public void clickUpgrades(View view){
        Intent intent = new Intent(InGame.this,UpgradeMenu.class);
        startActivityForResult(intent, UPGRADE_MENU);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == UPGRADE_MENU) {
            if (resultCode == RESULT_OK) {

            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void draw(Canvas c) {

        c.drawColor(Color.BLACK);
        //c.drawBitmap(alien, null, myRect, black);
        Paint brush = new Paint();
        brush.setColor(Color.rgb(0xff,0xf0,0xff));
        //brush.setStyle(Paint.Style.STROKE);
        c.drawRect(player.getX(),player.getY(),player.getX()+player.getWidth(),player.getY()+player.getHeight(),brush);

    }


    //all the methods needed for the SurfaceHolder.Callback
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.v(TAG, "surfaceCreated");
        //everything is setup, now start.
        height = mSurfaceView.getHeight();
        width = mSurfaceView.getWidth();
        //setup the thread for animation.
        thread = new myThread(mSurfaceView.getHolder());
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.v(TAG, "surfaceChanged");
        //ignored.
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.v(TAG, "surfaceDestroyed");
        // we have to tell thread to shut down & wait for it to finish, or else
        // it might touch the Surface after we return and explode
        boolean retry = true;
        thread.setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // we will try it again and again...
            }
        }
    }
    class myThread extends Thread {
        private SurfaceHolder _surfaceHolder;

        private boolean Running = false;

        public myThread(SurfaceHolder surfaceHolder) {
            _surfaceHolder = surfaceHolder;
        }

        public void setRunning(boolean run) {
            Running = run;
        }

        @Override
        public void run() {
            Canvas c;
            while (Running && !Thread.interrupted()) {
                c = null;
                try {
                    c = _surfaceHolder.lockCanvas(null);
                    synchronized (_surfaceHolder) {
                        //call a method that draws all the required objects onto the canvas.
                        draw(c);
                    }
                } finally {
                    // do this in a finally so that if an exception is thrown
                    // during the above, we don't leave the Surface in an
                    // inconsistent state
                    if (c != null) {
                        _surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
                //handle the game updates
                player.move();

                //sleep for a short period of time.
                if (!Running) return;  //don't sleep, just exit if we are done.
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        }
    }
}
