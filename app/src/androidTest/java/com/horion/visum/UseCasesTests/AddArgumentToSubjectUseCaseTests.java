package com.horion.visum.UseCasesTests;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.horion.visum.Data.ArgumentOperations.ArgumentRepository;
import com.horion.visum.Data.Database;
import com.horion.visum.Data.Entities.ArgumentEntity;
import com.horion.visum.Data.Entities.SubjectEntity;
import com.horion.visum.Data.SubjectOperations.SubjectRepository;
import com.horion.visum.Domain.Mappers.SubjectMapper;
import com.horion.visum.Domain.Model.Subject;
import com.horion.visum.Domain.UseCases.AddArgumentToSubjectUseCase;
import com.horion.visum.R;
import com.horion.visum.TestUtils.LiveDataTestUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(AndroidJUnit4ClassRunner.class)
public class AddArgumentToSubjectUseCaseTests {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private Database database;
    private SubjectRepository subjectRepository;
    private ArgumentRepository argumentRepository;
    private ArgumentEntity argumentTest;
    private Subject subjectTest;
    private SubjectMapper subjectMapper;
    private AddArgumentToSubjectUseCase addArgumentToSubjectUseCase;

    @Before
    public void setUp(){

        Context context = ApplicationProvider.getApplicationContext();
        database = Room.inMemoryDatabaseBuilder(context, Database.class).build();
        argumentRepository = new ArgumentRepository(database);
        subjectRepository = new SubjectRepository(database);
        subjectMapper = new SubjectMapper(database);
        argumentTest = new ArgumentEntity(8, true,
                ApplicationProvider.getApplicationContext().getString(R.string.lorem_ipsum),
                ApplicationProvider.getApplicationContext().getString(R.string.lorem_ipsum),
                 8);

        subjectTest = new Subject(0,
                ApplicationProvider.getApplicationContext().getString(R.string.lorem_ipsum),
                ApplicationProvider.getApplicationContext().getString(R.string.lorem_ipsum),
                ApplicationProvider.getApplicationContext().getString(R.string.lorem_ipsum));
    }

    @Test
    public void is_argument_inserted_to_subject() throws InterruptedException {
        SubjectEntity subjectEntity = subjectMapper.objectToEntity(subjectTest);
        database.subjectDao().insert(subjectEntity);
        addArgumentToSubjectUseCase = new AddArgumentToSubjectUseCase(subjectRepository, argumentRepository, subjectTest, argumentTest);
        addArgumentToSubjectUseCase.execute();
        Thread.sleep(1500);
        List<ArgumentEntity> arguments = LiveDataTestUtil.getValue(database.argumentDao().getArgumentsBySubjectName(subjectTest.getName()));
        assertThat(arguments.get(0).getId(), equalTo(argumentTest.getId()));
    }
}
