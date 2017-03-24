package com.profile.cv.ahmed.cvprofile.model;

/**
 * Created by ahmed on 2/26/2017.
 */
public class MenuModel {
    private String title;
    private int image;

    public MenuModel() {
    }

    public MenuModel(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
