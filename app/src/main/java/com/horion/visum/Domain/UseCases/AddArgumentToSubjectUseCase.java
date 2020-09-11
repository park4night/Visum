package com.horion.visum.Domain.UseCases;

import android.content.Context;

import com.horion.visum.Data.ArgumentOperations.ArgumentRepository;
import com.horion.visum.Data.Database;
import com.horion.visum.Data.Entities.ArgumentEntity;
import com.horion.visum.Data.SubjectOperations.SubjectRepository;
import com.horion.visum.Domain.Model.Subject;

import java.util.concurrent.Executors;

public class AddArgumentToSubjectUseCase {

    private ArgumentRepository argumentRepository;
    private SubjectRepository subjectRepository;
    private Subject subject;
    private ArgumentEntity argument;

    public AddArgumentToSubjectUseCase(Context context, Subject subject, ArgumentEntity argument) {
        this.argumentRepository = ArgumentRepository.getInstance(Database.getDatabase(context));
        this.subjectRepository = SubjectRepository.getInstance(Database.getDatabase(context));
        this.subject = subject;
        this.argument = argument;
    }

    //For testing
    public AddArgumentToSubjectUseCase(SubjectRepository subjectRepository, ArgumentRepository argumentRepository, Subject subject, ArgumentEntity argumentEntity){
        this.argumentRepository = argumentRepository;
        this.subjectRepository = subjectRepository;
        this.subject = subject;
        this.argument = argumentEntity;
    }

    public void execute() {
        Executors.newSingleThreadExecutor().execute(() -> {
            long subjectId = subjectRepository.getSubjectIdByName(subject.getName());
            argument.setSubjectId(subjectId);
            argumentRepository.insert(argument);
        });
    }
}
