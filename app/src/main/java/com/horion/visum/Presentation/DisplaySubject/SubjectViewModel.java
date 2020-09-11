package com.horion.visum.Presentation.DisplaySubject;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.horion.visum.Data.ArgumentOperations.ArgumentRepository;
import com.horion.visum.Data.Database;
import com.horion.visum.Data.Entities.ArgumentEntity;
import com.horion.visum.Data.Entities.SubjectVoteEntity;
import com.horion.visum.Data.SubjectOperations.SubjectRepository;
import com.horion.visum.Data.SubjectVoteOperations.SubjectVoteRepository;
import com.horion.visum.Domain.Model.Subject;
import com.horion.visum.Domain.UseCases.AddVoteToSubjectUseCase;

import java.util.List;
import java.util.concurrent.Executors;

// TODO: 24/07/2020 faire un get instance des repos
public class SubjectViewModel extends AndroidViewModel {

    private ArgumentRepository argumentRepository;
    private SubjectRepository subjectRepository;
    private SubjectVoteRepository subjectVoteRepository;
    public Subject subject;

    public SubjectViewModel(@NonNull Application application) {
        super(application);
        argumentRepository = ArgumentRepository.getInstance(Database.getDatabase(application));
        subjectRepository = SubjectRepository.getInstance(Database.getDatabase(application));
        subjectVoteRepository = SubjectVoteRepository.getInstance(Database.getDatabase(application));
    }

    public LiveData<List<ArgumentEntity>> getArgumentBySubjectName(String name){
       return argumentRepository.getArgumentBySubjectName(name);
    }

    public void addVote(boolean isPositive){
        SubjectVoteEntity subjectVote = new SubjectVoteEntity(0,2, isPositive, "", 0);
        new AddVoteToSubjectUseCase(getApplication(), subject, subjectVote).execute();
    }

    public LiveData getPositiveVote(){
        LiveData subjectId = subjectRepository.getSubjectIdByNameLd(subject.getName());
        LiveData positiveVoteCount = Transformations.switchMap(subjectId, id -> subjectVoteRepository.getPositiveVoteCountOfSubject((long) id));
        return positiveVoteCount;
    }

    public LiveData getNegativeVote(){
        LiveData subjectId = subjectRepository.getSubjectIdByNameLd(subject.getName());
        LiveData negativeVoteCount = Transformations.switchMap(subjectId, id -> subjectVoteRepository.getNegativeVoteCountOfSubject((long) id));
        return negativeVoteCount;
    }
}
