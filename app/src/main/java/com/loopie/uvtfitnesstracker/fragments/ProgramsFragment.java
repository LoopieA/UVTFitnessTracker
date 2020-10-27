package com.loopie.uvtfitnesstracker.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.loopie.uvtfitnesstracker.R;
import com.loopie.uvtfitnesstracker.adapters.ProgramsListAdapter;
import com.loopie.uvtfitnesstracker.models.Programs;
import com.loopie.uvtfitnesstracker.models.SubPrograms;
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
        setHasOptionsMenu(true);
        MaterialToolbar toolbar = (MaterialToolbar) getActivity().findViewById(R.id.topAppBar);
        toolbar.setTitle("Programs");
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
                final EditText edittext = new EditText(getContext());
                new MaterialAlertDialogBuilder(getContext())
                        .setTitle("Create New Program")
                        .setView(edittext)
                        .setCancelable(false)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialog, int which) {
                                final String content = edittext.getText().toString();
                                Programs program = new Programs(content);
                                mProgramsViewModel.insert(program);
                            }
                        })
                        .create().show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
}