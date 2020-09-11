package com.horion.visum.Presentation.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.horion.visum.Presentation.CustomView.VoteRatioCustomView;
import com.horion.visum.Domain.Model.Opinion;
import com.horion.visum.Domain.Model.Subject;
import com.horion.visum.R;

import java.util.List;

public class PopularsSubjectsRvAdapter extends RecyclerView.Adapter<PopularsSubjectsRvAdapter.SubjectViewHolder> {

    private List<Subject> subjects;
    private ICallbackFromHomeRecyclerView delegate;

    public PopularsSubjectsRvAdapter(List<Subject> subjects, ICallbackFromHomeRecyclerView delegate) {
        this.subjects = subjects;
        this.delegate = delegate;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_popular_subject_item, parent, false);

        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, final int position) {
        Subject subject = subjects.get(position);

        holder.subjectTitle.setText(subject.getName());

        holder.voteRatioCustomView.setVotes(subject.getPositiveVote(), subject.getNegativeVote());

        holder.voteRatioCustomView.invalidate();

        holder.subjectTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.onSubjectClicked(subject);
            }
        });
    }


    public class SubjectViewHolder extends RecyclerView.ViewHolder {

        TextView subjectTitle;
        VoteRatioCustomView voteRatioCustomView;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectTitle = itemView.findViewById(R.id.subject_item_title);
            voteRatioCustomView = itemView.findViewById(R.id.vote_view_subject_item);
        }
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public void setData(List<Subject> newSubjectsList) {

        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallback(newSubjectsList, subjects));
        this.subjects.clear();
        this.subjects.addAll(newSubjectsList);

        diffResult.dispatchUpdatesTo(this);
    }

    public class DiffCallback extends DiffUtil.Callback {

        List<Subject> oldSubjects;
        List<Subject> newSubjects;

        public DiffCallback(List<Subject> newSubjects, List<Subject> oldSubjects) {
            this.newSubjects = newSubjects;
            this.oldSubjects = oldSubjects;
        }

        @Override
        public int getOldListSize() {
            return oldSubjects.size();
        }

        @Override
        public int getNewListSize() {
            return newSubjects.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldSubjects.get(oldItemPosition) == newSubjects.get(newItemPosition);
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldSubjects.get(oldItemPosition).equals(newSubjects.get(newItemPosition));
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            //you can return particular field for changed item.
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }
    }
}
