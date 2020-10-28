package com.loopie.uvtfitnesstracker.repo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.loopie.uvtfitnesstracker.dao.ExerciseDao;
import com.loopie.uvtfitnesstracker.dao.ExerciseRepsDao;
import com.loopie.uvtfitnesstracker.models.Exercise;
import com.loopie.uvtfitnesstracker.models.ExerciseReps;
import com.loopie.uvtfitnesstracker.models.SubPrograms;

import java.util.List;

public class ExerciseRepsRepo {
    private ExerciseRepsDao mExerciseRepsDao;
    private LiveData<List<ExerciseReps>> mAllExerciseReps;
    private LiveData<List<ExerciseReps>> mFilteredExerciseReps;

    public ExerciseRepsRepo(Application application) {
        RoomMyDatabase db = RoomMyDatabase.getDatabase(application);
        mExerciseRepsDao = db.exerciseRepsDao();
        mAllExerciseReps = mExerciseRepsDao.getExerciseReps();
    }

    public LiveData<List<ExerciseReps>> getFilteredExerciseReps(long exerciseID) {
        mFilteredExerciseReps = mExerciseRepsDao.getFilteredExerciseReps(exerciseID);
        return mFilteredExerciseReps;
    }

    public LiveData<List<ExerciseReps>> getExerciseReps() {
        return mAllExerciseReps;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(ExerciseReps exerciseReps) {
        RoomMyDatabase.databaseWriteExecutor.execute(() -> {
            mExerciseRepsDao.insert(exerciseReps);
        });
    }
}
