package com.profile.cv.ahmed.cvprofile.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.profile.cv.ahmed.cvprofile.R;
import com.profile.cv.ahmed.cvprofile.controller.Utility;
import com.profile.cv.ahmed.cvprofile.interfaces.OnAdvertClick;
import com.profile.cv.ahmed.cvprofile.model.HomeModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ahmed on 7/19/2016.
 */
public class AdverAdapter extends RecyclerView
        .Adapter<AdverAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
     private ArrayList<HomeModel> mDataset;
    OnAdvertClick onAdvertClick;
     Activity context;
     public static class DataObjectHolder extends RecyclerView.ViewHolder
    {
        ImageView img;
        public DataObjectHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.imgItem);
            Log.i(LOG_TAG, "Adding Listener");
        }
    }

    public AdverAdapter(Activity context, ArrayList<HomeModel> myDataset, OnAdvertClick onAdvertClick) {
        this.mDataset = myDataset;
        this.context = context;
        this.onAdvertClick = onAdvertClick;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_items, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        Picasso.with(context).load("http://cv-campany.com/API/ar/general/thumb?url="
                + mDataset.get(position).getPicture() + "&width="+ Utility.widthScreen(context)[0]/2+"&height="+Utility.widthScreen(context)[1]/3)
                .into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAdvertClick.onAdvert(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
