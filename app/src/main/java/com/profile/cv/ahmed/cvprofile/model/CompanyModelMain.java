package com.profile.cv.ahmed.cvprofile.model;

import java.util.ArrayList;

/**
 * Created by ahmed on 2/19/2017.
 */
public class CompanyModelMain {

     private String success;
     private Object error;
     private ArrayList<CompanyModel> data;

    public CompanyModelMain() {
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

    public ArrayList<CompanyModel> getData() {
        return data;
    }

    public void setData(ArrayList<CompanyModel> data) {
        this.data = data;
    }
}
