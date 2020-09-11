package com.horion.visum.Domain;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.horion.visum.Data.Entities.ArgumentEntity;
import com.horion.visum.Data.Entities.SubjectEntity;

import java.util.List;

public class SubjectWithArguments {
    @Embedded
    public SubjectEntity subject;
    @Relation(
            parentColumn = "id",
            entityColumn = "subject_id"
    )
    public List<ArgumentEntity> arguments;
}
