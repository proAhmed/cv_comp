package com.profile.cv.ahmed.cvprofile.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.profile.cv.ahmed.cvprofile.R;
import com.profile.cv.ahmed.cvprofile.controller.Utility;
import com.profile.cv.ahmed.cvprofile.interfaces.OnDepartmentClick;
import com.profile.cv.ahmed.cvprofile.model.CompanyModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ahmed on 7/19/2016.
 */
public class BrandAdapter extends RecyclerView
        .Adapter<BrandAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
     private ArrayList<CompanyModel> mDataset;
    OnDepartmentClick onDepartmentClick;
     Activity context;

     public static class DataObjectHolder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView tvBrandName;
        LinearLayout lin;
        public DataObjectHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.imgItem);
            tvBrandName = (TextView) itemView.findViewById(R.id.tvBrandName);
            lin = (LinearLayout) itemView.findViewById(R.id.lin);
            Log.i(LOG_TAG, "Adding Listener");
        }
    }

    public BrandAdapter(Activity context, ArrayList<CompanyModel> myDataset, OnDepartmentClick onDepartmentClick) {
        this.mDataset = myDataset;
        this.context = context;
        this.onDepartmentClick = onDepartmentClick;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.brands_items, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {

        holder.tvBrandName.setText(mDataset.get(position).getName());
        Picasso.with(context).load("http://cv-campany.com/API/ar/general/thumb?url="
                + mDataset.get(position).getPicture() + "&width="+ Utility.widthScreen(context)[0]/2+"&height="+ Utility.widthScreen(context)[1]/6)
                .into(holder.img);
        holder.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDepartmentClick.onDep(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
