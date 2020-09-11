package com.horion.visum.Data.UserOperations;

import androidx.room.Dao;

import com.horion.visum.Data.BaseDao;
import com.horion.visum.Data.Entities.UserEntity;

@Dao
public interface UserDao extends BaseDao<UserEntity> {
}
