package com.horion.visum.FragmentsUiTests;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.horion.visum.Data.Database;
import com.horion.visum.Data.Entities.SubjectEntity;
import com.horion.visum.Data.SubjectOperations.SubjectDao;
import com.horion.visum.Domain.Model.Subject;
import com.horion.visum.MainActivity;
import com.horion.visum.Presentation.DisplaySubject.SubjectFragment;
import com.horion.visum.R;
import com.horion.visum.TestUtils.MapperTestUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4ClassRunner.class)
public class SubjectFragmentUiTest {

    private static final String SUBJECT_KEY = "subject_key";
    private Subject subjectTest;


    @Rule
    public ActivityScenarioRule<MainActivity> activityScenario = new ActivityScenarioRule(MainActivity.class);
    private Bundle bundle = new Bundle();

    @Before
    public void setUp() {
        SubjectEntity subjectEntity = getSubjectDao(ApplicationProvider.getApplicationContext()).getSubjectByName("Kendama");
        subjectTest = MapperTestUtil.subjectEntityToSubject(subjectEntity, 5, 5);
        bundle.putParcelable(SUBJECT_KEY, subjectTest);
        FragmentScenario.launchInContainer(SubjectFragment.class, bundle);
    }

    @Test
    public void is_title_displayed() {
        onView(ViewMatchers.withId(R.id.subject_title)).check(matches(isDisplayed()));
    }

    @Test
    public void is_title_correct() {
        onView(withId(R.id.subject_title)).check(matches(withText(subjectTest.getName())));
    }

    @Test
    public void is_positive_vote_btn_displayed() {
        onView(withId(R.id.subject_positive_vote_btn)).check(matches(isDisplayed()));
    }

    @Test
    public void is_negative_vote_btn_displayed() {
        onView(withId(R.id.subject_negative_vote_btn)).check(matches(isDisplayed()));
    }

    @Test
    public void is_recyclerView_displayed() {

        onView(withId(R.id.rv_arguments)).check(matches(isDisplayed()));
    }

    @Test
    public void is_add_button_displayed() {
        onView(withId(R.id.open_add_argument_btn)).check(matches(isDisplayed()));
    }

    public SubjectDao getSubjectDao(Context context) {
        Database database = Database.getDatabase(context);
        return database.subjectDao();
    }

}
