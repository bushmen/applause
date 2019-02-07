package com.buszko.myapplication;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void click_first_item_and_check_details_title() throws Exception {
        // Type text and then press the button.
        Thread.sleep(2000);
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
        Espresso.onView(ViewMatchers.withId(R.id.details_text_view)).
                check(ViewAssertions.matches(ViewMatchers.withText("ConsumerGraph")));
    }
}
