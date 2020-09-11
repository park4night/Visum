package com.horion.visum.Presentation.DisplaySubject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.horion.visum.Data.Entities.ArgumentEntity;
import com.horion.visum.R;

import java.util.List;

public class ArgumentRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ArgumentEntity> arguments;
    private ICallBackArgumentRecyclerView delegate;

    public ArgumentRvAdapter(List<ArgumentEntity> arguments, ICallBackArgumentRecyclerView delegate) {
        this.arguments = arguments;
        this.delegate = delegate;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_subject_arguments_item, parent, false);
        return new ArgumentViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ArgumentViewHolder argumentViewHolder = (ArgumentViewHolder) holder;
        argumentViewHolder.title.setText(arguments.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return arguments.size();
    }

    public class ArgumentViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public ArgumentViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.argument_item_title);
        }
    }

    public void setData(List<ArgumentEntity> newArgumentList) {

        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallback(newArgumentList, arguments));
        this.arguments.clear();
        this.arguments.addAll(newArgumentList);

        diffResult.dispatchUpdatesTo(this);
    }

    public class DiffCallback extends DiffUtil.Callback {

        List<ArgumentEntity> oldArguments;
        List<ArgumentEntity> newArguments;

        public DiffCallback(List<ArgumentEntity> newArguments, List<ArgumentEntity> oldArguments) {
            this.newArguments = newArguments;
            this.oldArguments = oldArguments;
        }

        @Override
        public int getOldListSize() {
            return oldArguments.size();
        }

        @Override
        public int getNewListSize() {
            return newArguments.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldArguments.get(oldItemPosition) == newArguments.get(newItemPosition);
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldArguments.get(oldItemPosition).equals(newArguments.get(newItemPosition));
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            //you can return particular field for changed item.
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }
    }

}
