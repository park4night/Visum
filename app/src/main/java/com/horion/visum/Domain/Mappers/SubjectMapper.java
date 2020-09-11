package com.horion.visum.Domain.Mappers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import com.horion.visum.Data.Database;
import com.horion.visum.Data.Entities.SubjectEntity;
import com.horion.visum.Data.SubjectVoteOperations.SubjectVoteRepository;
import com.horion.visum.Domain.Model.Subject;
import com.horion.visum.Toolbox.PairMediatorLiveData;

import java.util.ArrayList;
import java.util.List;

public class SubjectMapper implements IMapper<Subject, SubjectEntity> {

    private int positiveVotee = 0;
    private int negativeVotee = 0;
    private SubjectVoteRepository subjectVoteRepository;
    LiveData<List<Subject>> subjects;

    public SubjectMapper(Database database) {
        subjectVoteRepository = SubjectVoteRepository.getInstance(database);
    }

    // TODO: 06/09/2020 listen also to change of vote number of subject and redo the conversion when it changed
    @Override
    public LiveData<List<Subject>> entityListToObjectList(LiveData<List<SubjectEntity>> entitiesLiveData) {
        ////////
        subjects = Transformations.switchMap(entitiesLiveData, subjectEntities -> toSubjectList(subjectEntities));
        return subjects;
    }

    private LiveData<List<Subject>> toSubjectList(List<SubjectEntity> subjectEntities) {

        LiveData<List<Subject>> subjectsData = new MutableLiveData<>();
        List<Subject> subjects = new ArrayList<>();
        for (SubjectEntity subjectEntity : subjectEntities) {
            LiveData positiveVote = subjectVoteRepository.getPositiveVoteCountOfSubject(subjectEntity.getId());
            LiveData negativeVote = subjectVoteRepository.getNegativeVoteCountOfSubject(subjectEntity.getId());

            PairMediatorLiveData voteTrigger = new PairMediatorLiveData(positiveVote, negativeVote);
            subjectsData = Transformations.switchMap(voteTrigger, value -> toSubject(value.first, value.second, subjectEntity, subjects));
        }
        return subjectsData;
    }

    private LiveData<List<Subject>> toSubject(Integer first, Integer second, SubjectEntity entity, List<Subject> subjects) {
        MutableLiveData test = new MutableLiveData();
        subjects.add(new Subject(0, entity.getName(), entity.getDescription(), entity.getTheme(), first, second));
        test.setValue(subjects);
         return test;
    }

    private MediatorLiveData<Object> getMediatorVote(List<SubjectEntity> subjectEntities) {
        MediatorLiveData voteListener = new MediatorLiveData();

        for (SubjectEntity subjectEntity : subjectEntities) {
            voteListener.addSource(observeVote(subjectEntity), new Observer() {
                @Override
                public void onChanged(Object o) {
                    voteListener.setValue(o);
                }
            });
        }
        return voteListener;
    }

    private List<Subject> createSubjectsList(List<SubjectEntity> subjectEntities) {
        MediatorLiveData subject = new MediatorLiveData();
        List<Subject> subjects = new ArrayList<>();

        for (SubjectEntity subjectEntity : subjectEntities) {
            subject = observeVote(subjectEntity);
            subjects.add((Subject) subject.getValue());
        }

        return subjects;
    }

    @Override
    public LiveData<Subject> entityToObject(LiveData<SubjectEntity> entityLiveData) {
        LiveData subject = Transformations.map(entityLiveData, subjectEntity -> createSubject((subjectEntity)));
        return subject;
    }

    private Object createSubject(SubjectEntity subjectEntity) {
        MediatorLiveData subject = new MediatorLiveData();
        subject = observeVote(subjectEntity);
        return subject.getValue();
    }

    private MediatorLiveData observeVote(SubjectEntity entity) {
        MediatorLiveData subject = new MediatorLiveData();
        LiveData<Integer> positiveVote = subjectVoteRepository.getPositiveVoteCountOfSubject(entity.getId());
        LiveData<Integer> negativeVote = subjectVoteRepository.getNegativeVoteCountOfSubject(entity.getId());

        updateSubject(subject, entity);

        subject.addSource(positiveVote, count -> {
            positiveVotee = (Integer) count;
            updateSubject(subject, entity);
        });

        subject.addSource(negativeVote, count -> {
            negativeVotee = (Integer) count;
            updateSubject(subject, entity);
        });

        return subject;
    }

    private void updateSubject(MediatorLiveData subject, SubjectEntity entity) {
        subject.setValue(new Subject(entity.getId(), entity.getName(), entity.getDescription(), entity.getTheme(), positiveVotee, negativeVotee));
    }

    @Override
    public SubjectEntity objectToEntity(Subject modelObject) {
        SubjectEntity subjectEntity = new SubjectEntity(modelObject.getId(), modelObject.getName(), modelObject.getDescription(), modelObject.getTheme());
        return subjectEntity;
    }

    @Override
    public List<SubjectEntity> objectLisToEntityList(List<Subject> modelObjects) {
        List<SubjectEntity> subjectEntities = new ArrayList<>();
        for (int i = 0; i < modelObjects.size(); i++) {
            Subject subject = modelObjects.get(i);
            SubjectEntity subjectEntity = new SubjectEntity(subject.getId(), subject.getName(), subject.getDescription(), subject.getTheme());
            subjectEntities.add(subjectEntity);
        }
        return subjectEntities;
    }
}
