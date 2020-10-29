package com.loopie.uvtfitnesstracker.views;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.loopie.uvtfitnesstracker.models.Exercise;
import com.loopie.uvtfitnesstracker.models.ExerciseReps;
import com.loopie.uvtfitnesstracker.models.SubPrograms;
import com.loopie.uvtfitnesstracker.repo.ExerciseRepo;
import com.loopie.uvtfitnesstracker.repo.ExerciseRepsRepo;

import java.util.Date;
import java.util.List;

public class ExerciseRepsViewModel extends AndroidViewModel {
    private ExerciseRepsRepo mRepository;

    private LiveData<List<ExerciseReps>> mAllExerciseReps;
    private LiveData<List<ExerciseReps>> mFilteredExerciseReps;

    public ExerciseRepsViewModel(Application application) {
        super(application);
        mRepository = new ExerciseRepsRepo(application);

    }

    public LiveData<List<ExerciseReps>> getHistoryExerciseReps(long exerciseID) {
        mFilteredExerciseReps = mRepository.getHistoryExerciseReps(exerciseID);
        return mFilteredExerciseReps;
    }

    public LiveData<List<ExerciseReps>> getFilteredExerciseReps(long exerciseID, Date today) {
        mFilteredExerciseReps = mRepository.getFilteredExerciseReps(exerciseID, today);
        return mFilteredExerciseReps;
    }

    public LiveData<List<ExerciseReps>> getAllExerciseReps() {
        mAllExerciseReps = mRepository.getExerciseReps();
        return mAllExerciseReps;
    }

    public void insert(ExerciseReps exerciseReps) { mRepository.insert(exerciseReps); }
}
