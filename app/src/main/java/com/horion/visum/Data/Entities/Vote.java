package com.horion.visum.Data.Entities;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class Vote {

    @ColumnInfo(name = "vote_id")
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "user_vote_id")
    private long userId;

    private boolean isPositive;
    private String date;

    public Vote(long id, long userId, boolean isPositive, String date) {
        this.id = id;
        this.userId = userId;
        this.isPositive = isPositive;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isPositive() {
        return isPositive;
    }

    public void setPositive(boolean positive) {
        isPositive = positive;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
