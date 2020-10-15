package com.loopie.uvtfitnesstracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;

public class SecondFragment extends Fragment {
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        Exercise exercise1 = new Exercise("Squat");
        Exercise exercise2 = new Exercise("Bench");
        Exercise exercise3 = new Exercise("Deadlift");
        Exercise exercise4 = new Exercise("OHP");
        ArrayList<Exercise> peopleList = new ArrayList<>();
        peopleList.add(exercise1);
        peopleList.add(exercise2);
        peopleList.add(exercise3);
        peopleList.add(exercise4);
        ListView mListView = (ListView) getView().findViewById(R.id.listView);
        ExerciseListAdapter adapter = new ExerciseListAdapter(getActivity(), R.layout.adapter_view_layout, peopleList);
        mListView.setAdapter(adapter);
        super.onViewCreated(view, savedInstanceState);
    }
}