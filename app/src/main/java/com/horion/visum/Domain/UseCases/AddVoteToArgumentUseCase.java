package com.horion.visum.Domain.UseCases;

import android.content.Context;

import com.horion.visum.Data.ArgumentOperations.ArgumentRepository;
import com.horion.visum.Data.ArgumentVoteOperations.ArgumentVoteRepository;
import com.horion.visum.Data.Database;
import com.horion.visum.Data.Entities.ArgumentEntity;
import com.horion.visum.Data.Entities.ArgumentVoteEntity;
import com.horion.visum.Data.Entities.Vote;

import java.util.concurrent.Executors;

public class AddVoteToArgumentUseCase {

    private ArgumentVoteRepository argumentVoteRepository;
    private ArgumentRepository argumentRepository;
    private ArgumentEntity argument;
    private ArgumentVoteEntity argumentVote;

    public AddVoteToArgumentUseCase(Context context, ArgumentEntity argumentEntity, ArgumentVoteEntity argumentVoteEntity){
        this.argumentRepository = ArgumentRepository.getInstance(Database.getDatabase(context));
        this.argumentVoteRepository = ArgumentVoteRepository.getInstance(Database.getDatabase(context));
        this.argument = argumentEntity;
        this.argumentVote = argumentVoteEntity;
    }

    //for testing
    public AddVoteToArgumentUseCase(ArgumentVoteRepository argumentVoteRepository, ArgumentRepository argumentRepository, ArgumentEntity argument, ArgumentVoteEntity argumentVote) {
        this.argumentVoteRepository = argumentVoteRepository;
        this.argumentRepository = argumentRepository;
        this.argument = argument;
        this.argumentVote = argumentVote;
    }

    public void execute() {
        Executors.newSingleThreadExecutor().execute(() -> {
            long argumentId = argumentRepository.getArgumentIdByName(argument.getName());
            argumentVote.setArgumentId(argumentId);
            argumentVoteRepository.insert(argumentVote);
        });
    }
}
