package one.group.oneapp;


import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;

import androidx.test.espresso.Espresso;

import org.hamcrest.Matcher;
import org.junit.Rule;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.MotionEvents;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.core.internal.deps.guava.collect.Iterables;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;

import org.junit.Test;

import org.junit.runner.RunWith;

import java.util.Collection;

import static android.app.PendingIntent.getActivity;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static androidx.test.runner.lifecycle.Stage.RESUMED;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class EspressoTest {


    Activity currentActivity = null;

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityRule =
            new ActivityScenarioRule<>(MainActivity.class);


    /*
    * As a player seeking fun I want to be able to access menus so that I can play the game. #8
    * */


    //Given I am on the start menu when I hit the start button then I am sent to the game activity.
    @Test
    public void testPlayButton() {
        onView(withId(R.id.play)).perform(click());

        onView(withId(R.id.upgrade)).check(matches(withId(R.id.upgrade)));
    }
    //Given I am on the game activity when I hit the upgrade menu then I am sent to the upgrade menu.
    @Test
    public void testUpgradeMenuButton() {
        onView(withId(R.id.play)).perform(click());
        onView(withId(R.id.upgrade)).perform(click());
        onView(withId(R.id.back)).check(matches(withId(R.id.back)));
    }
    //Given I am on the upgrade menu when I press the back button then I am put back to the game activity.
    @Test
    public void testBackButton() {
        onView(withId(R.id.play)).perform(click());
        onView(withId(R.id.upgrade)).perform(click());
        onView(withId(R.id.back)).perform(click());
        onView(withId(R.id.upgrade)).check(matches(withId(R.id.upgrade)));
    }



    /*
     *As a player that controls the character when I swipe I want to change directions so that I can control which way I move #10
     *
     * */

    //Given I tap east of the player, when I am in game, then the player starts moving right
    @Test
    public void testMoveRight() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        onView(withId(R.id.surfaceView)).perform(touchDownAndUp(ingame.getPlayer().getX()+100,ingame.getPlayer().getY()));
        assertEquals(Player.Directions.RIGHT,ingame.getPlayer().getDirection());
    }
    //Given I tap west of the player, when I am in game, then the player starts moving right
    @Test
    public void testMoveLeft() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        onView(withId(R.id.surfaceView)).perform(touchDownAndUp(ingame.getPlayer().getX()-100,ingame.getPlayer().getY()));
        assertEquals(Player.Directions.LEFT,ingame.getPlayer().getDirection());
    }
    //Given I tap below the player, when I am in game, then the player starts moving down
    @Test
    public void testMoveDown() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        onView(withId(R.id.surfaceView)).perform(touchDownAndUp(ingame.getPlayer().getX(),ingame.getPlayer().getY()+100));
        assertEquals(Player.Directions.DOWN,ingame.getPlayer().getDirection());
    }
    //Given I tap above the player, when I am in game, then the player starts moving up
    @Test
    public void testMoveUp() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        onView(withId(R.id.surfaceView)).perform(touchDownAndUp(ingame.getPlayer().getX(),ingame.getPlayer().getY()-100));
        assertEquals(Player.Directions.UP,ingame.getPlayer().getDirection());
    }

    //As a player I want to not go off the games map so that I can continue to play the game.


    //Given I hit the right side of the play area, when I am moving, then I stop.
    @Test
    public void testRightBarrier() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getPlayer().setDirection(Player.Directions.RIGHT);
        for(int i = 0;i<1000;i++){
            ingame.getPlayer().move();
        }
        int oldX = ingame.getPlayer().getX();
        ingame.getPlayer().move();
        assertEquals(oldX,ingame.getPlayer().getX());
    }

    //Given I hit the left side of the play area, when I am moving, then I stop.
    @Test
    public void testLeftBarrier() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getPlayer().setDirection(Player.Directions.LEFT);
        for(int i = 0;i<1000;i++){
            ingame.getPlayer().move();
        }
        int oldX = ingame.getPlayer().getX();
        ingame.getPlayer().move();
        assertEquals(oldX,ingame.getPlayer().getX());
    }

    //Given I hit the bottom of the play area, when I am moving, then I stop.
    @Test
    public void testBottomBarrier() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getPlayer().setDirection(Player.Directions.DOWN);
        for(int i = 0;i<1000;i++){
            ingame.getPlayer().move();
        }
        int oldY = ingame.getPlayer().getY();
        ingame.getPlayer().move();
        assertEquals(oldY,ingame.getPlayer().getY());
    }
    //Given I hit the top of the play area, when I am moving, then I stop.
    @Test
    public void testTopBarrier() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getPlayer().setDirection(Player.Directions.UP);
        for(int i = 0;i<1000;i++){
            ingame.getPlayer().move();
        }
        int oldY = ingame.getPlayer().getY();
        ingame.getPlayer().move();
        assertEquals(oldY,ingame.getPlayer().getY());
    }

    //As a player that is moving I want to harvest items I collide with so that I have things to sell.

    //Given I collide with an item when I am on top of it then it should go in my inventory.
    @Test
    public void testItemIntoInvent() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getHarvestableManager().addPlant(ingame.getPlayer().getX(),ingame.getPlayer().getY());
        ingame.getHarvestableManager().collidePlayer(ingame.getPlayer());
        assertEquals(1,ingame.getPlayer().getItems());
    }

    //Given I collide with an item when I have harvesting upgrades unlocked then I should harvest extra of it.
    @Test
    public void testNoItemIntoInvent() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        //ingame.getHarvestableManager().addPlant(ingame.getPlayer().getX(),ingame.getPlayer().getY());
        ingame.getHarvestableManager().collidePlayer(ingame.getPlayer());
        assertEquals(0,ingame.getPlayer().getItems());
    }

    //Given I collide with an item when I am on top of it then it should be cleared from the screen.
    @Test
    public void testItemOffScreen() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getHarvestableManager().addPlant(ingame.getPlayer().getX(),ingame.getPlayer().getY());
        int oldCount = ingame.getHarvestableManager().getItems();
        ingame.getHarvestableManager().collidePlayer(ingame.getPlayer());
        assertEquals(oldCount-1,ingame.getHarvestableManager().getItems());
    }


