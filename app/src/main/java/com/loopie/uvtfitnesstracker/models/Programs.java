package com.loopie.uvtfitnesstracker.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "programs_table")
public class Programs {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "programsid")
    private long programsid;
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    public Programs(long id, String name) {
        this.programsid = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
