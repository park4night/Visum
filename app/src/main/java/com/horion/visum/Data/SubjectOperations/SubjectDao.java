package com.horion.visum.Data.SubjectOperations;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.horion.visum.Data.BaseDao;
import com.horion.visum.Data.Entities.SubjectEntity;
import com.horion.visum.Domain.SubjectWithArguments;

import java.util.List;

@Dao
public interface SubjectDao extends BaseDao<SubjectEntity> {

    @Query("DELETE  FROM subject_table")
    void deleteAll();

    @Query("SELECT * from subject_table ORDER BY name ASC")
    LiveData<List<SubjectEntity>> getAllSubjects();

    @Query("SELECT id FROM subject_table WHERE name = :subjectName ")
    LiveData<Long> getSubjectIdByNameLd(String subjectName);

    // TODO: 08/07/2020 construct the search for subject that have the highest total vote number 
    @Query("SELECT * from subject_table ORDER BY name ASC")
    LiveData<List<SubjectEntity>> getPopularsSubjects();

    @Query("SELECT * FROM subject_table WHERE name = :subjectName")
    SubjectEntity getSubjectByName(String subjectName);

    @Query("SELECT * from subject_table WHERE id = :id")
    SubjectEntity getSubjectByIdTest(long id);

    @Query("SELECT * from subject_table WHERE id = :id")
    LiveData<SubjectEntity> getSubjectById(long id);

    @Transaction
    @Query("SELECT * from subject_table WHERE name = :name")
    SubjectWithArguments getSubjectWithArgumentsByname(String name);

    @Query("SELECT count(*) FROM subject_table")
    int count();

    //For testing exclusively
    @Query("SELECT * from subject_table ORDER BY name ASC")
    List<SubjectEntity> getAllSubjectsList();
    //For testing exclusively
    @Query("SELECT id FROM subject_table WHERE name = :subjectName ")
    long getSubjectIdByName(String subjectName);

}
