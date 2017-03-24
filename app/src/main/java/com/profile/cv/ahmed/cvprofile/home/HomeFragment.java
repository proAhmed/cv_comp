package com.profile.cv.ahmed.cvprofile.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.profile.cv.ahmed.cvprofile.R;
import com.profile.cv.ahmed.cvprofile.adapter.AdverAdapter;
import com.profile.cv.ahmed.cvprofile.adapter.BrandAdapter;
import com.profile.cv.ahmed.cvprofile.adapter.ImageAdapter;
import com.profile.cv.ahmed.cvprofile.adapter.OfferViewPagerAdapter;
import com.profile.cv.ahmed.cvprofile.adapter.ProjectImageDetailsAdapter;
import com.profile.cv.ahmed.cvprofile.api.GetCompany;
import com.profile.cv.ahmed.cvprofile.api.GetHome;
import com.profile.cv.ahmed.cvprofile.api.GetHomeSlide;
import com.profile.cv.ahmed.cvprofile.company_profile.ProfileCompany;
import com.profile.cv.ahmed.cvprofile.company_profile.Projects;
import com.profile.cv.ahmed.cvprofile.interfaces.OnAdvertClick;
import com.profile.cv.ahmed.cvprofile.interfaces.OnDepartmentClick;
import com.profile.cv.ahmed.cvprofile.interfaces.OnImageClick;
import com.profile.cv.ahmed.cvprofile.interfaces.OnLoadingComplete;
import com.profile.cv.ahmed.cvprofile.main.MainActivity;
import com.profile.cv.ahmed.cvprofile.model.CompanyModel;
import com.profile.cv.ahmed.cvprofile.model.CompanyModelMain;
import com.profile.cv.ahmed.cvprofile.model.HomeModel;
import com.profile.cv.ahmed.cvprofile.model.HomeModelMain;
import com.profile.cv.ahmed.cvprofile.model.SlideModel;
import com.profile.cv.ahmed.cvprofile.model.SlideModelMain;

import java.util.ArrayList;

/**
 * Created by ahmed on 1/3/2017.
 */
public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener,OnImageClick,OnAdvertClick,OnDepartmentClick {

    RecyclerView reBrand,reAdvertising;
    ArrayList<Integer>arrayList,arrayListBrands;
    ArrayList<Integer>arrayListMain;
    ViewPager viewPager;
    LinearLayout page_indicator;
    int dotscount;
    ImageView[] dots;
    OfferViewPagerAdapter viewPagerAdapter;
    ViewPager.OnPageChangeListener vOnPageChangeListener;
    OnLoadingComplete onLoadingComplete;
    ArrayList<SlideModel> sliderArrayList;
    ArrayList<HomeModel> homeModelArrayList;
    ArrayList<CompanyModel> companyModelArrayList;
    OnImageClick onImageClick;
    OnAdvertClick onAdvertClick;
    OnDepartmentClick onDepartmentClick;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);
        initiateRecycle(view);
        return view;
    }

    private void initiateRecycle(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        page_indicator = (LinearLayout) view.findViewById(R.id.viewPagerCountDots);
        reBrand = (RecyclerView) view.findViewById(R.id.reBrand);
        reAdvertising = (RecyclerView) view.findViewById(R.id.reAdvertising);
        reBrand.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
        vOnPageChangeListener = this;
        onImageClick = this;
        onAdvertClick = this;
        onDepartmentClick = this;
        initialize();
        reAdvertising.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        reAdvertising.setAdapter(new ImageAdapter(getActivity(),arrayList));
        homeBrands();
        homeSlider();
        add();
    }

    private void initialize(){
        init();
        initMain();

    }
    private void initMain(){
        arrayListMain = new ArrayList<>();
        sliderArrayList = new ArrayList<>();
        homeModelArrayList = new ArrayList<>();
        companyModelArrayList = new ArrayList<>();
        arrayListMain.add(R.drawable.slide1);
        arrayListMain.add(R.drawable.slide3);
        arrayListMain.add(R.drawable.slide1);
        arrayListMain.add(R.drawable.slide3);
    }
    private void init(){
        arrayList = new ArrayList<>();

        arrayListBrands = new ArrayList<>();
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.findViewById(R.id.imgBack).setVisibility(View.INVISIBLE);
       TextView textView = (TextView) mainActivity.findViewById(R.id.tvTitle);
        textView.setText(R.string.home);

    }

    private void setUiPageViewController() {
        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i< dotscount; i++){
            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.circle_2));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(2, 0, 2, 0);

            page_indicator.addView(dots[i], params);
        }
        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.circle_1));
    }

    @Override
    public void onImage(int pos) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dotscount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.circle_2));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.circle_1));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    private void add(){
        onLoadingComplete = new OnLoadingComplete() {
            @Override
            public void onSuccess(Object object) {
                HomeModelMain homeModelMain = (HomeModelMain) object;
                homeModelArrayList = homeModelMain.getData();
                reAdvertising.setAdapter(new AdverAdapter(getActivity(),homeModelArrayList,onAdvertClick));
            }

            @Override
            public void onFailure() {

            }
        };
        GetHome getHome = new GetHome(getActivity(),onLoadingComplete);
        getHome.execute();

    }

    private void homeSlider(){
        onLoadingComplete = new OnLoadingComplete() {
            @Override
            public void onSuccess(Object object) {
                SlideModelMain slideModelMain = (SlideModelMain) object;
                sliderArrayList =  slideModelMain.getData();
                viewPagerAdapter = new OfferViewPagerAdapter(getActivity(), sliderArrayList,onImageClick);

                //    reBrand.setAdapter(new ProjectImageDetailsAdapter(getActivity(),arrayList,this));
                try{
                    setUiPageViewController();

                }catch (Exception e){
                    Log.d("ooo",e.toString());
                }
                viewPager.setAdapter(viewPagerAdapter);
                viewPager.setCurrentItem(0);
                viewPager.setOnPageChangeListener(vOnPageChangeListener);
            }

            @Override
            public void onFailure() {

            }
        };
        GetHomeSlide getHomeSlide = new GetHomeSlide(getActivity(),onLoadingComplete);
        getHomeSlide.execute();
    }

    private void homeBrands(){
        onLoadingComplete = new OnLoadingComplete() {
            @Override
            public void onSuccess(Object object) {
                CompanyModelMain companyModelMain = (CompanyModelMain) object;
                companyModelArrayList =  companyModelMain.getData();
                reBrand.setAdapter(new BrandAdapter(getActivity(),companyModelArrayList,onDepartmentClick));


            }

            @Override
            public void onFailure() {

            }
        };
        GetCompany getCompany = new GetCompany(getActivity(),onLoadingComplete);
        getCompany.execute();
    }

    @Override
    public void onAdvert(int pos) {
        Intent intent = new Intent(getActivity(), ProfileCompany.class);
        intent.putExtra("id",homeModelArrayList.get(pos).getCompanyID());
        intent.putExtra("projects","projects");
        intent.putExtra("pos",pos);

        startActivity(intent);
    }

    @Override
    public void onDep(int pos) {
        Intent intent = new Intent(getActivity(),ProfileCompany.class);
        intent.putExtra("id",companyModelArrayList.get(pos).getCompanyID());
        startActivity(intent);
    }
}
