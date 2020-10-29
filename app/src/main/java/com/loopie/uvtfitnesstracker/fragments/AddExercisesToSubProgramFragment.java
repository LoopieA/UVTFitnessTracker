package com.loopie.uvtfitnesstracker.fragments;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.loopie.uvtfitnesstracker.R;
import com.loopie.uvtfitnesstracker.adapters.ExerciseListAdapter;
import com.loopie.uvtfitnesstracker.models.Exercise;
import com.loopie.uvtfitnesstracker.models.Programs;
import com.loopie.uvtfitnesstracker.views.ExerciseViewModel;

import java.util.List;

public class AddExercisesToSubProgramFragment extends Fragment {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private ExerciseViewModel mExerciseViewModel;
    private ExerciseListAdapter mExericseAdapter;
    private long subProgID;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.exercises_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        MaterialToolbar toolbar = (MaterialToolbar) getActivity().findViewById(R.id.topAppBar);
        toolbar.setTitle("");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean alertAddExercise = preferences.getBoolean("alertAddExercise", true);
        if (alertAddExercise) {
            new MaterialAlertDialogBuilder(getContext())
                    .setTitle("")
                    .setMessage("Slide right to add an exercise to the workout.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(final DialogInterface dialog, int which) {
                            dialog.dismiss();
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putBoolean("alertAddExercise", false);
                            editor.apply();
                        }
                    })
                    .create().show();
        }
        Bundle arguments = getArguments();
        subProgID = arguments.getLong("subProgID");
        RecyclerView recyclerView = getActivity().findViewById(R.id.listView);
        mExericseAdapter = new ExerciseListAdapter(getActivity());
        new ItemTouchHelper(itemtouchhelpercallback).attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(mExericseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mExerciseViewModel = new ViewModelProvider(getActivity()).get(ExerciseViewModel.class);
        mExerciseViewModel.getAllExercises().observe(getViewLifecycleOwner(), new Observer<List<Exercise>>() {
            @Override
            public void onChanged(@Nullable final List<Exercise> exercises) {
                // Update the cached copy of the words in the adapter.
                mExericseAdapter.setExercises(exercises);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    ItemTouchHelper.SimpleCallback itemtouchhelpercallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                // Get RecyclerView item from the ViewHolder
                View itemView = viewHolder.itemView;

                Paint p = new Paint();
                Paint p2 = new Paint();
                Drawable icon;
                p.setColor(Color.parseColor("#E9D985"));
                p2.setColor(Color.parseColor("#FFFFFF"));
                if (dX > 0) {
                    /* Set your color for positive displacement */
                    icon = ContextCompat.getDrawable(getContext(), R.drawable.add);
                    // Draw Rect with varying right side, equal to displacement dX
                    c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), dX,
                            (float) itemView.getBottom(), p);
                    icon.setBounds(itemView.getRight() - convertDpToPx(32) - icon.getIntrinsicWidth(), itemView.getTop() + ((itemView.getBottom() - itemView.getTop()) - icon.getIntrinsicHeight()) / 2, itemView.getRight() - convertDpToPx(32), itemView.getTop() + ((itemView.getBottom() - itemView.getTop()) - icon.getIntrinsicHeight()) / 2 + icon.getIntrinsicHeight());
                    icon.draw(c);

                } else {
                    /* Set your color for negative displacement */
                    icon = ContextCompat.getDrawable(getContext(), R.drawable.add);

                    // Draw Rect with varying left side, equal to the item's right side plus negative displacement dX
                    c.drawRect((float) itemView.getRight() + dX, (float) itemView.getTop(),
                            (float) itemView.getRight(), (float) itemView.getBottom(), p);
                    icon.setBounds(itemView.getRight() - convertDpToPx(32) - icon.getIntrinsicWidth(), itemView.getTop() + ((itemView.getBottom() - itemView.getTop()) - icon.getIntrinsicHeight()) / 2, itemView.getRight() - convertDpToPx(32), itemView.getTop() + ((itemView.getBottom() - itemView.getTop()) - icon.getIntrinsicHeight()) / 2 + icon.getIntrinsicHeight());
                    icon.draw(c);
                }

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            mExericseAdapter.removeExercises(viewHolder.getAdapterPosition(), getActivity().getApplication(), subProgID);
            mExericseAdapter.notifyDataSetChanged();
        }
        private int convertDpToPx(int dp){
            return Math.round(dp * (getResources().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));
        }
    };

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.top_app_bar, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mExericseAdapter.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}
