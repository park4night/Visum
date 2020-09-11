package com.horion.visum.Domain.UseCases;

import android.content.Context;

import com.horion.visum.Data.Database;
import com.horion.visum.Data.Entities.SubjectVoteEntity;
import com.horion.visum.Data.SubjectOperations.SubjectRepository;
import com.horion.visum.Data.SubjectVoteOperations.SubjectVoteRepository;
import com.horion.visum.Domain.Model.Subject;

import java.util.concurrent.Executors;

public class AddVoteToSubjectUseCase {

    private SubjectRepository subjectRepository;
    private SubjectVoteRepository subjectVoteRepository;
    private Subject subject;
    private SubjectVoteEntity subjectVote;

    public AddVoteToSubjectUseCase(Context context, Subject subject, SubjectVoteEntity subjectVote){
        this.subjectRepository = SubjectRepository.getInstance(Database.getDatabase(context));
        this.subjectVoteRepository = SubjectVoteRepository.getInstance(Database.getDatabase(context));
        this.subject = subject;
        this.subjectVote = subjectVote;
    }

    //for testing
    public AddVoteToSubjectUseCase(SubjectRepository subjectRepository, SubjectVoteRepository subjectVoteRepository, Subject subject, SubjectVoteEntity subjectVote) {
        this.subjectRepository = subjectRepository;
        this.subjectVoteRepository = subjectVoteRepository;
        this.subject = subject;
        this.subjectVote = subjectVote;
    }

    public void execute(){
        Executors.newSingleThreadExecutor().execute(() -> {
            long subjectId = subjectRepository.getSubjectIdByName(subject.getName());
            subjectVote.setSubjectId(subjectId);
            subjectVoteRepository.insert(subjectVote);
        });
    }
}
