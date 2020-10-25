package com.loopie.uvtfitnesstracker.adapters;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loopie.uvtfitnesstracker.R;
import com.loopie.uvtfitnesstracker.fragments.ExercisesFragment;
import com.loopie.uvtfitnesstracker.fragments.ProgramsFragment;
import com.loopie.uvtfitnesstracker.fragments.SubProgramsFragment;
import com.loopie.uvtfitnesstracker.models.Programs;

import java.util.List;

public class ProgramsListAdapter extends RecyclerView.Adapter<ProgramsListAdapter.ProgramsViewHolder> {

    class ProgramsViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;
        private ProgramsViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView1);
        }
    }

    private final LayoutInflater mInflater;
    private List<Programs> mPrograms; // Cached copy of words

    public ProgramsListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public ProgramsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.adapter_view_layout, parent, false);
        return new ProgramsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProgramsViewHolder holder, int position) {
        if (mPrograms != null) {
            Programs current = mPrograms.get(position);
            holder.wordItemView.setText(current.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = new SubProgramsFragment();
                    Bundle arguments = new Bundle();
                    arguments.putLong("fkID" , current.getProgramsid());
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
            holder.wordItemView.setText("No Program");
        }

    }

    public void setPrograms(List<Programs> programs){
        mPrograms = programs;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mPrograms != null)
            return mPrograms.size();
        else return 0;
    }
}