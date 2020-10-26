package com.loopie.uvtfitnesstracker.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "programs_table")
public class Programs {
    @PrimaryKey(autoGenerate = true)
    private long programsid;
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    public Programs(String name) {
        this.name = name;
    }

    public long getProgramsid() {
        return this.programsid;
    }
    public void setProgramsid(long programsid) {
        this.programsid = programsid;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
