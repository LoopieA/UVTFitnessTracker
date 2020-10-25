package com.loopie.uvtfitnesstracker.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.loopie.uvtfitnesstracker.R;
import com.loopie.uvtfitnesstracker.models.Exercise;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ExerciseProgramsListAdapter extends RecyclerView.Adapter<ExerciseProgramsListAdapter.ExerciseProgramsViewHolder> {

    class ExerciseProgramsViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;
        private final ImageView imageView;
        private ExerciseProgramsViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView1);
            imageView = itemView.findViewById(R.id.image);
        }
    }

    private final LayoutInflater mInflater;
    private List<Exercise> mExercises; // Cached copy of words

    public ExerciseProgramsListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public ExerciseProgramsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.adapter_view_layout, parent, false);
        return new ExerciseProgramsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExerciseProgramsViewHolder holder, int position) {
        if (mExercises != null) {
            Exercise current = mExercises.get(position);
            holder.wordItemView.setText(current.getName());
            Picasso.get().load(current.getimgURL()).into(holder.imageView);
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("Loading");
        }
    }

    public void setExercises(List<Exercise> exercises){
        mExercises = exercises;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mExercises != null)
            return mExercises.size();
        else return 0;
    }
}