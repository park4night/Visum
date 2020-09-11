package com.horion.visum.Presentation.AddArgument;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.horion.visum.BaseFragment;
import com.horion.visum.Domain.Model.Subject;
import com.horion.visum.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddArgumentFragment extends BaseFragment {

    private static final String SUBJECT_KEY = "subject_key";

    private EditText nameInput, descriptionInput;
    private TextView addBtn;
    private AddArgumentViewModel addArgumentViewModel;

    public AddArgumentFragment() {
        // Required empty public constructor
    }

    public static AddArgumentFragment newInstance(Subject subject) {

        Bundle args = new Bundle();
        args.putParcelable(SUBJECT_KEY, subject);
        AddArgumentFragment fragment = new AddArgumentFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_add_argument, container, false);

        nameInput = layout.findViewById(R.id.argument_name_input);
        descriptionInput = layout.findViewById(R.id.argument_description_input);
        addBtn = layout.findViewById(R.id.add_argument_btn);

        setupViewModel();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArgumentViewModel.makeArgument(nameInput.getText().toString(), descriptionInput.getText().toString());
                addArgumentViewModel.addArgumentToSubject();
                onBackPressed();
            }
        });

        return layout;
    }

    private void setupViewModel() {
        addArgumentViewModel = ViewModelProviders.of(this).get(AddArgumentViewModel.class);
        addArgumentViewModel.subject = getArguments().getParcelable(SUBJECT_KEY);
    }

}
