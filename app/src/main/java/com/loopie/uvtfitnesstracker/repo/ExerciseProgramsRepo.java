package com.loopie.uvtfitnesstracker.repo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.loopie.uvtfitnesstracker.dao.ExerciseDao;
import com.loopie.uvtfitnesstracker.dao.ExerciseProgramsDao;
import com.loopie.uvtfitnesstracker.models.Exercise;
import com.loopie.uvtfitnesstracker.models.ExerciseProgramsMany;
import com.loopie.uvtfitnesstracker.models.ProgramsWithExercises;

import java.util.List;

public class ExerciseProgramsRepo {
    private ExerciseProgramsDao mExerciseProgramsDao;
    private LiveData<List<Exercise>> mAllExercisePrograms;

    public ExerciseProgramsRepo(Application application) {
        RoomMyDatabase db = RoomMyDatabase.getDatabase(application);
        mExerciseProgramsDao = db.exerciseProgramsDao();
    }

    public LiveData<List<Exercise>> getAllExercisePrograms(long exID) {
        mAllExercisePrograms = mExerciseProgramsDao.getProgramsWithExercises(exID);
        return mAllExercisePrograms;
    }

    public void insert(ExerciseProgramsMany exercise) {
        RoomMyDatabase.databaseWriteExecutor.execute(() -> {
            mExerciseProgramsDao.insert(exercise);
        });
    }
}
