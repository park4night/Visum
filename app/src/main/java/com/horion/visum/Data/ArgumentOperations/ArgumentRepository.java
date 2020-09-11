package com.horion.visum.Data.ArgumentOperations;

import androidx.lifecycle.LiveData;

import com.horion.visum.Data.Database;
import com.horion.visum.Data.Entities.ArgumentEntity;
import com.horion.visum.Domain.IBaseRepository;

import java.util.List;
import java.util.concurrent.Executors;

public class ArgumentRepository implements IBaseRepository{

    private static ArgumentRepository INSTANCE = null;

    ArgumentDao argumentDao;

    public static ArgumentRepository getInstance(Database database) {
        if (INSTANCE == null) {
            INSTANCE = new ArgumentRepository(database);
        }
        return INSTANCE;
    }

    public ArgumentRepository(Database database) {
        argumentDao = database.argumentDao();
    }

    @Override
    public void insert(Object item) {
        Executors.newSingleThreadExecutor().execute(() -> {
            argumentDao.insert((ArgumentEntity) item);
        });
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

    public LiveData<List<ArgumentEntity>> getArgumentBySubjectName(String subjectName){
        return  argumentDao.getArgumentsBySubjectName(subjectName);
    }

    public ArgumentEntity getArgumentByName(String argumentName){
        return argumentDao.getArgumentByName(argumentName);
    }

    public long getArgumentIdByName(String argumentName){
        return argumentDao.getArgumentIdByName(argumentName);
    }
}