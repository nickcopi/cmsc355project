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

import java.util.ArrayList;
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
        onView(withId(R.id.surfaceView)).perform(touchDownAndUp(ingame.getPlayer().getX() + 100, ingame.getPlayer().getY()));
        assertEquals(Player.Directions.RIGHT, ingame.getPlayer().getDirection());
    }

    //Given I tap west of the player, when I am in game, then the player starts moving right
    @Test
    public void testMoveLeft() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        onView(withId(R.id.surfaceView)).perform(touchDownAndUp(ingame.getPlayer().getX() - 100, ingame.getPlayer().getY()));
        assertEquals(Player.Directions.LEFT, ingame.getPlayer().getDirection());
    }

    //Given I tap below the player, when I am in game, then the player starts moving down
    @Test
    public void testMoveDown() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        onView(withId(R.id.surfaceView)).perform(touchDownAndUp(ingame.getPlayer().getX(), ingame.getPlayer().getY() + 100));
        assertEquals(Player.Directions.DOWN, ingame.getPlayer().getDirection());
    }

    //Given I tap above the player, when I am in game, then the player starts moving up
    @Test
    public void testMoveUp() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        onView(withId(R.id.surfaceView)).perform(touchDownAndUp(ingame.getPlayer().getX(), ingame.getPlayer().getY() - 100));
        assertEquals(Player.Directions.UP, ingame.getPlayer().getDirection());
    }

    //As a player I want to not go off the games map so that I can continue to play the game.


    //Given I hit the right side of the play area, when I am moving, then I stop.
    @Test
    public void testRightBarrier() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getPlayer().setDirection(Player.Directions.RIGHT);
        for (int i = 0; i < 1000; i++) {
            ingame.getPlayer().move();
        }
        int oldX = ingame.getPlayer().getX();
        ingame.getPlayer().move();
        assertEquals(oldX, ingame.getPlayer().getX());
    }

    //Given I hit the left side of the play area, when I am moving, then I stop.
    @Test
    public void testLeftBarrier() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getPlayer().setDirection(Player.Directions.LEFT);
        for (int i = 0; i < 1000; i++) {
            ingame.getPlayer().move();
        }
        int oldX = ingame.getPlayer().getX();
        ingame.getPlayer().move();
        assertEquals(oldX, ingame.getPlayer().getX());
    }

    //Given I hit the bottom of the play area, when I am moving, then I stop.
    @Test
    public void testBottomBarrier() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getPlayer().setDirection(Player.Directions.DOWN);
        for (int i = 0; i < 1000; i++) {
            ingame.getPlayer().move();
        }
        int oldY = ingame.getPlayer().getY();
        ingame.getPlayer().move();
        assertEquals(oldY, ingame.getPlayer().getY());
    }

    //Given I hit the top of the play area, when I am moving, then I stop.
    @Test
    public void testTopBarrier() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getPlayer().setDirection(Player.Directions.UP);
        for (int i = 0; i < 1000; i++) {
            ingame.getPlayer().move();
        }
        int oldY = ingame.getPlayer().getY();
        ingame.getPlayer().move();
        assertEquals(oldY, ingame.getPlayer().getY());
    }

    //As a player that is moving I want to harvest items I collide with so that I have things to sell.

    //Given I collide with an item when I am on top of it then it should go in my inventory.
    @Test
    public void testItemIntoInvent() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getPlayer().clearItems();
        ingame.getHarvestableManager().addPlant(ingame.getPlayer().getX(), ingame.getPlayer().getY());
        ingame.getHarvestableManager().collidePlayer(ingame.getPlayer());
        assertEquals(1, ingame.getPlayer().getItems());
    }

    //Given I collide with an item when I have harvesting upgrades unlocked then I should harvest extra of it.
    @Test
    public void testNoItemIntoInvent() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getPlayer().clearItems();
        //ingame.getHarvestableManager().addPlant(ingame.getPlayer().getX(),ingame.getPlayer().getY());
        ingame.getHarvestableManager().collidePlayer(ingame.getPlayer());
        assertEquals(0, ingame.getPlayer().getItems());
    }

    //Given I collide with an item when I am on top of it then it should be cleared from the screen.
    @Test
    public void testItemOffScreen() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getHarvestableManager().addPlant(ingame.getPlayer().getX(), ingame.getPlayer().getY());
        int oldCount = ingame.getHarvestableManager().getItems();
        ingame.getHarvestableManager().collidePlayer(ingame.getPlayer());
        assertEquals(oldCount - 1, ingame.getHarvestableManager().getItems());
    }


