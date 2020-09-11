package com.horion.visum.Data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.horion.visum.Data.ArgumentOperations.ArgumentDao;
import com.horion.visum.Data.ArgumentVoteOperations.ArgumentVoteDao;
import com.horion.visum.Data.Entities.ArgumentEntity;
import com.horion.visum.Data.Entities.ArgumentVoteEntity;
import com.horion.visum.Data.Entities.SubjectEntity;
import com.horion.visum.Data.Entities.SubjectVoteEntity;
import com.horion.visum.Data.Entities.UserEntity;
import com.horion.visum.Data.SubjectOperations.SubjectDao;
import com.horion.visum.Data.SubjectVoteOperations.SubjectVoteDao;
import com.horion.visum.Data.UserOperations.UserDao;

@androidx.room.Database(entities = {ArgumentEntity.class, SubjectEntity.class, UserEntity.class, ArgumentVoteEntity.class, SubjectVoteEntity.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {

    public static Database INSTANCE;
    public static Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Database.class,
                            "database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract SubjectDao subjectDao();

    public abstract ArgumentDao argumentDao();

    public abstract UserDao userDao();

    public abstract ArgumentVoteDao argumentVoteDao();

    public abstract SubjectVoteDao subjectVoteDao();
}
