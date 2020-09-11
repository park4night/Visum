package com.horion.visum.Presentation.DisplayArgument;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.horion.visum.Data.Entities.ArgumentEntity;
import com.horion.visum.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArgumentFragment extends Fragment {

    private static final String ARGUMENT_KEY = "argument_key";

    private TextView argumentTitle, positiveBtn, negativeBtn;
    private ArgumentViewModel argumentViewModel;

    public ArgumentFragment() {
        // Required empty public constructor
    }

    public static ArgumentFragment newInstance(ArgumentEntity argument) {

        Bundle args = new Bundle();
        args.putParcelable(ARGUMENT_KEY, argument);
        ArgumentFragment fragment = new ArgumentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_argument, container, false);

        argumentTitle = layout.findViewById(R.id.argument_title);
        positiveBtn = layout.findViewById(R.id.argument_positive_vote_btn);
        negativeBtn = layout.findViewById(R.id.argument_negative_vote_btn);
        
        setupViewModel();
        
        positiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                argumentViewModel.addVote(true);
            }
        });
        
        negativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                argumentViewModel.addVote(false);
            }
        });

        return layout;
    }
    
    public void setupViewModel(){
        argumentViewModel = ViewModelProviders.of(this).get(ArgumentViewModel.class);
        argumentViewModel.argument = getArguments().getParcelable(ARGUMENT_KEY);
    }

}
