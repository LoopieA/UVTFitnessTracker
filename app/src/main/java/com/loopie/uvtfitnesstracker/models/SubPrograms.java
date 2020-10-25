package com.loopie.uvtfitnesstracker.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "subprograms_table")
public class SubPrograms {
    @PrimaryKey(autoGenerate = true)
    public long subprogramsid;
    @NonNull
    @ColumnInfo(name = "name")
    public String name;
    @NonNull
    @ColumnInfo(name = "fkProgramid")
    public long fkProgramid;

    public SubPrograms(String name, long fkProgramid) {
        this.name = name;
        this.fkProgramid = fkProgramid;
    }

    public long getsubprogramsid() {
        return this.subprogramsid;
    }
    public void setsubprogramsid(long subprogramsid) {
        this.subprogramsid = subprogramsid;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public long getfkProgramid() {
        return this.fkProgramid;
    }
    public void setfkProgramid(long fkProgramid) {
        this.fkProgramid = fkProgramid;
    }
}
