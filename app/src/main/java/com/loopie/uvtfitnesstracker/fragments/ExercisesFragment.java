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

import com.loopie.uvtfitnesstracker.models.Exercise;
import com.loopie.uvtfitnesstracker.adapters.ExerciseListAdapter;
import com.loopie.uvtfitnesstracker.views.ExerciseViewModel;
import com.loopie.uvtfitnesstracker.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ExercisesFragment extends Fragment {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private ExerciseViewModel mExerciseViewModel;
    //private ExerciseProgramsViewModel mExerciseViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
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
//        RecyclerView recyclerView = getActivity().findViewById(R.id.listView);
//        final ExerciseProgramsListAdapter adapter = new ExerciseProgramsListAdapter(getActivity());
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mExerciseViewModel = new ViewModelProvider(getActivity()).get(ExerciseProgramsViewModel.class);
//        mExerciseViewModel.getAllExercises().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
//            @Override
//            public void onChanged(@Nullable final List<String> exercises) {
//                // Update the cached copy of the words in the adapter.
//                adapter.setExercises(exercises);
//            }
//        });
        super.onViewCreated(view, savedInstanceState);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("exercises.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}