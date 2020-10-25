package com.loopie.uvtfitnesstracker.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.loopie.uvtfitnesstracker.models.SubPrograms;

import java.util.List;

@Dao
public interface SubProgramsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(SubPrograms subprogram);

    @Query("SELECT * from subprograms_table WHERE fkProgramid LIKE :fkProgram")
    LiveData<List<SubPrograms>> getsubProgramsById(long fkProgram);

    @Query("SELECT * from subprograms_table")
    LiveData<List<SubPrograms>> getsubPrograms();
}
