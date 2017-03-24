package com.profile.cv.ahmed.cvprofile.adapter;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.profile.cv.ahmed.cvprofile.R;
import com.profile.cv.ahmed.cvprofile.interfaces.OnImageClick;
import com.profile.cv.ahmed.cvprofile.interfaces.OnMenuClick;
import com.profile.cv.ahmed.cvprofile.model.BranchesClass;
import com.profile.cv.ahmed.cvprofile.model.MenuModel;

import java.util.ArrayList;

/**
 * Created by ahmed on 7/19/2016.
 */
public class ListMenuAdapter extends RecyclerView
        .Adapter<ListMenuAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
     private ArrayList<MenuModel> mDataset;
     Activity context;
    OnMenuClick onImageClick;
     public static class DataObjectHolder extends RecyclerView.ViewHolder
    {
        TextView textView;
        ImageView imgMenu;
        LinearLayout lin;
        public DataObjectHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tvMenuName);
            imgMenu = (ImageView) itemView.findViewById(R.id.imgMenu);
            lin = (LinearLayout) itemView.findViewById(R.id.lin);
            Log.i(LOG_TAG, "Adding Listener");
        }
    }

    public ListMenuAdapter(Activity context, ArrayList<MenuModel> myDataset, OnMenuClick onImageClick ) {
        this.mDataset = myDataset;
        this.context = context;
        this.onImageClick = onImageClick;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_items, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        holder.textView.setText(mDataset.get(position).getTitle());
        holder.imgMenu.setImageResource(mDataset.get(position).getImage());
        holder.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onImageClick.onMenu(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
