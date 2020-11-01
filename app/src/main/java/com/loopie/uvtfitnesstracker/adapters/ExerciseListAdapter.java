package com.loopie.uvtfitnesstracker.adapters;
import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.loopie.uvtfitnesstracker.R;
import com.loopie.uvtfitnesstracker.models.Exercise;
import com.loopie.uvtfitnesstracker.models.ExerciseProgramsMany;
import com.loopie.uvtfitnesstracker.repo.ExerciseProgramsRepo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListAdapter.ExerciseViewHolder> implements Filterable {
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
    private List<Exercise> mExercises;
    private List<Exercise> mExercisesFull;

    public ExerciseListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_view_layout, parent, false);
        return new ExerciseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, int position) {
        if (mExercises != null) {
            Exercise current = mExercises.get(position);
            holder.wordItemView.setText(current.getName());
            if(!current.getimgURL().matches(""))
                Picasso.get().load(current.getimgURL()).into(holder.imageView);
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Exercise");
        }
    }

    /** Exercise getter*/
    public Exercise getExercise(int id) {
        return mExercises.get(id);
    }

    /** Populate list*/
    public void setExercises(List<Exercise> exercises){
        mExercises = exercises;
        mExercisesFull = new ArrayList<>(exercises);
        notifyDataSetChanged();
    }

    /** Adds exercise to many-to-many relation and removes it from the exercise list*/
    public void addExerciseToWorkout(int exercise, Application context, long subProgID) {
        Exercise ex = mExercises.get(exercise);
        mExercises.remove(ex);
        mExProgRepository = new ExerciseProgramsRepo(context);
        ExerciseProgramsMany exprogmany = new ExerciseProgramsMany(ex.getId_exercise(), subProgID);
        mExProgRepository.insert(exprogmany);
    }

    public void addExerciseToList(Exercise exercise) {
        mExercises.add(exercise);
        notifyDataSetChanged();
    }

    /** Removes exercise from many-to-many relation and adds it back to the exercise list*/
    public void removeExerciseFromWorkout(int position, Exercise ex, long subProgID) {
        mExProgRepository.delete(ex.getId_exercise(), subProgID);
        mExercises.add(position, ex);
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

    /** Exercise list filter */
    @Override
    public Filter getFilter() {
        return mExercisesFilter;
    }
    private Filter mExercisesFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List <Exercise> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mExercisesFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Exercise ex : mExercisesFull) {
                    if (ex.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(ex);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mExercises.clear();
            mExercises.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}