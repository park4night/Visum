package com.horion.visum.Data.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "argument_table",indices = @Index(value = {"subject_id"}), foreignKeys =
@ForeignKey(entity = SubjectEntity.class, parentColumns = "id", childColumns = "subject_id", onDelete = ForeignKey.CASCADE))

public class ArgumentEntity implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "subject_id")
    private long subjectId;
    private boolean isPredicate;
    private String name;
    private String description;
    
    public ArgumentEntity(long id, boolean isPredicate, String name, String description, long subjectId) {
        this.id = id;
        this.isPredicate = isPredicate;
        this.name = name;
        this.description = description;
        this.subjectId = subjectId;
    }

    @Ignore
    public ArgumentEntity (String name, String argument){
        this.id = 0;
        this.isPredicate = true;
        this.name = name;
        this.description = argument;
        this.subjectId = 0;
    }

    protected ArgumentEntity(Parcel in) {
        id = in.readLong();
        subjectId = in.readLong();
        isPredicate = in.readByte() != 0;
        name = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(subjectId);
        dest.writeByte((byte) (isPredicate ? 1 : 0));
        dest.writeString(name);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ArgumentEntity> CREATOR = new Creator<ArgumentEntity>() {
        @Override
        public ArgumentEntity createFromParcel(Parcel in) {
            return new ArgumentEntity(in);
        }

        @Override
        public ArgumentEntity[] newArray(int size) {
            return new ArgumentEntity[size];
        }
    };

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isPredicate() {
        return isPredicate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPredicate(boolean predicate) {
        isPredicate = predicate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArgument() {
        return description;
    }

    public void setArgument(String argument) {
        this.description = argument;
    }
}