//As a player with some specific item I want to get money when I click the item’s sell button so that I can buy upgrades.

    //Given I press the sell button when I have an item to sell then the appropriate amount of them are sold
    @Test
    public void testItemSell() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getPlayer().clearItems();
        ingame.getPlayer().setFrozen(true);
        ingame.getPlayer().incrementItems();
        assertEquals(1, ingame.getPlayer().getItems());
        onView(withId(R.id.sell)).perform(click());
        assertEquals(0, ingame.getPlayer().getItems());
    }

    //Given I press the sell button when I have no items to sell then nothing happens.
    @Test
    public void testNoItemSell() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        //ingame.getPlayer().incrementItems();
        ingame.getPlayer().clearItems();
        ingame.getPlayer().setFrozen(true);
        onView(withId(R.id.sell)).perform(click());
        assertEquals(0, ingame.getPlayer().getItems());
        assertEquals(0, ingame.getPlayer().getMoney());
    }

    //Given I press the sell button when I have an item to sell then money goes up.
    @Test
    public void testItemSellMoney() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getPlayer().clearItems();
        ingame.getPlayer().setFrozen(true);
        ingame.getPlayer().incrementItems();
        int oldMoney = ingame.getPlayer().getMoney();
        onView(withId(R.id.sell)).perform(click());
        assertEquals(oldMoney + 2, ingame.getPlayer().getMoney());
    }

//As a player looking to more quickly harvest items I want to move faster when I buy a speed upgrade so that I can make more money.


    //Given I press the speed upgrade when I have money then my speed increases by the upgrades amount.
    @Test
    public void testSpeedUpgrade() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getPlayer().getWallet().addMoney(10000);
        double oldSpeed = ingame.getPlayer().getSpeed();
        onView(withId(R.id.upgrade)).perform(click());
        onView(withId(R.id.SpeedButton)).perform(click());
        onView(withId(R.id.back)).perform(click());
        assertEquals(true, oldSpeed < ingame.getPlayer().getSpeed());
    }

    //Given I press the speed upgrade when my speed is at maximum then my speed doesn’t change and no money is taken, as well as no upgrades locked.
    @Test
    public void testSpeedUpgradeLock() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getPlayer().getWallet().addMoney(100000);
        onView(withId(R.id.upgrade)).perform(click());
        for (int i = 0; i < 31; i++)
            onView(withId(R.id.SpeedButton)).perform(click());
        onView(withId(R.id.back)).perform(click());
        double oldSpeed = ingame.getPlayer().getSpeed();
        int oldMoney = ingame.getPlayer().getMoney();
        onView(withId(R.id.upgrade)).perform(click());
        onView(withId(R.id.SpeedButton)).perform(click());
        onView(withId(R.id.back)).perform(click());

        assertEquals(true, oldSpeed == ingame.getPlayer().getSpeed());
        assertEquals(true, oldMoney == ingame.getPlayer().getMoney());

    }

    //Given I press the speed upgrade when I haven't enough money then nothing happens.
    @Test
    public void testNoSpeedUpgrade() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        double oldSpeed = ingame.getPlayer().getSpeed();
        onView(withId(R.id.upgrade)).perform(click());
        onView(withId(R.id.SpeedButton)).perform(click());
        onView(withId(R.id.back)).perform(click());
        assertEquals(true, oldSpeed == ingame.getPlayer().getSpeed());
    }

