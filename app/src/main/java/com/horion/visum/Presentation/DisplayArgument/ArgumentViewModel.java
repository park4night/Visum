package com.horion.visum.Presentation.DisplayArgument;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.horion.visum.Data.ArgumentOperations.ArgumentRepository;
import com.horion.visum.Data.ArgumentVoteOperations.ArgumentVoteRepository;
import com.horion.visum.Data.Database;
import com.horion.visum.Data.Entities.ArgumentEntity;
import com.horion.visum.Data.Entities.ArgumentVoteEntity;
import com.horion.visum.Domain.UseCases.AddVoteToArgumentUseCase;

public class ArgumentViewModel extends AndroidViewModel {

    public ArgumentEntity argument;

    private ArgumentRepository argumentRepository;
    private ArgumentVoteRepository argumentVoteRepository;

    public ArgumentViewModel(@NonNull Application application) {
        super(application);
        argumentRepository = ArgumentRepository.getInstance(Database.getDatabase(application));
        argumentVoteRepository = ArgumentVoteRepository.getInstance(Database.getDatabase(application));
    }

    public void addVote(boolean isPositive) {
        ArgumentVoteEntity vote = new ArgumentVoteEntity(0, 2, isPositive, "", 0);
        new AddVoteToArgumentUseCase(getApplication(), argument, vote).execute();
    }
}
