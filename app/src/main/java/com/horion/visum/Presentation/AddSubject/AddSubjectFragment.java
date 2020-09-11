package com.horion.visum.Presentation.AddSubject;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.horion.visum.BaseFragment;
import com.horion.visum.Data.NavigationRuleEnum;
import com.horion.visum.NavigationRule;
import com.horion.visum.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddSubjectFragment extends BaseFragment {

    private EditText nameInput, themeInput, descriptionInput;
    private TextView addBtn;
    private AddSubjectViewModel addSubjectViewModel;

    public AddSubjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_add_subject, container, false);

        nameInput = layout.findViewById(R.id.subject_name_input);
        descriptionInput = layout.findViewById(R.id.subject_description_input);
        themeInput = layout.findViewById(R.id.subject_theme_input);
        addBtn = layout.findViewById(R.id.add_subject_btn);

        setupViewModel();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSubjectViewModel.makeSubject(nameInput.getText().toString(), descriptionInput.getText().toString(), themeInput.getText().toString());
                addSubjectViewModel.addSubject();
                onBackPressed();
            }
        });
        return layout;
    }

    private void setupViewModel() {
        addSubjectViewModel = ViewModelProviders.of(this).get(AddSubjectViewModel.class);
    }

}
