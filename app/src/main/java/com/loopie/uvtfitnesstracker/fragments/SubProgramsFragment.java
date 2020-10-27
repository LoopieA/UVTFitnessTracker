package com.loopie.uvtfitnesstracker.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.loopie.uvtfitnesstracker.R;
import com.loopie.uvtfitnesstracker.adapters.SubProgramsListAdapter;
import com.loopie.uvtfitnesstracker.models.SubPrograms;
import com.loopie.uvtfitnesstracker.views.SubProgramsViewModel;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class SubProgramsFragment extends Fragment {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private SubProgramsViewModel mSubProgramsViewModel;
    private long fkProgramID;

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
        Bundle arguments = getArguments();
        toolbar.setTitle(arguments.getString("subProgName"));
        fkProgramID = arguments.getLong("fkID");
        RecyclerView recyclerView = getActivity().findViewById(R.id.listView);
        final SubProgramsListAdapter adapter = new SubProgramsListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSubProgramsViewModel = new ViewModelProvider(getActivity()).get(SubProgramsViewModel.class);
        mSubProgramsViewModel.getsubProgramsById(fkProgramID).observe(getViewLifecycleOwner(), new Observer<List<SubPrograms>>() {
            @Override
            public void onChanged(@Nullable final List<SubPrograms> subprograms) {
                // Update the cached copy of the words in the adapter.
                adapter.setPrograms(subprograms);
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
                        .setTitle("Create New Workout")
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
                                SubPrograms subprog = new SubPrograms(content, fkProgramID);
                                mSubProgramsViewModel.insert(subprog);
                            }
                        })
                        .create().show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}