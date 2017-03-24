package com.profile.cv.ahmed.cvprofile.model;

import java.util.ArrayList;

/**
 * Created by ahmed on 2/19/2017.
 */
public class DepartmentModelMain {

     private String success;
     private Object error;
     private ArrayList<DepartmentModel> data;

    public DepartmentModelMain() {
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

    public ArrayList<DepartmentModel> getData() {
        return data;
    }

    public void setData(ArrayList<DepartmentModel> data) {
        this.data = data;
    }
}