//As a player with some specific item I want to get money when I click the item’s sell button so that I can buy upgrades.

    //Given I press the sell button when I have an item to sell then the appropriate amount of them are sold
    @Test
    public void testItemSell() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getPlayer().incrementItems();
        assertEquals(1,ingame.getPlayer().getItems());
        onView(withId(R.id.sell)).perform(click());
        assertEquals(0,ingame.getPlayer().getItems());
    }

    //Given I press the sell button when I have no items to sell then nothing happens.
    @Test
    public void testNoItemSell() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        //ingame.getPlayer().incrementItems();
        onView(withId(R.id.sell)).perform(click());
        assertEquals(0,ingame.getPlayer().getItems());
        assertEquals(0,ingame.getPlayer().getMoney());
    }

    //Given I press the sell button when I have an item to sell then money goes up.
    @Test
    public void testItemSellMoney() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getPlayer().incrementItems();
        int oldMoney = ingame.getPlayer().getMoney();
        onView(withId(R.id.sell)).perform(click());
        assertEquals(oldMoney+2,ingame.getPlayer().getMoney());
    }







    public Activity getActivityInstance(){
        getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Collection resumedActivities =
                        ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(RESUMED);
                if (resumedActivities.iterator().hasNext()){
                    currentActivity = (Activity) resumedActivities.iterator().next();
                }
            }
        });

        return currentActivity;
    }



    //code yoinked from https://stackoverflow.com/questions/39438575/android-espresso-how-do-i-perform-touch-events
    public static ViewAction touchDownAndUp(final float x, final float y) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isDisplayed();
            }

            @Override
            public String getDescription() {
                return "Send touch events.";
            }

            @Override
            public void perform(UiController uiController, final View view) {
                // Get view absolute position
                int[] location = new int[2];
                view.getLocationOnScreen(location);

                // Offset coordinates by view position
                float[] coordinates = new float[] { x + location[0], y + location[1] };
                float[] precision = new float[] { 1f, 1f };

                // Send down event, pause, and send up
                MotionEvent down = MotionEvents.sendDown(uiController, coordinates, precision).down;
                uiController.loopMainThreadForAtLeast(200);
                MotionEvents.sendUp(uiController, down, coordinates);
            }
        };
    }

}
