package com.loopie.uvtfitnesstracker.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.loopie.uvtfitnesstracker.models.Exercise;
import com.loopie.uvtfitnesstracker.models.Programs;

import java.util.List;

@Dao
public interface ProgramsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Programs program);

    @Query("SELECT * from programs_table")
    LiveData<List<Programs>> getPrograms();
}
