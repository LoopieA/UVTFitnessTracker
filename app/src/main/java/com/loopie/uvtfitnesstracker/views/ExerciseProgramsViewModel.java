package com.loopie.uvtfitnesstracker.views;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.loopie.uvtfitnesstracker.models.Exercise;
import com.loopie.uvtfitnesstracker.models.ExerciseProgramsMany;
import com.loopie.uvtfitnesstracker.repo.ExerciseProgramsRepo;
import com.loopie.uvtfitnesstracker.repo.ExerciseRepo;

import java.util.List;

public class ExerciseProgramsViewModel extends AndroidViewModel {
    private ExerciseProgramsRepo mRepository;

    private LiveData<List<Exercise>> mAllExercises;

    public ExerciseProgramsViewModel(Application application) {
        super(application);
        mRepository = new ExerciseProgramsRepo(application);

    }

    public LiveData<List<Exercise>> getAllExercises(long exID) {
        mAllExercises = mRepository.getAllExercisePrograms(exID);
        return mAllExercises;
    }

    public void insert(ExerciseProgramsMany exercise) { mRepository.insert(exercise); }
    public void delete(long exID, long subProgID) { mRepository.delete(exID, subProgID); }
}
