package com.profile.cv.ahmed.cvprofile.model;

import java.util.ArrayList;

/**
 * Created by ahmed on 2/19/2017.
 */
public class HomeModelMain {
    private String success;
    private Object error;
    private ArrayList<HomeModel> data;


    public HomeModelMain() {
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public ArrayList<HomeModel> getData() {
        return data;
    }

    public void setData(ArrayList<HomeModel> data) {
        this.data = data;
    }
}
