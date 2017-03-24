package com.profile.cv.ahmed.cvprofile.model;

import java.util.ArrayList;

/**
 * Created by ahmed on 2/19/2017.
 */
public class OfferSingleMain {

     private String success;
     private Object error;
      OfferModel data;

    public OfferSingleMain() {
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

    public  OfferModel  getData() {
        return data;
    }

    public void setData( OfferModel data) {
        this.data = data;
    }
}
