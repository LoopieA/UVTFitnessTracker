package com.loopie.uvtfitnesstracker.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.loopie.uvtfitnesstracker.models.Exercise;

import java.util.List;

@Dao
public interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Exercise exercise);

    @Query("DELETE FROM exercise_table")
    void deleteAll();

    @Query("SELECT * from exercise_table")
    LiveData<List<Exercise>> getExercises();
}
