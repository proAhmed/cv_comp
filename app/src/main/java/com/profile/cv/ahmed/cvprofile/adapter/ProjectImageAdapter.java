package com.profile.cv.ahmed.cvprofile.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.profile.cv.ahmed.cvprofile.R;
import com.profile.cv.ahmed.cvprofile.controller.RoundRectCornerImageView;
import com.profile.cv.ahmed.cvprofile.controller.RoundedImageView;
import com.profile.cv.ahmed.cvprofile.controller.Utility;
import com.profile.cv.ahmed.cvprofile.interfaces.OnImageClick;
import com.profile.cv.ahmed.cvprofile.model.ProjectsClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ahmed on 7/19/2016.
 */
public class ProjectImageAdapter extends RecyclerView
        .Adapter<ProjectImageAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
     private ArrayList<ProjectsClass> mDataset;
     Activity context;
    OnImageClick onImageClick;
     public static class DataObjectHolder extends RecyclerView.ViewHolder
    {
        CircularImageView img;
        public DataObjectHolder(View itemView) {
            super(itemView);
            img = (CircularImageView) itemView.findViewById(R.id.imgProjects);
            Log.i(LOG_TAG, "Adding Listener");
        }
    }

    public ProjectImageAdapter(Activity context, ArrayList<ProjectsClass> myDataset,OnImageClick onImageClick ) {
        this.mDataset = myDataset;
        this.context = context;
        this.onImageClick = onImageClick;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.projects_items, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        Picasso.with(context).load("http://cv-campany.com/API/ar/general/thumb?url="
                + mDataset.get(position).getPicture() + "&width="+ Utility.widthScreen(context)[0]/5+"&height="+Utility.widthScreen(context)[0]/5)
                .into(holder.img);

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
