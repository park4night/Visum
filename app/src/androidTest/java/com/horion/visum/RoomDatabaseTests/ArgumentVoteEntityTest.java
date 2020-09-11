package com.horion.visum.RoomDatabaseTests;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.horion.visum.Data.Database;
import com.horion.visum.Data.ArgumentVoteOperations.ArgumentVoteDao;
import com.horion.visum.Data.Entities.ArgumentVoteEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(AndroidJUnit4ClassRunner.class)
public class ArgumentVoteEntityTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private Database fakeDatabase;
    private Long autoGenerateId;
    private ArgumentVoteDao argumentVoteDao;

    @Before
    public void setup() {
        fakeDatabase = new FakeDatabase().getFakeDatabase();
        argumentVoteDao = fakeDatabase.argumentVoteDao();
        autoGenerateId = FakeDatabase.ARGUMENT_VOTE_IDS[0];
    }

    @Test
    public void is_count_of_argumentVote_correct() {
        int count = argumentVoteDao.getArgumentVoteCount(FakeDatabase.ARGUMENTS_IDS[0]);
        assertThat(count, equalTo(2));
    }

    @Test
    public void is_all_vote_of_argument_retrieve() {
        int count = argumentVoteDao.getArgumentVoteCount(FakeDatabase.ARGUMENTS_IDS[0]);
        List<ArgumentVoteEntity> votes = argumentVoteDao.getVotesOfArgument(FakeDatabase.ARGUMENTS_IDS[0]);
        assertThat(votes.size(), equalTo(count));
    }

    @Test
    public void is_argument_vote_count_valid() {
        List<ArgumentVoteEntity> votes = argumentVoteDao.getVotesOfArgument(FakeDatabase.ARGUMENTS_IDS[0]);
        int count = argumentVoteDao.getPositiveVotesCountOfArgument(FakeDatabase.ARGUMENTS_IDS[0]);
        assertThat(count, equalTo(votes.size()));
    }

    @After
    public void closeDb() throws IOException {
        fakeDatabase.close();
    }

}
