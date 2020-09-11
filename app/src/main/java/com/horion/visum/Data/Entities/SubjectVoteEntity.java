package com.horion.visum.Data.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(tableName = "subject_vote_table", indices = @Index(value = "subject_id"), foreignKeys =
@ForeignKey(entity = SubjectEntity.class, parentColumns = "id", childColumns = "subject_id", onDelete = ForeignKey.CASCADE))
public class SubjectVoteEntity extends Vote {

    @ColumnInfo(name = "subject_id")
    private long subjectId;

    public SubjectVoteEntity(long id, long userId, boolean isPositive, String date, long subjectId) {
        super(id, userId, isPositive, date);
        this.subjectId = subjectId;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

}
