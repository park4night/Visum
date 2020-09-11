package com.horion.visum.Domain.Model;

public class Opinion {

    public int positiveVotes;
    public int negativeVotes;

    public Opinion(int positiveVotes, int negativeVotes) {
        this.positiveVotes = positiveVotes;
        this.negativeVotes = negativeVotes;
    }
}