//As a player looking to easier harvest items I want to become larger when I buy a size upgrade so that I can make more money.


    //Given I press the size upgrade when I have money then my size increases by the upgrades amount.
    @Test
    public void testSizeUpgrade() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getPlayer().getWallet().addMoney(10000);
        double oldSize = ingame.getPlayer().getWidth();
        onView(withId(R.id.upgrade)).perform(click());
        onView(withId(R.id.SizeButton)).perform(click());
        onView(withId(R.id.back)).perform(click());
        assertEquals(true, oldSize < ingame.getPlayer().getWidth());
    }

    //Given I press the size upgrade when my size is at maximum then my size doesn’t change and no money is taken.
    @Test
    public void testSizeUpgradeLock() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getPlayer().getWallet().addMoney(100000);
        onView(withId(R.id.upgrade)).perform(click());
        for (int i = 0; i < 21; i++)
            onView(withId(R.id.SizeButton)).perform(click());
        onView(withId(R.id.back)).perform(click());
        double oldSize = ingame.getPlayer().getWidth();
        int oldMoney = ingame.getPlayer().getMoney();
        onView(withId(R.id.upgrade)).perform(click());
        onView(withId(R.id.SizeButton)).perform(click());
        onView(withId(R.id.back)).perform(click());

        assertEquals(true, oldSize == ingame.getPlayer().getWidth());
        assertEquals(true, oldMoney == ingame.getPlayer().getMoney());

    }

    //Given I press the size upgrade when I haven't enough money then nothing happens.
    @Test
    public void testNoSizeUpgrade() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        double oldSize = ingame.getPlayer().getWidth();
        onView(withId(R.id.upgrade)).perform(click());
        onView(withId(R.id.SizeButton)).perform(click());
        onView(withId(R.id.back)).perform(click());
        assertEquals(true, oldSize == ingame.getPlayer().getWidth());
    }

//As a player I want collected items to repopulate when all are collected so that I can continue playing.

    //Given I collide with an item when there are still more on the screen then they don't regenerate.
    @Test
    public void testNoRegenerateGrass() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        int oldSize = ingame.getHarvestableManager().getItems();
        ingame.getHarvestableManager().collidePlayer(ingame.getPlayer());
        ingame.getPlayer().clearItems();
        ingame.getHarvestableManager().addPlant(ingame.getPlayer().getX(), ingame.getPlayer().getY());
        ingame.getHarvestableManager().collidePlayer(ingame.getPlayer());
        assertEquals(oldSize, ingame.getHarvestableManager().getItems());
    }

    //Given I collide with an item when it is the last one on the screen then they regenerate.
    @Test
    public void testRegenerateGrass() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getHarvestableManager().collidePlayer(ingame.getPlayer());
        ingame.getPlayer().clearItems();
        ingame.getHarvestableManager().clearItems();
        ingame.getHarvestableManager().addPlant(ingame.getPlayer().getX(), ingame.getPlayer().getY());
        ingame.getHarvestableManager().collidePlayer(ingame.getPlayer());
        assertEquals(true, ingame.getHarvestableManager().getItems()>1);
    }

    //Given I collide with an item when it will cause a regeneration then regenerated items will be be within the bounds of the game.
    @Test
    public void testRegenerateGrassBounds() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getHarvestableManager().collidePlayer(ingame.getPlayer());
        ingame.getPlayer().clearItems();
        ingame.getHarvestableManager().clearItems();
        ingame.getHarvestableManager().addPlant(ingame.getPlayer().getX(), ingame.getPlayer().getY());
        ingame.getHarvestableManager().collidePlayer(ingame.getPlayer());
        ArrayList<Collidable> items = ingame.getHarvestableManager().getPlants();
        for(Collidable item: items){
            assertEquals(true,PhysicsManager.isValidPosition(item));
        }
        //assertEquals(true, ingame.getHarvestableManager().getItems()>1);
    }




//As a player trying to make money, I want to be able to purchase sales upgrades, so that I can sell more items at once for more profit.

    //Given I press the sales upgrade when I have money then my maximum sell amount increases by the upgrades amount.
    @Test
    public void testSalesUpgradePrice() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getPlayer().getWallet().addMoney(10000);
        double oldMultiplier = ingame.getPlayer().getSellMultiplier();
        onView(withId(R.id.upgrade)).perform(click());
        onView(withId(R.id.SalesButton)).perform(click());
        onView(withId(R.id.back)).perform(click());
        assertEquals(true, oldMultiplier < ingame.getPlayer().getSellMultiplier());
    }

    //Given I press the sales upgrade when I have money then my sell bonus increases by the upgrades amount.
    @Test
    public void testSalesUpgradeAmount() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getPlayer().getWallet().addMoney(10000);
        int oldMax = ingame.getPlayer().getMaxStack();
        onView(withId(R.id.upgrade)).perform(click());
        onView(withId(R.id.SalesButton)).perform(click());
        onView(withId(R.id.back)).perform(click());
        assertEquals(true, oldMax < ingame.getPlayer().getMaxStack());
    }

    //Given I press the sales upgrade when I haven't enough money then nothing happens.
    @Test
    public void testNoSalesUpgrade() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        double oldMultiplier = ingame.getPlayer().getSellMultiplier();
        onView(withId(R.id.upgrade)).perform(click());
        onView(withId(R.id.SalesButton)).perform(click());
        onView(withId(R.id.back)).perform(click());
        assertEquals(true, oldMultiplier == ingame.getPlayer().getSellMultiplier());
    }


