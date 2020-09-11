package com.horion.visum.Data.SubjectVoteOperations;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.horion.visum.Data.BaseDao;
import com.horion.visum.Data.Entities.SubjectVoteEntity;

import java.util.List;

@Dao
public interface SubjectVoteDao extends BaseDao<SubjectVoteEntity> {

    @Query("SELECT * FROM subject_vote_table WHERE isPositive = 1 AND subject_id = :subjectId")
    List<SubjectVoteEntity> getPositiveVotesOfArgument(long subjectId);

    @Query("SELECT * FROM subject_vote_table WHERE isPositive = 0 AND subject_id = :subjectId")
    List<SubjectVoteEntity> getNegativeVotesOfArgument(long subjectId);

    @Query("SELECT * FROM subject_vote_table WHERE isPositive = 0 AND subject_id = :subjectId")
    LiveData<List<SubjectVoteEntity>> getVotesOfSubject(long subjectId);

    @Query("SELECT * FROM subject_vote_table WHERE subject_id = :subjectId")
    List<SubjectVoteEntity> getVotesOfSubjectTesting(long subjectId);

    @Query("SELECT COUNT(*) FROM subject_vote_table WHERE isPositive = 1 AND subject_id = :subjectId")
    LiveData<Integer> getPositiveVotesCountOfSubject(long subjectId);

    @Query("SELECT COUNT(*) FROM subject_vote_table WHERE isPositive = 0 AND subject_id = :subjectId")
    LiveData<Integer> getNegativeVotesCountOfSubject(long subjectId);

    @Query("SELECT COUNT(*) FROM subject_vote_table WHERE subject_id = :subjectId")
    LiveData<Integer> getVoteCount(long subjectId);
}
