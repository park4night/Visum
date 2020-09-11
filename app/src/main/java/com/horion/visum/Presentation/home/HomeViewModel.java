package com.horion.visum.Presentation.home;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import com.horion.visum.Data.Database;
import com.horion.visum.Data.Entities.SubjectVoteEntity;
import com.horion.visum.Data.SubjectOperations.SubjectRepository;
import com.horion.visum.Data.SubjectVoteOperations.SubjectVoteRepository;
import com.horion.visum.Domain.Model.Subject;
import com.horion.visum.Domain.UseCases.UpdateFromServerUseCase;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private SubjectRepository subjectRepository;
    private SubjectVoteRepository subjectVoteRepository;

    private MutableLiveData<String> title;
    private MutableLiveData subjects;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        subjectRepository = SubjectRepository.getInstance(Database.getDatabase(application));
        subjectVoteRepository = SubjectVoteRepository.getInstance(Database.getDatabase(application));
        title = new MutableLiveData<>();
        subjects = new MutableLiveData<>();
    }

    public LiveData<String> getTitle() {
        title.setValue("Sujets tendances");
        return title;
    }

    public LiveData<List<Subject>> getAllSubjects() {
        return subjectRepository.getAll();
    }

    public LiveData<List<Subject>> getPopularsSubjects(){
        return subjectRepository.getPopularsSubjects();
    }

    public void setupAppFromServer(){
        UpdateFromServerUseCase updateFromServerUseCase = new UpdateFromServerUseCase(getApplication());
        updateFromServerUseCase.execute();
    }

    public MediatorLiveData<List<Subject>> getSubjectsAndVotes(){
        MediatorLiveData subjectAndVotes = new MediatorLiveData<>();

        LiveData votes = Transformations.switchMap(getPopularsSubjects(), subjects -> getSubjectsVotes(subjects));

        subjectAndVotes.addSource(getPopularsSubjects(), new Observer() {
            @Override
            public void onChanged(Object o) {
                subjects.setValue(o);
                subjectAndVotes.setValue(o);
            }
        });

        subjectAndVotes.addSource(votes, new Observer() {
            @Override
            public void onChanged(Object o) {
                // TODO: 06/09/2020 gepopular here
                subjectAndVotes.setValue(subjects.getValue());
                Log.d("Logging", "One of the subject vote count has changed");
            }
        });

        return subjectAndVotes;
    }

    public MediatorLiveData<List<SubjectVoteEntity>> getSubjectsVotes(List<Subject> subjects){
        MediatorLiveData votesObserver = new MediatorLiveData();

        for (Subject subject : subjects) {
            votesObserver.addSource(subjectVoteRepository.getVoteCount(subject.getId()), new Observer() {
                @Override
                public void onChanged(Object o) {
                    votesObserver.setValue(o);
                    Log.d("Logging", "Number of votes of " + subject.getName() + " is " + o.toString());
                }
            });
        }
        return votesObserver;
    }
}