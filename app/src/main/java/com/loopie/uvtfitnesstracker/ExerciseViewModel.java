package com.loopie.uvtfitnesstracker;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class ExerciseViewModel extends AndroidViewModel {
    private ExerciseRepo mRepository;

    private LiveData<List<Exercise>> mAllExercises;

    public ExerciseViewModel (Application application) {
        super(application);
        mRepository = new ExerciseRepo(application);
        mAllExercises = mRepository.getAllWords();
    }

    LiveData<List<Exercise>> getAllWords() { return mAllExercises; }

    public void insert(Exercise exercise) { mRepository.insert(exercise); }
}
