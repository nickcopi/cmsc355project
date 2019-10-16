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


    @Test
    public void testPlayButton() {
        onView(withId(R.id.play)).perform(click());

        onView(withId(R.id.upgrade)).check(matches(withId(R.id.upgrade)));
    }
    @Test
    public void testUpgradeMenuButton() {
        onView(withId(R.id.play)).perform(click());
        onView(withId(R.id.upgrade)).perform(click());
        onView(withId(R.id.back)).check(matches(withId(R.id.back)));
    }

    @Test
    public void testBackButton() {
        onView(withId(R.id.play)).perform(click());
        onView(withId(R.id.upgrade)).perform(click());
        onView(withId(R.id.back)).perform(click());
        onView(withId(R.id.upgrade)).check(matches(withId(R.id.upgrade)));
    }
    @Test
    public void testMoveRight() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        onView(withId(R.id.surfaceView)).perform(touchDownAndUp(400,300));
        assertEquals(Player.Directions.RIGHT,ingame.getPlayer().getDirection());
    }
    @Test
    public void testMoveLeft() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        onView(withId(R.id.surfaceView)).perform(touchDownAndUp(ingame.getPlayer().getX()-100,ingame.getPlayer().getY()));
        assertEquals(Player.Directions.LEFT,ingame.getPlayer().getDirection());
    }
    @Test
    public void testMoveDown() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        onView(withId(R.id.surfaceView)).perform(touchDownAndUp(ingame.getPlayer().getX(),ingame.getPlayer().getY()+100));
        assertEquals(Player.Directions.DOWN,ingame.getPlayer().getDirection());
    }
    @Test
    public void testMoveUp() {
        onView(withId(R.id.play)).perform(click());
        InGame ingame = (InGame) getActivityInstance();
        onView(withId(R.id.surfaceView)).perform(touchDownAndUp(ingame.getPlayer().getX(),ingame.getPlayer().getY()-100));
        assertEquals(Player.Directions.UP,ingame.getPlayer().getDirection());
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
