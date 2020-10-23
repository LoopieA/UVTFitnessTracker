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

import com.loopie.uvtfitnesstracker.R;
import com.loopie.uvtfitnesstracker.adapters.ProgramsListAdapter;
import com.loopie.uvtfitnesstracker.models.Programs;
import com.loopie.uvtfitnesstracker.views.ProgramsViewModel;
import java.util.List;

public class ProgramsFragment extends Fragment {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private ProgramsViewModel mProgramsViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.exercises_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        RecyclerView recyclerView = getActivity().findViewById(R.id.listView);
        final ProgramsListAdapter adapter = new ProgramsListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mProgramsViewModel = new ViewModelProvider(getActivity()).get(ProgramsViewModel.class);
        mProgramsViewModel.getAllPrograms().observe(getViewLifecycleOwner(), new Observer<List<Programs>>() {
            @Override
            public void onChanged(@Nullable final List<Programs> programs) {
                // Update the cached copy of the words in the adapter.
                adapter.setPrograms(programs);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}