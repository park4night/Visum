package com.horion.visum.Data.JsonSource;

import com.horion.visum.Data.Entities.SubjectEntity;

import java.util.List;

public class JsonResponse {

    public List<SubjectEntity> subjectEntities;

    public JsonResponse(List<SubjectEntity> subjectEntities) {
        this.subjectEntities = subjectEntities;
    }

    public List<SubjectEntity> getEntites(){
        return subjectEntities;
    }
}