//As a player that wants to make more money with less effort I want to be able to be able to unlock an auto move upgrade so that I can make money while not interacting with the game directly.



    //Given I press the unlock button for the upgrade when I can afford it then I unlock it.
    @Test
    public void testAutoMoveUpgrade() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getPlayer().getWallet().addMoney(10000);
        onView(withId(R.id.upgrade)).perform(click());
        onView(withId(R.id.AutomoveButton)).perform(click());
        onView(withId(R.id.back)).perform(click());
        assertEquals(ingame.getPlayer().canAutoMove(), true);
    }



    //Given I press the unlock button for the upgrade when I can't afford it then nothing happens.
    @Test
    public void testNoAutoMoveUpgrade() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        //ingame.getPlayer().getWallet().addMoney(10000);
        onView(withId(R.id.upgrade)).perform(click());
        onView(withId(R.id.AutomoveButton)).perform(click());
        onView(withId(R.id.back)).perform(click());
        assertEquals(ingame.getPlayer().canAutoMove(), false);
    }


    //Given I press the unlock button for the upgrade when I already have it then nothing happens.
    @Test
    public void testAutoMoveLockedUpgrade() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getPlayer().getWallet().addMoney(10000);
        int oldMoney = ingame.getPlayer().getMoney() - ingame.getPlayer().getUpgradeManager().getMoveUpgrade().getCost();
        onView(withId(R.id.upgrade)).perform(click());
        onView(withId(R.id.AutomoveButton)).perform(click());
        onView(withId(R.id.AutomoveButton)).perform(click());
        onView(withId(R.id.back)).perform(click());
        assertEquals(ingame.getPlayer().getMoney(), oldMoney);
    }

//As a player I want to be able to move between the ingame and upgrade menu without money or upgrades disappearing so that I can play more effectively

    //Given I return to the ingame menu from the upgrade menu when I have spent no money then my money will remain the same.
    @Test
    public void testMenuSyncNoBuy() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getPlayer().getWallet().addMoney(10000);
        onView(withId(R.id.upgrade)).perform(click());
       // onView(withId(R.id.AutomoveButton)).perform(click());
        onView(withId(R.id.back)).perform(click());
        assertEquals(ingame.getPlayer().getMoney(), 10000);
    }


    //Given I return to the ingame menu from the upgrade menu when I have spent money then my money will be updated.
    @Test
    public void testMenuSyncBuy() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getPlayer().getWallet().addMoney(10000);
        int oldMoney = ingame.getPlayer().getMoney() - ingame.getPlayer().getUpgradeManager().getMoveUpgrade().getCost();
        onView(withId(R.id.upgrade)).perform(click());
        onView(withId(R.id.AutomoveButton)).perform(click());
        onView(withId(R.id.back)).perform(click());
        assertEquals(ingame.getPlayer().getMoney(), oldMoney);
    }

    //Given I return to the ingame menu from the upgrade menu when I have purchased an upgrade then the upgrade will remain purchased.
    @Test
    public void testMenuSyncBuyUpgrade() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        ingame.getPlayer().getWallet().addMoney(10000);
        int oldLevel = ingame.getPlayer().getUpgradeManager().getSpeedUpgrade().getLevel();
        onView(withId(R.id.upgrade)).perform(click());
        onView(withId(R.id.SpeedButton)).perform(click());
        onView(withId(R.id.back)).perform(click());
        assertEquals(ingame.getPlayer().getUpgradeManager().getSpeedUpgrade().getLevel(), oldLevel + 1);
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
