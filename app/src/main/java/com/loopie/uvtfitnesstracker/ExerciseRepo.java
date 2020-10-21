package com.loopie.uvtfitnesstracker;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ExerciseRepo {
    private ExerciseDao mExerciseDao;
    private LiveData<List<Exercise>> mAllExercises;

    ExerciseRepo(Application application) {

        RoomMyDatabase db = RoomMyDatabase.getDatabase(application);
        mExerciseDao = db.exerciseDao();
        mAllExercises = mExerciseDao.getExercises();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Exercise>> getAllWords() {
        return mAllExercises;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Exercise exercise) {
        RoomMyDatabase.databaseWriteExecutor.execute(() -> {
            mExerciseDao.insert(exercise);
        });
    }
}
