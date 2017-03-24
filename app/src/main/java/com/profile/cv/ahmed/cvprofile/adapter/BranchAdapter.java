package com.profile.cv.ahmed.cvprofile.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.profile.cv.ahmed.cvprofile.R;
import com.profile.cv.ahmed.cvprofile.interfaces.OnImageClick;
import com.profile.cv.ahmed.cvprofile.model.BranchesClass;

import java.util.ArrayList;

/**
 * Created by ahmed on 7/19/2016.
 */
public class BranchAdapter extends RecyclerView
        .Adapter<BranchAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
     private ArrayList<BranchesClass> mDataset;
     Activity context;
    OnImageClick onImageClick;
     public static class DataObjectHolder extends RecyclerView.ViewHolder
    {
        Button img;
        public DataObjectHolder(View itemView) {
            super(itemView);
            img = (Button) itemView.findViewById(R.id.btnBranch);
            Log.i(LOG_TAG, "Adding Listener");
        }
    }

    public BranchAdapter(Activity context, ArrayList<BranchesClass> myDataset, OnImageClick onImageClick ) {
        this.mDataset = myDataset;
        this.context = context;
        this.onImageClick = onImageClick;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.buttons_items, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        holder.img.setText(mDataset.get(position).getName());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // onImageClick.onImage(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
