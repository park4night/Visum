package com.horion.visum.RoomDatabaseTests;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.horion.visum.Data.ArgumentOperations.ArgumentDao;
import com.horion.visum.Data.Database;
import com.horion.visum.Data.SubjectOperations.SubjectDao;
import com.horion.visum.Data.SubjectVoteOperations.SubjectVoteDao;
import com.horion.visum.Data.UserOperations.UserDao;
import com.horion.visum.Data.ArgumentVoteOperations.ArgumentVoteDao;
import static com.horion.visum.RoomDatabaseTests.FakeData.ARGUMENTS;
import static com.horion.visum.RoomDatabaseTests.FakeData.SUBJECTS;
import static com.horion.visum.RoomDatabaseTests.FakeData.ARGUMENT_VOTES;
import static com.horion.visum.RoomDatabaseTests.FakeData.SUBJECT_VOTES;
import static com.horion.visum.RoomDatabaseTests.FakeData.USERS;


public class FakeDatabase {
    
    public static long ARGUMENTS_IDS[];
    public static long SUBJECTS_IDS[];
    public static long ARGUMENT_VOTE_IDS[];
    public static long SUBJECT_VOTE_IDS[];
    public static long USER_IDS[];

    public static int SUBJECTS_COUNT;
    public static int ARGUMENTS_COUNT;
    public static int ARGUMENT_VOTE_COUNT;
    public static int SUBJECT_VOTE_COUNT;
    public static int USER_COUNT;

    private Database fakeDatabase;
    private ArgumentDao argumentDao;
    private SubjectDao subjectDao;
    private ArgumentVoteDao argumentVoteDao;
    private SubjectVoteDao subjectVoteDao;
    private UserDao userDao;
    
    public void init(){
        Context context = ApplicationProvider.getApplicationContext();
        fakeDatabase = Room.inMemoryDatabaseBuilder(context, Database.class).build();
        argumentDao = fakeDatabase.argumentDao();
        subjectDao = fakeDatabase.subjectDao();
        argumentVoteDao = fakeDatabase.argumentVoteDao();
        subjectVoteDao = fakeDatabase.subjectVoteDao();
        userDao = fakeDatabase.userDao();

       SUBJECTS_IDS = subjectDao.insertAll(SUBJECTS);
       ARGUMENTS_IDS = argumentDao.insertAll(ARGUMENTS);
       USER_IDS = userDao.insertAll(USERS);
       ARGUMENT_VOTE_IDS = argumentVoteDao.insertAll(ARGUMENT_VOTES);
       SUBJECT_VOTE_IDS = subjectVoteDao.insertAll(SUBJECT_VOTES);

       ARGUMENT_VOTE_COUNT = ARGUMENT_VOTES.size();
       SUBJECT_VOTE_COUNT = SUBJECT_VOTES.size();
       ARGUMENTS_COUNT = ARGUMENTS.size();
       SUBJECTS_COUNT  = SUBJECTS.size();
       USER_COUNT = USERS.size();
    }
    
    public Database getFakeDatabase(){
        this.init();
        return fakeDatabase;
    }
}
