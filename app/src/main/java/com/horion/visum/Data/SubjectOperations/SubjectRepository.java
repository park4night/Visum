package com.horion.visum.Data.SubjectOperations;

import androidx.lifecycle.LiveData;

import com.horion.visum.Data.ArgumentOperations.ArgumentRepository;
import com.horion.visum.Data.Database;
import com.horion.visum.Data.Entities.SubjectEntity;
import com.horion.visum.Domain.IBaseRepository;
import com.horion.visum.Domain.Mappers.SubjectMapper;
import com.horion.visum.Domain.Model.Subject;
import com.horion.visum.Domain.SubjectWithArguments;

import java.util.List;
import java.util.concurrent.Executors;

public class SubjectRepository implements IBaseRepository {

    private static SubjectRepository INSTANCE = null;

    private SubjectDao subjectDao;
    private SubjectMapper subjectMapper;

    public static SubjectRepository getInstance(Database database) {
        if (INSTANCE == null) {
            INSTANCE = new SubjectRepository(database);
        }
        return INSTANCE;
    }

    public SubjectRepository(Database database) {
        subjectDao = database.subjectDao();
        subjectMapper = new SubjectMapper(database);
    }

    @Override
    public void insert(Object item) {
        Executors.newSingleThreadExecutor().execute(() -> {
            subjectDao.insert(subjectMapper.objectToEntity((Subject) item));
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
    public LiveData<List<Subject>> getAll() {
        return subjectMapper.entityListToObjectList(subjectDao.getAllSubjects());
    }

    @Override
    public void get(int id, GetItemCallback getItemCallback) {

    }

    public SubjectWithArguments getSubjectWithArgumentsByName(String name) {
        return subjectDao.getSubjectWithArgumentsByname(name);
    }

    public LiveData<List<Subject>> getPopularsSubjects() {
        // TODO: 05/09/2020 Ce livedata doit etre cappable de trigger quand on ajoute un vote 
        return subjectMapper.entityListToObjectList(subjectDao.getPopularsSubjects());
    }

    public long getSubjectIdByName(String subjectName) {
        return subjectDao.getSubjectIdByName(subjectName);
    }

    public LiveData getSubjectIdByNameLd(String subjectName) {
        return subjectDao.getSubjectIdByNameLd(subjectName);
    }
}
