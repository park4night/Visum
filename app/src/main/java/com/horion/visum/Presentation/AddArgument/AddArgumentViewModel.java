package com.horion.visum.Presentation.AddArgument;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.horion.visum.Data.ArgumentOperations.ArgumentRepository;
import com.horion.visum.Data.Database;
import com.horion.visum.Data.Entities.ArgumentEntity;
import com.horion.visum.Data.SubjectOperations.SubjectRepository;
import com.horion.visum.Domain.Model.Subject;
import com.horion.visum.Domain.UseCases.AddArgumentToSubjectUseCase;

public class AddArgumentViewModel extends AndroidViewModel {

    private ArgumentRepository argumentRepository;
    private SubjectRepository subjectRepository;

    public Subject subject;
    public ArgumentEntity argument;

    public AddArgumentViewModel(@NonNull Application application) {
        super(application);
        subjectRepository = SubjectRepository.getInstance(Database.getDatabase(application));
        argumentRepository = ArgumentRepository.getInstance(Database.getDatabase(application));
    }

    public void makeArgument(String name, String description){
        argument = new ArgumentEntity(name ,description);
    }

    public void addArgumentToSubject(){
        new AddArgumentToSubjectUseCase(getApplication(), subject, argument).execute();
    }
}
