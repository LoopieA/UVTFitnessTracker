package com.loopie.uvtfitnesstracker.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "exercise_reps_table")
public class ExerciseReps {
    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "ex_id")
    public long ex_id;
    @ColumnInfo(name="Reps")
    public String reps;
    @ColumnInfo(name="Weight")
    public String weight;
    @ColumnInfo(name="repDate")
    public Date repDate;

    public ExerciseReps(long ex_id, String reps, String weight, Date repDate) {
        this.ex_id = ex_id;
        this.reps = reps;
        this.weight = weight;
        this.repDate = repDate;
    }
    public long getId() {
        return this.ex_id;
    }
    public void setId(long id) {
        this.ex_id = id;
    }
    public String getReps() {
        return this.reps;
    }
    public void setReps(String reps) {
        this.reps = reps;
    }
    public String getWeight() {
        return this.weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
    public Date getRepDate() {
        return this.repDate;
    }
    public void setRepDate(Date repDate) {
        this.repDate = repDate;
    }
}
