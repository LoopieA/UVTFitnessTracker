package com.loopie.uvtfitnesstracker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.loopie.uvtfitnesstracker.R;
import com.loopie.uvtfitnesstracker.adapters.ExerciseRepsListAdapter;
import com.loopie.uvtfitnesstracker.adapters.HistoryRepsListAdapter;
import com.loopie.uvtfitnesstracker.models.ExerciseReps;
import com.loopie.uvtfitnesstracker.views.ExerciseRepsViewModel;

import java.util.Calendar;
import java.util.List;

public class HistoryRepsFragment extends Fragment {
    private ExerciseRepsViewModel mExerciseRepsViewModel;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.exercise_reps_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        MaterialToolbar toolbar = getActivity().findViewById(R.id.topAppBar);
        toolbar.setTitle(arguments.getString("exName"));
        long exerciseID = arguments.getLong("exID");
        RecyclerView recyclerView = getActivity().findViewById(R.id.historyRecyclerView);
        final HistoryRepsListAdapter adapter = new HistoryRepsListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mExerciseRepsViewModel = new ViewModelProvider(getActivity()).get(ExerciseRepsViewModel.class);
        mExerciseRepsViewModel.getHistoryExerciseReps(exerciseID).observe(getViewLifecycleOwner(), new Observer<List<ExerciseReps>>() {
            @Override
            public void onChanged(@Nullable final List<ExerciseReps> exerciseReps) {
                // Update the cached copy of the words in the adapter.
                adapter.setExerciseReps(exerciseReps);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}