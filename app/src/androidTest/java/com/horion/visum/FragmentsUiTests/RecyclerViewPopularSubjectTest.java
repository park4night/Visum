package com.horion.visum.FragmentsUiTests;

import android.content.Context;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import com.horion.visum.Data.Database;
import com.horion.visum.Data.Entities.SubjectEntity;
import com.horion.visum.Data.SubjectOperations.SubjectDao;
import com.horion.visum.MainActivity;
import com.horion.visum.R;
import com.horion.visum.TestUtils.RecyclerViewItemCountAssertion;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4ClassRunner.class)
public class RecyclerViewPopularSubjectTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenario = new ActivityScenarioRule(MainActivity.class);
    private static final int  POSITION_ITEM_IN_TEST = 2;

    @Test
    public void is_popularsSubjectsRv_displayed() {
        onView(ViewMatchers.withId(R.id.rv_populars_subjects)).check(matches(isDisplayed()));
    }

    @Test
    public void is_right_fragment_open_on_item_click() {
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        List<SubjectEntity> subjects = getSubjectDao(context).getAllSubjectsList();

        SubjectEntity SUBJECT_IN_TEST = subjects.get(POSITION_ITEM_IN_TEST);
        onView(withId(R.id.rv_populars_subjects)).perform(RecyclerViewActions.actionOnItemAtPosition(POSITION_ITEM_IN_TEST, click()));

        onView(withId(R.id.subject_title)).check(matches(withText(SUBJECT_IN_TEST.getName())));
    }

    @Test
    public void PopularsSubjectsRvCount_matchPopularsSubjectsCount() {
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        int subjectCount = getSubjectDao(context).count();

        onView(withId(R.id.rv_populars_subjects)).check(new RecyclerViewItemCountAssertion(subjectCount));
    }

    public SubjectDao getSubjectDao(Context context) {
        Database database = Database.getDatabase(context);
        return database.subjectDao();
    }

    @Test
    public void is_back_navigation_toHomeFragment() {
        onView(withId(R.id.rv_populars_subjects)).perform(RecyclerViewActions.actionOnItemAtPosition(POSITION_ITEM_IN_TEST, click()));
        pressBack();

        onView(withId(R.id.rv_populars_subjects)).check(matches(isDisplayed()));
    }
}
