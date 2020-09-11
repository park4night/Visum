package com.horion.visum.FragmentsUiTests;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.horion.visum.MainActivity;
import com.horion.visum.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4ClassRunner.class)
public class HomeFragmentUiTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenario = new ActivityScenarioRule(MainActivity.class);

    @Test
    public void is_fragmentHost_displayed() {
        onView(ViewMatchers.withId(R.id.home_fragment_container)).check(matches(isDisplayed()));
    }

    @Test
    public void is_title_popular_displayed() {
        onView(withId(R.id.popular_subjects_title)).check(matches(isDisplayed()));
    }

    @Test
    public void is_addSubjectBtn_displayed() {
        onView(withId(R.id.open_add_subject_btn)).check(matches(isDisplayed()));
    }

    @Test
    public void is_title_popular_matches_text() {
        onView(withId(R.id.popular_subjects_title)).check(matches(withText(R.string.popular_subjects_title)));
    }

    @Test
    public void is_bottomBar_displayed() {
        onView(withId(R.id.bottom_navigation_bar)).check(matches(isDisplayed()));
    }
}
