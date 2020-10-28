package com.loopie.uvtfitnesstracker.adapters;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loopie.uvtfitnesstracker.R;
import com.loopie.uvtfitnesstracker.fragments.ProgramExercisesFragment;
import com.loopie.uvtfitnesstracker.models.SubPrograms;

import java.util.List;

public class SubProgramsListAdapter extends RecyclerView.Adapter<SubProgramsListAdapter.SubProgramsViewHolder> {

    class SubProgramsViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;
        private SubProgramsViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView1);
        }
    }

    private final LayoutInflater mInflater;
    private List<SubPrograms> mSubPrograms;

    public SubProgramsListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public SubProgramsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_view_layout, parent, false);
        return new SubProgramsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SubProgramsViewHolder holder, int position) {
        if (mSubPrograms != null) {
            SubPrograms current = mSubPrograms.get(position);
            holder.wordItemView.setText(current.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = new ProgramExercisesFragment();
                    Bundle arguments = new Bundle();
                    arguments.putLong("exID" , current.getsubprogramsid());
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

    public void setPrograms(List<SubPrograms> programs){
        mSubPrograms = programs;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mSubPrograms != null)
            return mSubPrograms.size();
        else return 0;
    }
}