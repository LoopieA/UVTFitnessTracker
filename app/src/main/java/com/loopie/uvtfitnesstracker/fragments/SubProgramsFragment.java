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
import com.loopie.uvtfitnesstracker.adapters.SubProgramsListAdapter;
import com.loopie.uvtfitnesstracker.models.SubPrograms;
import com.loopie.uvtfitnesstracker.views.SubProgramsViewModel;

import java.util.List;

public class SubProgramsFragment extends Fragment {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private SubProgramsViewModel mSubProgramsViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.exercises_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        RecyclerView recyclerView = getActivity().findViewById(R.id.listView);
        final SubProgramsListAdapter adapter = new SubProgramsListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSubProgramsViewModel = new ViewModelProvider(getActivity()).get(SubProgramsViewModel.class);
        mSubProgramsViewModel.getsubProgramsById(1).observe(getViewLifecycleOwner(), new Observer<List<SubPrograms>>() {
            @Override
            public void onChanged(@Nullable final List<SubPrograms> subprograms) {
                // Update the cached copy of the words in the adapter.
                adapter.setPrograms(subprograms);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}