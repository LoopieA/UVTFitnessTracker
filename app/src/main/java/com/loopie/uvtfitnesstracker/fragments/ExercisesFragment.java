package com.loopie.uvtfitnesstracker.fragments;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loopie.uvtfitnesstracker.models.Exercise;
import com.loopie.uvtfitnesstracker.adapters.ExerciseListAdapter;
import com.loopie.uvtfitnesstracker.views.ExerciseViewModel;
import com.loopie.uvtfitnesstracker.R;
import com.google.android.material.appbar.MaterialToolbar;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ExercisesFragment extends Fragment {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private ExerciseViewModel mExerciseViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.exercises_fragment, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        MaterialToolbar toolbar = (MaterialToolbar) getActivity().findViewById(R.id.topAppBar);
        toolbar.setTitle("Exercises");
        RecyclerView recyclerView = getActivity().findViewById(R.id.listView);
        final ExerciseListAdapter adapter = new ExerciseListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mExerciseViewModel = new ViewModelProvider(getActivity()).get(ExerciseViewModel.class);
        mExerciseViewModel.getAllExercises().observe(getViewLifecycleOwner(), new Observer<List<Exercise>>() {
            @Override
            public void onChanged(@Nullable final List<Exercise> exercises) {
                // Update the cached copy of the words in the adapter.
                adapter.setExercises(exercises);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }


}