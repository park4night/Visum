package com.horion.visum.Presentation.DisplaySubject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.horion.visum.BaseFragment;
import com.horion.visum.Data.Entities.ArgumentEntity;
import com.horion.visum.Data.NavigationRuleEnum;
import com.horion.visum.Domain.Model.Subject;
import com.horion.visum.NavigationRule;
import com.horion.visum.Presentation.AddArgument.AddArgumentFragment;
import com.horion.visum.R;

import java.util.ArrayList;
import java.util.List;

public class SubjectFragment extends BaseFragment implements ICallBackArgumentRecyclerView {

    private static final String SUBJECT_KEY = "subject_key";
    private static final String ADD_ARGUMENT_FRAGMENT_TAG = "add_argument";

    private TextView subjectTitleTv, positiveBtn, negativeBtn;
    private ImageButton addButton;
    private RecyclerView argumentsRv;

    private SubjectViewModel subjectViewModel;
    private ArgumentRvAdapter argumentRvAdapter;

    public SubjectFragment() {
        // Required empty public constructor
    }

    public static SubjectFragment newInstance(Subject subject) {

        Bundle args = new Bundle();
        args.putParcelable(SUBJECT_KEY, subject);
        SubjectFragment fragment = new SubjectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_subject, container, false);
        subjectTitleTv = layout.findViewById(R.id.subject_title);
        argumentsRv = layout.findViewById(R.id.rv_arguments);
        addButton = layout.findViewById(R.id.open_add_argument_btn);
        positiveBtn = layout.findViewById(R.id.subject_positive_vote_btn);
        negativeBtn = layout.findViewById(R.id.subject_negative_vote_btn);

        setupViewModel();

        subjectTitleTv.setText(subjectViewModel.subject.getName());

        positiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subjectViewModel.addVote(true);
            }
        });

        negativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subjectViewModel.addVote(false);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new NavigationRule(NavigationRuleEnum.ADD, AddArgumentFragment.newInstance(subjectViewModel.subject), ADD_ARGUMENT_FRAGMENT_TAG ));
            }
        });

        subjectViewModel.getPositiveVote().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public void onChanged(Object positiveCount) {
                positiveBtn.setText(positiveCount.toString());
            }
        });

        subjectViewModel.getNegativeVote().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public void onChanged(Object negativeCount) {
                negativeBtn.setText(negativeCount.toString());
            }
        });

        setupRecyclerView();
        return layout;
    }

    private void setupViewModel(){
        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel.class);
        subjectViewModel.subject = getArguments().getParcelable(SUBJECT_KEY);

        subjectViewModel.getArgumentBySubjectName(subjectViewModel.subject.getName()).observe(getViewLifecycleOwner(), new Observer<List<ArgumentEntity>>(){
            @Override
            public void onChanged(List<ArgumentEntity> arguments) {
                argumentRvAdapter.setData(arguments);
                argumentRvAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setupRecyclerView() {
        argumentRvAdapter = new ArgumentRvAdapter(new ArrayList(), this);
        argumentsRv.setLayoutManager(new LinearLayoutManager(getContext()));
        argumentsRv.setAdapter(argumentRvAdapter);
    }

    @Override
    public void onArgumentCliked(ArgumentEntity argument) {

    }
}
