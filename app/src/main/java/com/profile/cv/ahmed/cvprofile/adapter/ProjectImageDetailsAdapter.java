package com.profile.cv.ahmed.cvprofile.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.profile.cv.ahmed.cvprofile.R;
import com.profile.cv.ahmed.cvprofile.controller.RoundRectCornerImageView;
import com.profile.cv.ahmed.cvprofile.interfaces.OnImageClick;

import java.util.ArrayList;

/**
 * Created by ahmed on 7/19/2016.
 */
public class ProjectImageDetailsAdapter extends RecyclerView
        .Adapter<ProjectImageDetailsAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
     private ArrayList<Integer> mDataset;
     Activity context;
    OnImageClick onImageClick;
     public static class DataObjectHolder extends RecyclerView.ViewHolder
    {
        RoundRectCornerImageView img;
        public DataObjectHolder(View itemView) {
            super(itemView);
            img = (RoundRectCornerImageView) itemView.findViewById(R.id.imgItem);
            Log.i(LOG_TAG, "Adding Listener");
        }
    }

    public ProjectImageDetailsAdapter(Activity context, ArrayList<Integer> myDataset, OnImageClick onImageClick ) {
        this.mDataset = myDataset;
        this.context = context;
        this.onImageClick = onImageClick;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_project_details_items, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        holder.img.setImageResource(mDataset.get(position));
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onImageClick.onImage(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
