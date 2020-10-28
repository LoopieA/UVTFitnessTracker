package com.loopie.uvtfitnesstracker.adapters;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loopie.uvtfitnesstracker.R;
import com.loopie.uvtfitnesstracker.fragments.SetsRepsFragment;
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
    private List<Exercise> mExercises;

    public ExerciseProgramsListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ExerciseProgramsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_view_layout, parent, false);
        return new ExerciseProgramsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExerciseProgramsViewHolder holder, int position) {
        if (mExercises != null) {
            Exercise current = mExercises.get(position);
            holder.wordItemView.setText(current.getName());
            Picasso.get().load(current.getimgURL()).into(holder.imageView);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = new SetsRepsFragment();
                    Bundle arguments = new Bundle();
                    arguments.putLong("exID" , current.getId_exercise());
                    arguments.putString("exName" , current.getName());
                    fragment.setArguments(arguments);
                    FragmentManager fragmentManager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_main, fragment)
                            .addToBackStack("tag")
                            .commit();
                }
            });
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