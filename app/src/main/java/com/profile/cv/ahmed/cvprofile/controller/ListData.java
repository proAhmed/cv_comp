package com.profile.cv.ahmed.cvprofile.controller;

import android.app.Activity;

import com.profile.cv.ahmed.cvprofile.R;
import com.profile.cv.ahmed.cvprofile.model.MenuModel;

import java.util.ArrayList;

/**
 * Created by ahmed on 2/26/2017.
 */
public class ListData {

    public ArrayList<MenuModel> slideData(Activity activity){
        ArrayList<MenuModel> arrayList = new ArrayList<>();
        arrayList.add(new MenuModel(activity.getResources().getString(R.string.social),0));
        arrayList.add(new MenuModel( activity.getResources().getString(R.string.fac)  , R.drawable.facebooks));
        arrayList.add(new MenuModel(activity.getResources().getString(R.string.twitter),R.drawable.twitters));
        arrayList.add(new MenuModel(activity.getResources().getString(R.string.youtube),R.drawable.youtubes));
        arrayList.add(new MenuModel(activity.getResources().getString(R.string.instagram),R.drawable.instagrame));
        arrayList.add(new MenuModel(activity.getResources().getString(R.string.customer_service),0));

        return arrayList;
    }
}
