package com.loopie.uvtfitnesstracker.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.loopie.uvtfitnesstracker.models.Exercise;
import com.loopie.uvtfitnesstracker.models.ExerciseProgramsMany;
import com.loopie.uvtfitnesstracker.models.ProgramsWithExercises;

import java.util.List;

@Dao
public interface ExerciseProgramsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ExerciseProgramsMany exerciseprograms);

    @Query("DELETE from exercise_programs_table WHERE id_exercise=:exID AND programsid=:subProgID")
    void delete(long exID, long subProgID);

    @Transaction
    @Query("SELECT * from exercise_table JOIN exercise_programs_table ON exercise_table.id_exercise = exercise_programs_table.id_exercise WHERE exercise_programs_table.programsid=:exID")
    LiveData<List<Exercise>> getProgramsWithExercises(long exID);
}
