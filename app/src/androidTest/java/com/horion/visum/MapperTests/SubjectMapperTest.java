package com.horion.visum.MapperTests;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.horion.visum.Data.Database;
import com.horion.visum.Data.Entities.SubjectEntity;
import com.horion.visum.Domain.Mappers.SubjectMapper;
import com.horion.visum.Domain.Model.Subject;
import com.horion.visum.RoomDatabaseTests.FakeData;
import com.horion.visum.RoomDatabaseTests.FakeDatabase;
import com.horion.visum.TestUtils.LiveDataTestUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


@RunWith(AndroidJUnit4ClassRunner.class)
public class SubjectMapperTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    private SubjectMapper subjectMapper;
    private Subject subjectTest;
    private List<Subject> subjectsTest;
    private Database fakeDatabase;

    @Before
    public void setup() {
        fakeDatabase = new FakeDatabase().getFakeDatabase();
        subjectMapper = new SubjectMapper(fakeDatabase);
        subjectTest = FakeData.MODEL_SUBJECT.get(0);
        subjectsTest = FakeData.MODEL_SUBJECT;
    }

    @Test
    public void is_subject_to_subjectEntity_mapped() {
        SubjectEntity entity = subjectMapper.objectToEntity(subjectTest);
        assertThat(entity.getName(), equalTo(subjectTest.getName()));
    }

    @Test
    public void is_subjectsList_to_subjectEntityList_mapped() {
        List<SubjectEntity> entities = subjectMapper.objectLisToEntityList(subjectsTest);
        assertThat(entities.size(), equalTo(subjectsTest.size()));
    }

    @Test
    public void is_subjectEntity_to_subject_mapped() throws InterruptedException {
        LiveData<SubjectEntity> subjectEntityLiveData = fakeDatabase.subjectDao().getSubjectById(FakeDatabase.SUBJECTS_IDS[0]);
        LiveData<Subject> subjectLiveData = subjectMapper.entityToObject(subjectEntityLiveData);
        Subject subject = LiveDataTestUtil.getValue(subjectLiveData);
        assertThat(subject.getName(), equalTo(FakeData.SUBJECTS.get(0).getName()));
    }

    @Test
    public void is_subjectEntityList_to_subjectList_mapped() throws InterruptedException {
        LiveData<List<SubjectEntity>> listLiveData = fakeDatabase.subjectDao().getAllSubjects();
        LiveData<List<Subject>> subjectLiveData = subjectMapper.entityListToObjectList(listLiveData);
        List<Subject> subjects = LiveDataTestUtil.getValue(subjectLiveData);
        assertThat(subjects.size(), equalTo(FakeDatabase.SUBJECTS_COUNT));
    }
}
