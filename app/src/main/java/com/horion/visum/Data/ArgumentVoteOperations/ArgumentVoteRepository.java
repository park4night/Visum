package com.horion.visum.Data.ArgumentVoteOperations;

import androidx.lifecycle.LiveData;

import com.horion.visum.Data.Database;
import com.horion.visum.Data.Entities.ArgumentVoteEntity;
import com.horion.visum.Domain.IBaseRepository;

import java.util.List;

public class ArgumentVoteRepository implements IBaseRepository {

    private static ArgumentVoteRepository INSTANCE = null;

    private ArgumentVoteDao argumentVoteDao;

    public static ArgumentVoteRepository getInstance(Database database) {
        if (INSTANCE == null) {
            INSTANCE = new ArgumentVoteRepository(database);
        }
        return INSTANCE;
    }

    public ArgumentVoteRepository(Database database) {
        argumentVoteDao = database.argumentVoteDao();
    }

    @Override
    public void insert(Object item) {
        argumentVoteDao.insert((ArgumentVoteEntity) item);
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
}
