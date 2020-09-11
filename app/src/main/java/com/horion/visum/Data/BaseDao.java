package com.horion.visum.Data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import java.util.List;

//Base DAO qui implemente les fonctions communes aux autres DAO dans le cas ou l'application
// utiliserait plusieurs entitées
@Dao
public interface BaseDao<Object>  {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    long insert(Object object);

    @Insert(onConflict = OnConflictStrategy.FAIL)
    long[] insertAll(List<Object> objects);

    @Update(onConflict = OnConflictStrategy.FAIL)
    void update(Object object);

    @Delete
    void delete(Object object);

}
