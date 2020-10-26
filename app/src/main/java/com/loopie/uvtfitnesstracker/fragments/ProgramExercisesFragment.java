package com.loopie.uvtfitnesstracker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loopie.uvtfitnesstracker.R;
import com.loopie.uvtfitnesstracker.adapters.ExerciseProgramsListAdapter;
import com.loopie.uvtfitnesstracker.models.Exercise;
import com.loopie.uvtfitnesstracker.views.ExerciseProgramsViewModel;

import java.util.List;

public class ProgramExercisesFragment extends Fragment {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private ExerciseProgramsViewModel mExerciseProgramsViewModel;
    private long exID;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.exercises_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        Bundle arguments = getArguments();
        exID = arguments.getLong("exID");
        RecyclerView recyclerView = getActivity().findViewById(R.id.listView);
        final ExerciseProgramsListAdapter adapter = new ExerciseProgramsListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mExerciseProgramsViewModel = new ViewModelProvider(getActivity()).get(ExerciseProgramsViewModel.class);
        mExerciseProgramsViewModel.getAllExercises(exID).observe(getViewLifecycleOwner(), new Observer<List<Exercise>>() {
            @Override
            public void onChanged(@Nullable final List<Exercise> exercises) {
                // Update the cached copy of the words in the adapter.
                adapter.setExercises(exercises);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.subprogramexercises_add_bar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add_exercise:
                Fragment fragment = new AddExercisesToSubProgramFragment();
                Bundle arguments = new Bundle();
                arguments.putLong("subProgID", exID);
                fragment.setArguments(arguments);
                FragmentManager fragmentManager = ((FragmentActivity) getView().getContext()).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_main, fragment)
                        .addToBackStack("tag")
                        .commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
