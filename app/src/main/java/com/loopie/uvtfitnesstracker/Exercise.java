package com.loopie.uvtfitnesstracker;

public class Exercise {
    private String name;
    private String imgURL;

    public Exercise(String name, String imgURL) {
        this.name = name;
        this.imgURL = imgURL;
    }

    public String getName() {
        return name;
    }

    public String getimgURL() {
        return imgURL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setimgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
