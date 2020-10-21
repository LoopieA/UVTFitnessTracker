package com.loopie.uvtfitnesstracker;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListAdapter.ExerciseViewHolder> {

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
    private List<Exercise> mWords; // Cached copy of words

    ExerciseListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.adapter_view_layout, parent, false);
        return new ExerciseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, int position) {
        if (mWords != null) {
            Exercise current = mWords.get(position);
            holder.wordItemView.setText(current.getName());
            Picasso.get().load(current.getimgURL()).into(holder.imageView);
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Exercise");
        }
    }

    void setWords(List<Exercise> words){
        mWords = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }
}