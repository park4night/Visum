package com.horion.visum.Data.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(tableName = "argument_vote_table", indices = @Index(value = "argument_id"), foreignKeys =
@ForeignKey(entity = ArgumentEntity.class, parentColumns = "id", childColumns = "argument_id", onDelete = ForeignKey.CASCADE))
public class ArgumentVoteEntity extends Vote {

    @ColumnInfo(name = "argument_id")
    private long argumentId;

    public ArgumentVoteEntity(long id, long userId, boolean isPositive, String date, long argumentId) {
        super(id, userId, isPositive, date);
        this.argumentId = argumentId;
    }

    public long getArgumentId() {
        return argumentId;
    }

    public void setArgumentId(long argumentId) {
        this.argumentId = argumentId;
    }
}
