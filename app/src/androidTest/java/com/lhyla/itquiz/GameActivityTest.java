package com.lhyla.itquiz;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.runner.RunWith;


/**
 * Created by RENT on 2017-08-07.
 */

@RunWith(AndroidJUnit4.class)
public class GameActivityTest {
    @Rule
    public ActivityTestRule<GameActivity> espressoGameActivityTestRule =
            new ActivityTestRule<>(GameActivity.class);



}
