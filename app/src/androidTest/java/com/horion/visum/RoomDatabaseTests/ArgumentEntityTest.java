package com.horion.visum.RoomDatabaseTests;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.horion.visum.Data.ArgumentOperations.ArgumentDao;
import com.horion.visum.Data.Database;
import com.horion.visum.Data.Entities.ArgumentEntity;
import com.horion.visum.Data.Entities.SubjectEntity;
import com.horion.visum.TestUtils.LiveDataTestUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(AndroidJUnit4ClassRunner.class)
public class ArgumentEntityTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private Database fakeDatabase;
    private ArgumentDao argumentDao;
    private ArgumentEntity fakeArgument;
    private SubjectEntity subjectTest;
    private Long autoGenerateId;

    @Before
    public void setup() {
        fakeDatabase = new FakeDatabase().getFakeDatabase();
        argumentDao = fakeDatabase.argumentDao();
        autoGenerateId = FakeDatabase.ARGUMENTS_IDS[0];
        subjectTest = FakeData.SUBJECTS.get(0);
        fakeArgument = FakeData.ARGUMENTS.get(0);
    }

    @Test
    public void is_all_argument_retrieve() throws InterruptedException {
        List<ArgumentEntity> argumentsRetrieve = LiveDataTestUtil.getValue(argumentDao.getAllArguments());
        assertThat(argumentsRetrieve.get(0).getName(), equalTo(fakeArgument.getName()));
    }

    @Test
    public void is_argument_by_name_retrieve() {
        ArgumentEntity argumentRetrieve = argumentDao.getArgumentByName(fakeArgument.getName());
        assertThat(argumentRetrieve.getName(), equalTo(fakeArgument.getName()));
    }

    @Test
    public void is_argument_by_id_retrieve() {
        ArgumentEntity argumentRetrieve = argumentDao.getArgumentById(autoGenerateId);
        assertThat(argumentRetrieve.getName(), equalTo(fakeArgument.getName()));
    }

    @Test
    public void is_argument_count_valid() {
        int argumentCount = argumentDao.count();
        Assert.assertEquals(argumentCount, FakeDatabase.ARGUMENTS_COUNT);
    }

    @Test
    public void is_argumentsBySubjectName_retrieve() throws InterruptedException {
        List<ArgumentEntity> arguments = LiveDataTestUtil.getValue(argumentDao.getArgumentsBySubjectName(subjectTest.getName()));
        assertThat(arguments.get(0).getId(), equalTo(fakeArgument.getId()));
    }

    @After
    public void closeDb() throws IOException {
        fakeDatabase.close();
    }

}
