package com.horion.visum.FragmentsUiTests;

import android.os.Bundle;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.horion.visum.Data.Entities.SubjectEntity;
import com.horion.visum.Domain.Model.Subject;
import com.horion.visum.Presentation.AddArgument.AddArgumentFragment;
import com.horion.visum.R;
import com.horion.visum.RoomDatabaseTests.FakeData;
import com.horion.visum.TestUtils.MapperTestUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class AddArgumentFragmentUiTest {

    private Bundle bundle = new Bundle();

    @Before
    public void setUp() throws Exception {
        SubjectEntity subjectEntity = FakeData.SUBJECTS.get(0);
        Subject subject = MapperTestUtil.subjectEntityToSubject(subjectEntity, 5, 5);
        bundle.putParcelable("subject_key", subject);
        FragmentScenario.launchInContainer(AddArgumentFragment.class, bundle);
    }

    @Test
    public void is_title_displayed() {
        onView(ViewMatchers.withId(R.id.add_argument_title)).check(matches(isDisplayed()));
    }

    @Test
    public void is_name_input_displayed() {
        onView(withId(R.id.argument_name_input)).check(matches(isDisplayed()));
    }

    @Test
    public void is_description_input_displayed() {
        onView(withId(R.id.argument_description_input)).check(matches(isDisplayed()));
    }

    @Test
    public void is_add_button_displayed() {
        onView(withId(R.id.add_argument_btn));
    }
}
