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

import com.mikhaellopez.circularimageview.CircularImageView;
import com.profile.cv.ahmed.cvprofile.R;
import com.profile.cv.ahmed.cvprofile.interfaces.OnDepartmentClick;
import com.profile.cv.ahmed.cvprofile.model.CompanyModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ahmed on 7/19/2016.
 */
public class CompanyAdapter extends RecyclerView
        .Adapter<CompanyAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
     private ArrayList<CompanyModel> mDataset;
     Activity context;
    OnDepartmentClick onDepartmentClick;
    public static class DataObjectHolder extends RecyclerView.ViewHolder
    {
        CircularImageView img;
        TextView tvDepTitle;
        LinearLayout reDepartment;
        public DataObjectHolder(View itemView) {
            super(itemView);
            img = (CircularImageView) itemView.findViewById(R.id.imgDepartment);
            tvDepTitle = (TextView) itemView.findViewById(R.id.tvDepTitle);
            reDepartment  = (LinearLayout) itemView.findViewById(R.id.reDepartment);
            Log.i(LOG_TAG, "Adding Listener");
        }
    }

    public CompanyAdapter(Activity context, ArrayList<CompanyModel> myDataset, OnDepartmentClick onDepartmentClick) {
        this.mDataset = myDataset;
        this.context = context;
        this.onDepartmentClick = onDepartmentClick;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.department_items, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {

        holder.tvDepTitle.setText(mDataset.get(position).getName());
        holder.reDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDepartmentClick.onDep(position);
            }
        });

        Picasso.with(context).load("http://cv-campany.com/API/ar/general/thumb?url=" + mDataset.get(position).getPicture() + "&width=140&height=140")
                .into(holder.img);


    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
