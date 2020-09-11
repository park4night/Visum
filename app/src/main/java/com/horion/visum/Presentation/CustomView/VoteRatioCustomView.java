package com.horion.visum.Presentation.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.horion.visum.Domain.Model.Opinion;
import com.horion.visum.R;

public class VoteRatioCustomView extends LinearLayout {

    private View positiveView, negativeView;
    private int positiveVotes = 50, negativeVotes = 50;

    public VoteRatioCustomView(Context context) {
        super(context);
    }

    public VoteRatioCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(R.styleable.VoteRatioCustomView);
        init(typedArray);
    }

    public VoteRatioCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.VoteRatioCustomView, defStyleAttr, 0);
        init(typedArray);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public VoteRatioCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.VoteRatioCustomView, defStyleAttr, 0);
        init(typedArray);
    }

    private void init(TypedArray typedArray){

        inflate(getContext(),R.layout.vote_compound_view,this);

        positiveView = findViewById(R.id.positive_vote_view);
        negativeView = findViewById(R.id.negative_vote_view);

//        positiveVote = typedArray.getInt(R.styleable.VoteRatioCustomView_positive_vote,50);
//        negativeVote = typedArray.getInt(R.styleable.VoteRatioCustomView_negative_vote,50);

        setWeight(positiveView, positiveVotes);
        setWeight(negativeView, negativeVotes);

    }

    private void setWeight(View view, int weight){
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT,
                weight
        );
        view.setLayoutParams(param);
    }

    public void setVotes(int positiveVote, int negativeVote) {
        this.positiveVotes = positiveVote;
        this.negativeVotes = negativeVote;
    }
}
