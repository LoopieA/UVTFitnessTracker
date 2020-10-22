package com.loopie.uvtfitnesstracker.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.loopie.uvtfitnesstracker.models.ProgramsWithExercises;

import java.util.List;

@Dao
public interface ExerciseProgramsDao {
    @Transaction
    @Query("SELECT * FROM programs_table")
    public List<ProgramsWithExercises> getProgramsWithExercises();
}
