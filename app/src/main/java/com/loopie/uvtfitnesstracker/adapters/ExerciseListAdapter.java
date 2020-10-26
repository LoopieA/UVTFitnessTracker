package com.loopie.uvtfitnesstracker.adapters;
import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.loopie.uvtfitnesstracker.R;
import com.loopie.uvtfitnesstracker.dao.ExerciseProgramsDao;
import com.loopie.uvtfitnesstracker.models.Exercise;
import com.loopie.uvtfitnesstracker.models.ExerciseProgramsMany;
import com.loopie.uvtfitnesstracker.repo.ExerciseProgramsRepo;
import com.loopie.uvtfitnesstracker.views.ExerciseProgramsViewModel;
import com.loopie.uvtfitnesstracker.views.ExerciseViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListAdapter.ExerciseViewHolder> {
    private ExerciseProgramsRepo mExProgRepository;
    class ExerciseViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;
        private final ImageView imageView;

        private ExerciseViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView1);
            imageView = itemView.findViewById(R.id.image);
        }
    }

    private final LayoutInflater mInflater;
    private List<Exercise> mExercises; // Cached copy of words

    public ExerciseListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.adapter_view_layout, parent, false);
        return new ExerciseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, int position) {
        if (mExercises != null) {
            Exercise current = mExercises.get(position);
            holder.wordItemView.setText(current.getName());
            Picasso.get().load(current.getimgURL()).into(holder.imageView);
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Exercise");
        }
    }

    public void setExercises(List<Exercise> exercises){
        mExercises = exercises;
        notifyDataSetChanged();
    }

    public void removeExercises(int exercise, Application context, long subProgID) {
        Exercise ex = mExercises.get(exercise);
        mExercises.remove(ex);
        mExProgRepository = new ExerciseProgramsRepo(context);
        ExerciseProgramsMany exprogmany = new ExerciseProgramsMany(ex.getId_exercise(), subProgID);
        mExProgRepository.insert(exprogmany);
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