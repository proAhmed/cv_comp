package com.profile.cv.ahmed.cvprofile.offers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.profile.cv.ahmed.cvprofile.R;
import com.profile.cv.ahmed.cvprofile.adapter.OffersAdapter;
import com.profile.cv.ahmed.cvprofile.api.GetOffer;
import com.profile.cv.ahmed.cvprofile.interfaces.OnLoadingComplete;
import com.profile.cv.ahmed.cvprofile.interfaces.OnOfferClick;
import com.profile.cv.ahmed.cvprofile.main.MainActivity;
import com.profile.cv.ahmed.cvprofile.model.OfferModel;
import com.profile.cv.ahmed.cvprofile.model.OfferModelMain;

import java.util.ArrayList;

/**
 * Created by ahmed on 1/2/2017.
 */
public class Offers extends Fragment implements OnOfferClick {

    RecyclerView reOffer;
    ArrayList<OfferModel>offerModelArrayList;
    OnOfferClick onOfferClick;
    OnLoadingComplete onLoadingComplete;
    Offers offers;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.offers,container,false);
        onOfferClick = this;
        offers = this;
        initiateRecycle(view);
        return view;
    }

    private void initiateRecycle(View view) {
        reOffer = (RecyclerView) view.findViewById(R.id.reOffer);
        reOffer.setLayoutManager(new LinearLayoutManager(getActivity()));
        offerModelArrayList = new ArrayList<>();

        MainActivity mainActivity = (MainActivity) getActivity();
        ImageView  imageView = (ImageView) mainActivity.findViewById(R.id.imgBack);
        imageView.setVisibility(View.VISIBLE);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    getActivity().getSupportFragmentManager().beginTransaction().remove(offers).commit();

                }catch (Exception e){

                }
            }
        });
        TextView textView = (TextView) mainActivity.findViewById(R.id.tvTitle);
        textView.setText(getResources().getString(R.string.offers));
        addData();

    }
        private void addData(){


            onLoadingComplete = new OnLoadingComplete() {
                @Override
                public void onSuccess(Object object) {
                    OfferModelMain offerModelMain = (OfferModelMain) object;
                    offerModelArrayList = offerModelMain.getData();
                    reOffer.setAdapter(new OffersAdapter(getActivity(),offerModelArrayList,onOfferClick));

                }

                @Override
                public void onFailure() {

                }
            };
            GetOffer task= new  GetOffer(getActivity(), onLoadingComplete);
            task.execute();
        }

    @Override
    public void onOffer(int pos) {
        Intent intent = new Intent(getActivity(),OfferDetails.class);
        intent.putExtra("id",offerModelArrayList.get(pos).getID());
        startActivity(intent);
    }
}
