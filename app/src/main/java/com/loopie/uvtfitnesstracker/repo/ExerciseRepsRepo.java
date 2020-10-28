package com.loopie.uvtfitnesstracker.repo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.loopie.uvtfitnesstracker.dao.ExerciseDao;
import com.loopie.uvtfitnesstracker.dao.ExerciseRepsDao;
import com.loopie.uvtfitnesstracker.models.Exercise;
import com.loopie.uvtfitnesstracker.models.ExerciseReps;

import java.util.List;

public class ExerciseRepsRepo {
    private ExerciseRepsDao mExerciseRepsDao;
    private LiveData<List<ExerciseReps>> mAllExerciseReps;

    public ExerciseRepsRepo(Application application) {
        RoomMyDatabase db = RoomMyDatabase.getDatabase(application);
        mExerciseRepsDao = db.exerciseRepsDao();
        mAllExerciseReps = mExerciseRepsDao.getExerciseReps();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
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
