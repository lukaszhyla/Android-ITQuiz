package com.lhyla.itquiz;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class EndGameActivityTest {
    @Rule
    public ActivityTestRule<EndGameActivity> espressoGameActivityTestRule =
            new ActivityTestRule<>(EndGameActivity.class);

    @Test
    public void firstPlayAgainBtn() {
        onView(withId(R.id.act_eng_game_play_again_btn)).perform(click());
    }

}
