package com.loopie.uvtfitnesstracker.views;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.loopie.uvtfitnesstracker.models.SubPrograms;
import com.loopie.uvtfitnesstracker.repo.SubProgramsRepo;

import java.util.List;

public class SubProgramsViewModel extends AndroidViewModel {
    private SubProgramsRepo mRepository;

    private LiveData<List<SubPrograms>> mAllSubPrograms;

    public SubProgramsViewModel(Application application) {
        super(application);
        mRepository = new SubProgramsRepo(application);
    }

    public LiveData<List<SubPrograms>> getsubProgramsById(long fkProgramId) {
        mAllSubPrograms = mRepository.getSubProgramsById(fkProgramId);
        return mAllSubPrograms;
    }

    public void insert(SubPrograms subprogram) { mRepository.insert(subprogram); }
}
