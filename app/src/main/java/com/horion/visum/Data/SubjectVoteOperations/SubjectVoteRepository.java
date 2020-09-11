package com.horion.visum.Data.SubjectVoteOperations;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.horion.visum.Data.Database;
import com.horion.visum.Data.Entities.SubjectVoteEntity;
import com.horion.visum.Domain.IBaseRepository;

import java.util.List;

public class SubjectVoteRepository implements IBaseRepository {

    private static SubjectVoteRepository INSTANCE = null;

    private SubjectVoteDao subjectVoteDao;

    public static SubjectVoteRepository getInstance(Database database) {
        if (INSTANCE == null) {
            INSTANCE = new SubjectVoteRepository(database);
        }
        return INSTANCE;
    }

    public SubjectVoteRepository(Database database) {
        subjectVoteDao = database.subjectVoteDao();
    }

    public LiveData getPositiveVoteCountOfSubject(long id){
        return subjectVoteDao.getPositiveVotesCountOfSubject(id);

    }

    public  LiveData getNegativeVoteCountOfSubject(long id){
        return subjectVoteDao.getNegativeVotesCountOfSubject(id);
    }

    public LiveData<List<SubjectVoteEntity>> getSubjectVote(long id){
        return subjectVoteDao.getVotesOfSubject(id);
    }

//    public  LiveData getPositiveVoteOfSubject(long id){
//        return subjectVoteDao.getPositiveVotesCountOfSubject(id);
//    }

    @Override
    public void insert(Object item) {
        subjectVoteDao.insert((SubjectVoteEntity) item);
    }

    @Override
    public void replace(Object item) {

    }

    @Override
    public void upsert(Object item) {

    }

    @Override
    public void delete(String name) {

    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public LiveData<List> getAll() {
        return null;
    }

    @Override
    public void get(int id, GetItemCallback getItemCallback) {

    }

    public LiveData getVoteCount(long subjectId) {
        return subjectVoteDao.getVoteCount(subjectId);
    }
}
