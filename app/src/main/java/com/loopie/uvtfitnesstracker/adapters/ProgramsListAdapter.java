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
import com.loopie.uvtfitnesstracker.models.Programs;
import com.squareup.picasso.Picasso;

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