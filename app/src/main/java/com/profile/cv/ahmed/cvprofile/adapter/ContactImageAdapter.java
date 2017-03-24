package com.profile.cv.ahmed.cvprofile.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.profile.cv.ahmed.cvprofile.R;
import com.profile.cv.ahmed.cvprofile.interfaces.OnSocialClick;

import java.util.ArrayList;

/**
 * Created by ahmed on 7/19/2016.
 */
public class ContactImageAdapter extends RecyclerView
        .Adapter<ContactImageAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
     private ArrayList<Integer> mDataset;
     Activity context;
    OnSocialClick onSocialClick;
    public static class DataObjectHolder extends RecyclerView.ViewHolder
    {
        ImageView img;
        public DataObjectHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.imgItem);
            Log.i(LOG_TAG, "Adding Listener");
        }
    }

    public ContactImageAdapter(Activity context, ArrayList<Integer> myDataset, OnSocialClick onSocialClick) {
        this.mDataset = myDataset;
        this.context = context;
        this.onSocialClick = onSocialClick;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_contact_items, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        holder.img.setImageResource(mDataset.get(position));
        holder.img.setOnClickListener(view -> onSocialClick.onSocial(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
