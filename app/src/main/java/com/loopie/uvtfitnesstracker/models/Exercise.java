package com.loopie.uvtfitnesstracker.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercise_table")
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    private long id_exercise;
    @NonNull
    @ColumnInfo(name = "name")
    private String name;
    @NonNull
    @ColumnInfo(name = "imgURL")
    public String imgURL;

    public Exercise(String name, String imgURL) {
        this.name = name;
        this.imgURL = imgURL;
    }

    public long getId_exercise() { return id_exercise; }
    public void setId_exercise(long id_exercise) { this.id_exercise = id_exercise; }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getimgURL() {
        return this.imgURL;
    }
    public void setimgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
