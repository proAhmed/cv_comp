package com.profile.cv.ahmed.cvprofile.model;

import java.util.ArrayList;

/**
 * Created by ahmed on 2/19/2017.
 */
public class CompanySingleMain {

     private String success;
     private Object error;
      CompanySingleModel data;

    public CompanySingleMain() {
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

    public CompanySingleModel  getData() {
        return data;
    }

    public void setData( CompanySingleModel data) {
        this.data = data;
    }
}
