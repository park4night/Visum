package com.horion.visum.Presentation.AddSubject;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.horion.visum.Data.Database;
import com.horion.visum.Data.SubjectOperations.SubjectRepository;
import com.horion.visum.Domain.Model.Subject;

public class AddSubjectViewModel extends AndroidViewModel {

    public Subject subject;

    private SubjectRepository subjectRepository;

    public AddSubjectViewModel(@NonNull Application application) {
        super(application);
        subjectRepository = SubjectRepository.getInstance(Database.getDatabase(application));
    }

    public void makeSubject(String name, String description, String theme){
        subject = new Subject(0, name, description, theme);
    }

    public void addSubject(){
        subjectRepository.insert(subject);
    }

}
