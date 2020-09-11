package com.horion.visum.Toolbox;

import android.util.Pair;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;


public class PairMediatorLiveData extends MediatorLiveData<Pair<Integer, Integer>> {
    public PairMediatorLiveData(LiveData<Integer> positive, LiveData<Integer> negative) {
        addSource(positive, new Observer<Integer>() {
            public void onChanged(@Nullable Integer positive) {
                setValue(Pair.create(positive, negative.getValue()));
            }
        });
        addSource(negative, new Observer<Integer>() {
            public void onChanged(@Nullable Integer negative) {
                setValue(Pair.create(positive.getValue(), negative));
            }
        });
    }
}
