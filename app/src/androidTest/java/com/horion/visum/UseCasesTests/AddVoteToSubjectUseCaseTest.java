package com.horion.visum.UseCasesTests;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.horion.visum.Data.ArgumentOperations.ArgumentRepository;
import com.horion.visum.Data.ArgumentVoteOperations.ArgumentVoteDao;
import com.horion.visum.Data.ArgumentVoteOperations.ArgumentVoteRepository;
import com.horion.visum.Data.Database;
import com.horion.visum.Data.Entities.ArgumentEntity;
import com.horion.visum.Data.Entities.ArgumentVoteEntity;
import com.horion.visum.Data.Entities.SubjectVoteEntity;
import com.horion.visum.Data.SubjectOperations.SubjectRepository;
import com.horion.visum.Data.SubjectVoteOperations.SubjectVoteDao;
import com.horion.visum.Data.SubjectVoteOperations.SubjectVoteRepository;
import com.horion.visum.Domain.Mappers.SubjectMapper;
import com.horion.visum.Domain.Model.Subject;
import com.horion.visum.Domain.UseCases.AddVoteToArgumentUseCase;
import com.horion.visum.Domain.UseCases.AddVoteToSubjectUseCase;
import com.horion.visum.RoomDatabaseTests.FakeData;
import com.horion.visum.RoomDatabaseTests.FakeDatabase;
import com.horion.visum.TestUtils.MapperTestUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(AndroidJUnit4ClassRunner.class)
public class AddVoteToSubjectUseCaseTest {

    private AddVoteToSubjectUseCase addVoteToSubjectUseCase;
    private Subject subjectTest;
    private SubjectVoteEntity voteTest;
    private SubjectRepository subjectRepository;
    private SubjectVoteRepository subjectVoteRepository;
    private Database fakedatabase;

    @Before
    public void setup(){
        fakedatabase = new FakeDatabase().getFakeDatabase();
        subjectRepository = new SubjectRepository(fakedatabase);
        subjectVoteRepository = new SubjectVoteRepository(fakedatabase);
        subjectTest = MapperTestUtil.subjectEntityToSubject(FakeData.SUBJECTS.get(0), 0, 0);
        voteTest = new SubjectVoteEntity(8,2,true, "test", 3);
    }

    @Test
    public void is_vote_added_to_subject() throws InterruptedException {
        addVoteToSubjectUseCase = new AddVoteToSubjectUseCase(subjectRepository, subjectVoteRepository, subjectTest, voteTest);
        addVoteToSubjectUseCase.execute();
        SubjectVoteDao subjectVoteDao = fakedatabase.subjectVoteDao();
        Thread.sleep(1500);
        List<SubjectVoteEntity> subjectVoteEntities = subjectVoteDao.getVotesOfSubjectTesting(FakeDatabase.SUBJECTS_IDS[0]);
        assertThat(subjectVoteEntities.get(0).getDate(), equalTo(voteTest.getDate()));
    }
}
