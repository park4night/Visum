package com.horion.visum.Data.ArgumentVoteOperations;

import androidx.room.Dao;
import androidx.room.Query;

import com.horion.visum.Data.BaseDao;
import com.horion.visum.Data.Entities.ArgumentVoteEntity;

import java.util.List;

@Dao
public interface ArgumentVoteDao extends BaseDao<ArgumentVoteEntity> {

    @Query("SELECT * FROM argument_vote_table WHERE isPositive = 1 AND argument_id = :argumentId")
    List<ArgumentVoteEntity> getPositiveVotesOfArgument(long argumentId);

    @Query("SELECT * FROM argument_vote_table WHERE isPositive = 0 AND argument_id = :argumentId")
    List<ArgumentVoteEntity> getNegativeVotesOfArgument(long argumentId);

    @Query("SELECT * FROM argument_vote_table WHERE argument_id = :argumentId")
    List<ArgumentVoteEntity> getVotesOfArgument(long argumentId);

    @Query("SELECT COUNT(*) FROM argument_vote_table WHERE isPositive = 1 AND argument_id = :argumentId")
    int getPositiveVotesCountOfArgument(long argumentId);

    @Query("SELECT COUNT(*) FROM argument_vote_table WHERE isPositive = 0 AND argument_id = :argumentId")
    int getNegativeVotesCountOfArgument(long argumentId);

    @Query("SELECT COUNT(*) FROM argument_vote_table WHERE argument_id = :argumentId")
    int getArgumentVoteCount(long argumentId);

}
