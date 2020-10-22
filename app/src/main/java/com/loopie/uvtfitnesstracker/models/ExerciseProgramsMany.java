package com.loopie.uvtfitnesstracker.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName="exerciseprograms_table", primaryKeys = {"id_exercise", "programsid"})
public class ExerciseProgramsMany{
    public long id_exercise;
    public long programsid;
}
