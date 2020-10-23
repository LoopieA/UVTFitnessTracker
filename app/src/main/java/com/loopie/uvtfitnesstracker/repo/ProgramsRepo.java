package com.loopie.uvtfitnesstracker.repo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.loopie.uvtfitnesstracker.dao.ProgramsDao;
import com.loopie.uvtfitnesstracker.models.Programs;

import java.util.List;

public class ProgramsRepo {
    private ProgramsDao mProgramsDao;
    private LiveData<List<Programs>> mAllPrograms;

    public ProgramsRepo(Application application) {

        RoomMyDatabase db = RoomMyDatabase.getDatabase(application);
        mProgramsDao = db.programsDao();
        mAllPrograms = mProgramsDao.getPrograms();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Programs>> getAllPrograms() {
        return mAllPrograms;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Programs program) {
        RoomMyDatabase.databaseWriteExecutor.execute(() -> {
            mProgramsDao.insert(program);
        });
    }
}
