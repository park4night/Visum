package com.horion.visum.Data.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "subject_table", indices = {@Index(value =
        {"name"}, unique = true)})
public class SubjectEntity{

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String description;
    private String theme;

    public SubjectEntity(long id, String name, String description, String theme) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.theme = theme;
    }

    @Ignore
    public SubjectEntity(String name, String description) {
        this.id = 0;
        this.name = name;
        this.description = description;
    }

    @Ignore
    public SubjectEntity(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    protected SubjectEntity(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        theme = in.readString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
