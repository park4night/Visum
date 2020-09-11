package com.horion.visum.FragmentsUiTests;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.horion.visum.Presentation.AddSubject.AddSubjectFragment;
import com.horion.visum.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class AddSubjectFragmentUiTests {

    @Before
    public void setUp() {
        FragmentScenario.launchInContainer(AddSubjectFragment.class);
    }

    @Test
    public void is_title_displayed() {
        onView(ViewMatchers.withId(R.id.add_subject_title)).check(matches(isDisplayed()));
    }

    @Test
    public void is_name_input_displayed() {
        onView(withId(R.id.subject_name_input)).check(matches(isDisplayed()));
    }

    @Test
    public void is_theme_input_displayed() {
        onView(withId(R.id.subject_theme_input)).check(matches(isDisplayed()));
    }

    @Test
    public void is_add_btn_displayed() {
        onView(withId(R.id.add_subject_btn)).check(matches(isDisplayed()));
    }
}
