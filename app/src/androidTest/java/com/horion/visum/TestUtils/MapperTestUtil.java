package com.horion.visum.TestUtils;

import com.horion.visum.Data.Entities.SubjectEntity;
import com.horion.visum.Domain.Model.Subject;

public class MapperTestUtil {

    public static Subject subjectEntityToSubject(SubjectEntity entity, int positiveVote, int negativeVote){
        Subject subject = new Subject(entity.getId(), entity.getName(), entity.getDescription(), entity.getTheme(), positiveVote, negativeVote);
        return subject;
    }
}
