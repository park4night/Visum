package com.horion.visum.UseCasesTests;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.os.SystemClock;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.internal.inject.InstrumentationContext;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import com.horion.visum.Data.ArgumentOperations.ArgumentRepository;
import com.horion.visum.Data.ArgumentVoteOperations.ArgumentVoteDao;
import com.horion.visum.Data.ArgumentVoteOperations.ArgumentVoteRepository;
import com.horion.visum.Data.Database;
import com.horion.visum.Data.Entities.ArgumentEntity;
import com.horion.visum.Data.Entities.ArgumentVoteEntity;
import com.horion.visum.Data.Entities.Vote;
import com.horion.visum.Domain.UseCases.AddVoteToArgumentUseCase;
import com.horion.visum.RoomDatabaseTests.FakeData;
import com.horion.visum.RoomDatabaseTests.FakeDatabase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(AndroidJUnit4ClassRunner.class)
public class AddVoteToArgumentUseCaseTest {

    private AddVoteToArgumentUseCase addVoteToArgumentUseCase;
    private ArgumentEntity argumentTest;
    private ArgumentVoteEntity voteTest;
    private ArgumentRepository argumentRepository;
    private ArgumentVoteRepository argumentVoteRepository;
    private Database fakedatabase;

    @Before
    public void setup(){
        Context context = ApplicationProvider.getApplicationContext();
        fakedatabase = new FakeDatabase().getFakeDatabase();
        argumentRepository = new ArgumentRepository(fakedatabase);
        argumentVoteRepository = new ArgumentVoteRepository(fakedatabase);
        argumentTest = FakeData.ARGUMENTS.get(1);
        voteTest = new ArgumentVoteEntity(8,2,true, "test", 3);
    }

    @Test
    public void is_vote_added_to_argument() throws InterruptedException {
        addVoteToArgumentUseCase = new AddVoteToArgumentUseCase(argumentVoteRepository, argumentRepository, argumentTest, voteTest);
        addVoteToArgumentUseCase.execute();
        ArgumentVoteDao argumentVoteDao = fakedatabase.argumentVoteDao();
        Thread.sleep(1500);
        List<ArgumentVoteEntity> argumentVoteEntities = argumentVoteDao.getVotesOfArgument(FakeDatabase.ARGUMENTS_IDS[1]);
        assertThat(argumentVoteEntities.get(0).getDate(), equalTo(voteTest.getDate()));
    }
}
