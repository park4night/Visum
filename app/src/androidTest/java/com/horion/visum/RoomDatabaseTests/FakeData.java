package com.horion.visum.RoomDatabaseTests;

import com.horion.visum.Data.Entities.ArgumentEntity;
import com.horion.visum.Data.Entities.ArgumentVoteEntity;
import com.horion.visum.Data.Entities.SubjectEntity;
import com.horion.visum.Data.Entities.SubjectVoteEntity;
import com.horion.visum.Data.Entities.UserEntity;
import com.horion.visum.Domain.Model.Subject;

import java.util.Arrays;
import java.util.List;

public class FakeData {

    public static final List<ArgumentEntity> ARGUMENTS = Arrays.asList(
            new ArgumentEntity(1, true,
                    "argumentName1",
                    "argumentDescription1",
                    8),

            new ArgumentEntity(2, true,
                    "argumentName2",
                    "argumentDescription2",
                    8),

            new ArgumentEntity(3, true,
                    "argumentName3",
                    "argumentDescription3",
                    8));


    public static final List<SubjectEntity> SUBJECTS = Arrays.asList(
            new SubjectEntity(8,
            "subjectName1",
            "subjectDescription1",
            "subjectTheme1"),
            new SubjectEntity(7,
                    "subjectName2",
                    "subjectDescription1",
                    "subjectTheme1"));

    public static final List<Subject> MODEL_SUBJECT = Arrays.asList(
            new Subject(8,
                    "subjectName11",
                    "subjectDescription1",
                    "subjectTheme1"),
            new Subject(8,
                    "subjectName12",
                    "subjectDescription1",
                    "subjectTheme1"));

    public static final List<ArgumentVoteEntity> ARGUMENT_VOTES = Arrays.asList(
            new ArgumentVoteEntity(5,
                    2,
                    true,
                    "voteDate1",
                    1),
            new ArgumentVoteEntity(4,
                    2,
                    true,
                    "voteDate2",
                    1));

    public static final List<SubjectVoteEntity> SUBJECT_VOTES = Arrays.asList(
            new SubjectVoteEntity(5,
                    2,
                    true,
                    "voteDate1",
                    7),
            new SubjectVoteEntity(4,
                    2,
                    true,
                    "voteDate2",
                    7));

    public static final List<UserEntity> USERS = Arrays.asList(
            new UserEntity(2,
                    "userUsername1",
                    "userPassword1",
                    "userEmail1")
    );
}
