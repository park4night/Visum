package com.horion.visum.Data.ArgumentOperations;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.horion.visum.Data.BaseDao;
import com.horion.visum.Data.Entities.ArgumentEntity;

import java.util.List;

@Dao
public interface ArgumentDao extends BaseDao<ArgumentEntity> {

    @Query("DELETE  FROM argument_table")
    void deleteAll();

    @Query("SELECT * from argument_table ORDER BY name ASC")
    LiveData<List<ArgumentEntity>> getAllArguments();

    @Query("SELECT * from argument_table WHERE id = :id")
    ArgumentEntity getArgumentById(long id);

    @Query("SELECT * FROM argument_table WHERE name = :argumentName")
    ArgumentEntity getArgumentByName(String argumentName);

    @Query("SELECT id FROM argument_table WHERE name = :argumentName")
    long getArgumentIdByName(String argumentName);

    @Query("SELECT argument_table.id, subject_id, isPredicate, argument_table.name, argument_table.description " +
            "FROM argument_table " +
            "INNER JOIN subject_table ON argument_table.subject_id = subject_table.id " +
            "WHERE subject_table.name = :subjectName")
    LiveData<List<ArgumentEntity>> getArgumentsBySubjectName(String subjectName);

    @Query("SELECT count(*) FROM argument_table")
    int count();
}
