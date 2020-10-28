package com.loopie.uvtfitnesstracker.views;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.loopie.uvtfitnesstracker.models.Exercise;
import com.loopie.uvtfitnesstracker.models.ExerciseReps;
import com.loopie.uvtfitnesstracker.repo.ExerciseRepo;
import com.loopie.uvtfitnesstracker.repo.ExerciseRepsRepo;

import java.util.List;

public class ExerciseRepsViewModel extends AndroidViewModel {
    private ExerciseRepsRepo mRepository;

    private LiveData<List<ExerciseReps>> mAllExerciseReps;

    public ExerciseRepsViewModel(Application application) {
        super(application);
        mRepository = new ExerciseRepsRepo(application);
        mAllExerciseReps = mRepository.getExerciseReps();
    }

    public LiveData<List<ExerciseReps>> getAllExerciseReps() { return mAllExerciseReps; }

    public void insert(ExerciseReps exerciseReps) { mRepository.insert(exerciseReps); }
}
