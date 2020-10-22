package com.loopie.uvtfitnesstracker.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.loopie.uvtfitnesstracker.models.Programs;

import java.util.List;

@Dao
public abstract class ProgramsDao {
    @Query("SELECT * from programs_table")
    abstract LiveData<List<Programs>> getPrograms();
}
