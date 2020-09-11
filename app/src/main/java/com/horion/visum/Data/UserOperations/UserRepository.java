package com.horion.visum.Data.UserOperations;

import androidx.lifecycle.LiveData;

import com.horion.visum.Data.Database;
import com.horion.visum.Domain.IBaseRepository;

import java.util.List;

public class UserRepository implements IBaseRepository {

    private static  UserRepository INSTANCE = null;

    private UserDao userDao;

    public static UserRepository getInstance(Database database) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(database);
        }
        return INSTANCE;
    }

    public UserRepository(Database database) {
        userDao = database.userDao();
    }

    @Override
    public void insert(Object item) {

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
