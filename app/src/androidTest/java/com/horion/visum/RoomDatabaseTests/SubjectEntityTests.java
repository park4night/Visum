package com.horion.visum.RoomDatabaseTests;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.horion.visum.Data.ArgumentOperations.ArgumentDao;
import com.horion.visum.Data.Database;
import com.horion.visum.Data.Entities.ArgumentEntity;
import com.horion.visum.Data.Entities.SubjectEntity;
import com.horion.visum.Data.SubjectOperations.SubjectDao;
import com.horion.visum.Domain.SubjectWithArguments;
import com.horion.visum.TestUtils.LiveDataTestUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.List;

@RunWith(AndroidJUnit4ClassRunner.class)
public class SubjectEntityTests {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private Database fakeDatabase;
    private ArgumentDao argumentDao;
    private SubjectDao subjectDao;
    private ArgumentEntity fakeArgument;
    private SubjectEntity subjectTest;
    private Long autoGenerateId;
    
    @Before
    public void setup() {
        fakeDatabase = new FakeDatabase().getFakeDatabase();
        argumentDao = fakeDatabase.argumentDao();
        subjectDao = fakeDatabase.subjectDao();
        autoGenerateId = FakeDatabase.SUBJECTS_IDS[0];
        subjectTest = FakeData.SUBJECTS.get(0);
        fakeArgument = FakeData.ARGUMENTS.get(0);
    }

    @Test
    public void is_SubjectId_by_subjectName_retrieve() {
        long id = subjectDao.getSubjectIdByName(subjectTest.getName());
        assertThat(id, equalTo(subjectTest.getId()));
    }

    @Test
    public void is_subject_by_name_retrieve() {
        SubjectEntity subjectRetrieve = subjectDao.getSubjectByName(subjectTest.getName());
        assertThat(subjectRetrieve.getName(), equalTo(subjectTest.getName()));
    }

    @Test
    public void is_all_subject_retrieve() throws InterruptedException {
        List<SubjectEntity> subjectsRetrieve = LiveDataTestUtil.getValue(subjectDao.getAllSubjects());
        assertThat(subjectsRetrieve.get(0).getName(), equalTo(subjectTest.getName()));
    }

    @Test
    public void is_subject_by_id_retrieve() {
        SubjectEntity subjectRetrieve = subjectDao.getSubjectByIdTest(autoGenerateId);
        assertThat(subjectRetrieve.getName(), equalTo(subjectTest.getName()));
    }

    @Test
    public void is_subject_count_valid() {
        int subjectCount = subjectDao.count();
        Assert.assertEquals(subjectCount, FakeDatabase.SUBJECTS_COUNT);
    }

    @Test
    public void is_subjectWithArgument_by_name_retrieve() {
        SubjectWithArguments subjectWithArgumentsRetrieve = subjectDao.getSubjectWithArgumentsByname(subjectTest.getName());
        assertThat(subjectWithArgumentsRetrieve.arguments.get(0).getId(), equalTo(FakeData.ARGUMENTS.get(0).getId()));
    }

    @After
    public void closeDb() throws IOException {
        fakeDatabase.close();
    }
    
}
