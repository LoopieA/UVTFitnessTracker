package com.loopie.uvtfitnesstracker.models;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class ProgramsWithExercises{
    @Embedded public Programs program;
    @Relation(
            parentColumn = "programsid",
            entityColumn = "id_exercise",
            associateBy = @Junction(
                    value = ExerciseProgramsMany.class)
            )
    public List<Exercise> exercises;
}
