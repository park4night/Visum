package com.horion.visum.Presentation.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.horion.visum.BaseFragment;
import com.horion.visum.Data.Entities.SubjectVoteEntity;
import com.horion.visum.Data.NavigationRuleEnum;
import com.horion.visum.Domain.Model.Subject;
import com.horion.visum.NavigationRule;
import com.horion.visum.Presentation.AddSubject.AddSubjectFragment;
import com.horion.visum.Presentation.DisplaySubject.SubjectFragment;
import com.horion.visum.R;

import java.util.ArrayList;
import java.util.List;

// TODO: 20/07/2020 srp 
public class HomeFragment extends BaseFragment implements ICallbackFromHomeRecyclerView {

    private static final String SUBJECT_FRAGMENT_TAG = "subject_fragment";
    private static final String ADD_SUBJECT_FRAFMENT_TAG = "add_sbuject";

    private RecyclerView popularsSubjectsRv;
    private PopularsSubjectsRvAdapter popularsSubjectsRvAdapter;
    private ImageButton imageButton;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_home, container, false);
        popularsSubjectsRv = layout.findViewById(R.id.rv_populars_subjects);
        imageButton = layout.findViewById(R.id.open_add_subject_btn);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        setupViewModel();
        setupRecyclerView();
        homeViewModel.setupAppFromServer();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new NavigationRule(NavigationRuleEnum.ADD, new AddSubjectFragment(), ADD_SUBJECT_FRAFMENT_TAG));
            }
        });

        return layout;
    }

    // class specifique a l'implementation d'un viewmodel ou de l'observer
    private void setupViewModel() {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.getPopularsSubjects().observe(getViewLifecycleOwner(), new Observer<List<Subject>>() {
            @Override
            public void onChanged(List<Subject> subjects) {
                if (subjects != null){
                    popularsSubjectsRvAdapter.setData(subjects);
                    popularsSubjectsRvAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    // class specifique a l'implementation des recyclerview
    private void setupRecyclerView() {
        popularsSubjectsRvAdapter = new PopularsSubjectsRvAdapter(new ArrayList<>(), this);
        popularsSubjectsRv.setLayoutManager(new LinearLayoutManager(getContext()));
        popularsSubjectsRv.setAdapter(popularsSubjectsRvAdapter);
    }

    @Override
    public void onSubjectClicked(Subject subject) {
        loadFragment(new NavigationRule(NavigationRuleEnum.ADD, SubjectFragment.newInstance(subject), SUBJECT_FRAGMENT_TAG));
    }
}