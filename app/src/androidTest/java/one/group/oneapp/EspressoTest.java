package one.group.oneapp;


import androidx.test.espresso.Espresso;
import org.junit.Rule;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Test;

import org.junit.runner.RunWith;

import static android.app.PendingIntent.getActivity;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class EspressoTest {


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

}
