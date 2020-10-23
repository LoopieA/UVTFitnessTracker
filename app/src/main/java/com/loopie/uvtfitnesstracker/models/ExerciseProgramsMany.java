package com.loopie.uvtfitnesstracker.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName="exercise_programs_table")
public class ExerciseProgramsMany{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_exercise")
    public long id_exercise;
    @ColumnInfo(name = "programsid")
    public long programsid;

    public ExerciseProgramsMany(long id_exercise, long programsid) {
        this.id_exercise = id_exercise;
        this.programsid = programsid;
    }

    public long getId_exercise() { return id_exercise; }
    public long getProgramsid() { return programsid; }
}
