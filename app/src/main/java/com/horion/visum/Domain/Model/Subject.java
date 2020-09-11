package com.horion.visum.Domain.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Subject implements Parcelable {

    private long id;
    private String name;
    private String description;
    private String theme;

    private int positiveVote;
    private int negativeVote;

    public Subject(long id, String name, String description, String theme, int positiveVote, int negativeVote) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.theme = theme;
        this.positiveVote = positiveVote;
        this.negativeVote = negativeVote;
    }

    public Subject(long id, String name, String description, String theme) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.theme = theme;
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

    public int getPositiveVote() {
        return positiveVote;
    }

    public void setPositiveVote(int positiveVote) {
        this.positiveVote = positiveVote;
    }

    public int getNegativeVote() {
        return negativeVote;
    }

    public void setNegativeVote(int negativeVote) {
        this.negativeVote = negativeVote;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.theme);
        dest.writeInt(this.positiveVote);
        dest.writeInt(this.negativeVote);
    }

    protected Subject(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.description = in.readString();
        this.theme = in.readString();
        this.positiveVote = in.readInt();
        this.negativeVote = in.readInt();
    }

    public static final Parcelable.Creator<Subject> CREATOR = new Parcelable.Creator<Subject>() {
        @Override
        public Subject createFromParcel(Parcel source) {
            return new Subject(source);
        }

        @Override
        public Subject[] newArray(int size) {
            return new Subject[size];
        }
    };
}
