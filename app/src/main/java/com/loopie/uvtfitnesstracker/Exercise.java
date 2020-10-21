package com.loopie.uvtfitnesstracker;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercise_table")
public class Exercise {
    @PrimaryKey
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
