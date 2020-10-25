package com.loopie.uvtfitnesstracker.repo;
import android.app.Application;
import androidx.lifecycle.LiveData;
import com.loopie.uvtfitnesstracker.dao.SubProgramsDao;
import com.loopie.uvtfitnesstracker.models.SubPrograms;
import java.util.List;

public class SubProgramsRepo {
    private SubProgramsDao mSubProgramsDao;
    private LiveData<List<SubPrograms>> mAllSubPrograms;

    public SubProgramsRepo(Application application) {
        RoomMyDatabase db = RoomMyDatabase.getDatabase(application);
        mSubProgramsDao = db.subProgramsDao();

    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<SubPrograms>> getSubProgramsById(long fkProgramId) {
        mAllSubPrograms = mSubProgramsDao.getsubProgramsById(fkProgramId);
        return mAllSubPrograms;
    }

    public LiveData<List<SubPrograms>> getAllSubPrograms() {
        mAllSubPrograms = mSubProgramsDao.getsubPrograms();
        return mAllSubPrograms;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(SubPrograms subprogram) {
        RoomMyDatabase.databaseWriteExecutor.execute(() -> {
            mSubProgramsDao.insert(subprogram);
        });
    }
}
