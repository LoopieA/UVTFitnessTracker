package com.loopie.uvtfitnesstracker.views;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.loopie.uvtfitnesstracker.models.Programs;
import com.loopie.uvtfitnesstracker.repo.ProgramsRepo;

import java.util.List;

public class ProgramsViewModel extends AndroidViewModel {
    private ProgramsRepo mRepository;

    private LiveData<List<Programs>> mAllPrograms;

    public ProgramsViewModel(Application application) {
        super(application);
        mRepository = new ProgramsRepo(application);
        mAllPrograms = mRepository.getAllPrograms();
    }

    public LiveData<List<Programs>> getAllPrograms() { return mAllPrograms; }

    public void insert(Programs program) { mRepository.insert(program); }
}
