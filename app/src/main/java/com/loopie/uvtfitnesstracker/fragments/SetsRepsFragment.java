package com.loopie.uvtfitnesstracker.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.loopie.uvtfitnesstracker.R;
import com.loopie.uvtfitnesstracker.adapters.ExerciseListAdapter;
import com.loopie.uvtfitnesstracker.models.Exercise;
import com.loopie.uvtfitnesstracker.views.ExerciseViewModel;

import java.util.List;

public class SetsRepsFragment extends Fragment {
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.in_exercise_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        MaterialButton addRep = (MaterialButton) view.findViewById(R.id.addRep);
        MaterialButton subtractRep = (MaterialButton) view.findViewById(R.id.subtractRep);
        MaterialButton addWeight = (MaterialButton) view.findViewById(R.id.addWeight);
        MaterialButton subtractWeight = (MaterialButton) view.findViewById(R.id.subtractWeight);
        TextInputEditText reps = (TextInputEditText) view.findViewById(R.id.Reps);
        TextInputEditText weight = (TextInputEditText) view.findViewById(R.id.weight);
        addRep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String repsText = reps.getText().toString();
                if (repsText.matches("")) {
                    reps.setText("1");
                } else {
                    int repsCount = Integer.parseInt(repsText) + 1;
                    reps.setText(String.valueOf(repsCount));
                }
            }
        });
        subtractRep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String repsText = reps.getText().toString();
                if (repsText.matches("")) {
                    reps.setText("0");
                } else {
                    int repsCount = Integer.parseInt(repsText);
                    if (Integer.parseInt(repsText) > 0) {
                        reps.setText(String.valueOf(repsCount - 1));
                    }
                }
            }
        });
        addWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String weightText = weight.getText().toString();
                if (weightText.matches("")) {
                    weight.setText("1");
                } else {
                    int repsCount = Integer.parseInt(weightText) + 1;
                    weight.setText(String.valueOf(repsCount));
                }
            }
        });
        subtractWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String weightText = weight.getText().toString();
                if (weightText.matches("")) {
                    weight.setText("0");
                } else {
                    int repsCount = Integer.parseInt(weightText);
                    if (Integer.parseInt(weightText) > 0) {
                        weight.setText(String.valueOf(repsCount - 1));
                    }
                }
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}