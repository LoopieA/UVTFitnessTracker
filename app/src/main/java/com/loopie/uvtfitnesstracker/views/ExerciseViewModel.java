package com.loopie.uvtfitnesstracker.views;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.loopie.uvtfitnesstracker.models.Exercise;
import com.loopie.uvtfitnesstracker.repo.ExerciseRepo;

import java.util.List;

public class ExerciseViewModel extends AndroidViewModel {
    private ExerciseRepo mRepository;

    private LiveData<List<Exercise>> mAllExercises;

    public ExerciseViewModel (Application application) {
        super(application);
        mRepository = new ExerciseRepo(application);
        mAllExercises = mRepository.getAllExercises();
    }

    public LiveData<List<Exercise>> getAllExercises() { return mAllExercises; }

    public void insert(Exercise exercise) { mRepository.insert(exercise); }
}
