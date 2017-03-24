package com.profile.cv.ahmed.cvprofile.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.profile.cv.ahmed.cvprofile.R;
import com.profile.cv.ahmed.cvprofile.controller.Utility;
import com.profile.cv.ahmed.cvprofile.interfaces.OnImageClick;
import com.profile.cv.ahmed.cvprofile.model.SlideModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



/**
 * Created by tesark on 18/8/16.
 */
public class OfferViewPagerAdapter extends PagerAdapter {
    Context context;
    ArrayList<SlideModel> offerList;
    OnImageClick onImageClick;
    public OfferViewPagerAdapter(Context context, ArrayList<SlideModel> offerList, OnImageClick onImageClick) {
        this.context = context;
        this.offerList = offerList;
        this.onImageClick = onImageClick;

    }

    @Override
    public int getCount() {
        return offerList.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.image_pager_items, container, false);
        ImageView imageView = (ImageView) v.findViewById(R.id.imgItem);
        Picasso.with(context).load("http://cv-campany.com/API/ar/general/thumb?url="
                + offerList.get(position).getPicture() + "&width="+ Utility.widthScreenHome(context)[0]+"&height="+Utility.widthScreenHome(context)[1]/3
        )
                .into(imageView);
        //  Picasso.with(context).load("http://vente1paris.com/API/fr/general/thumb?url=" + offerList.get(position).getPicture()+"&width=350&height=350").into(imageView);
         imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
   onImageClick.onImage(position);
            }
        });
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
