package com.loopie.uvtfitnesstracker.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.loopie.uvtfitnesstracker.R;
import com.loopie.uvtfitnesstracker.models.ExerciseReps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ExerciseRepsListAdapter extends RecyclerView.Adapter<ExerciseRepsListAdapter.ExerciseRepsViewHolder> {
    class ExerciseRepsViewHolder extends RecyclerView.ViewHolder {
        private final TextView repsCount;
        private final TextView weightCount;
        private final TextView repDate;

        private ExerciseRepsViewHolder(View itemView) {
            super(itemView);
            repsCount = itemView.findViewById(R.id.repsCount);
            weightCount = itemView.findViewById(R.id.weightCount);
            repDate = itemView.findViewById(R.id.timeLabel);
        }
    }

    private final LayoutInflater mInflater;
    private List<ExerciseReps> mExerciseReps;

    public ExerciseRepsListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ExerciseRepsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.exercises_reps_layout, parent, false);
        return new ExerciseRepsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExerciseRepsViewHolder holder, int position) {
        if (mExerciseReps != null) {
            ExerciseReps current = mExerciseReps.get(position);
            holder.repsCount.setText(current.getReps());
            holder.weightCount.setText(current.getWeight());
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            holder.repDate.setText(dateFormat.format(current.getRepDate().getTime()));
        } else {
            holder.repsCount.setText("0");
        }
    }

    public void setExerciseReps(List<ExerciseReps> exercises){
        mExerciseReps = exercises;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mExerciseReps != null)
            return mExerciseReps.size();
        else return 0;
    }
}