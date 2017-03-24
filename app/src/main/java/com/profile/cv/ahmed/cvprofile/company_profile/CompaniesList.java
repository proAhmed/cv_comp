package com.profile.cv.ahmed.cvprofile.company_profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.profile.cv.ahmed.cvprofile.R;
import com.profile.cv.ahmed.cvprofile.adapter.CompanyAdapter;
import com.profile.cv.ahmed.cvprofile.api.GetCompany;
import com.profile.cv.ahmed.cvprofile.interfaces.OnDepartmentClick;
import com.profile.cv.ahmed.cvprofile.interfaces.OnLoadingComplete;
import com.profile.cv.ahmed.cvprofile.main.MainActivity;
import com.profile.cv.ahmed.cvprofile.model.CompanyModel;
import com.profile.cv.ahmed.cvprofile.model.CompanyModelMain;

import java.util.ArrayList;

/**
 * Created by ahmed on 1/2/2017.
 */
public class CompaniesList extends Fragment implements OnDepartmentClick {

    RecyclerView reDepartment;
    ArrayList<CompanyModel> companyModelArrayList;
    OnDepartmentClick onDepartmentClick;
    OnLoadingComplete onLoadingComplete;
    CompaniesList companiesList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.department,container,false);
        onDepartmentClick = this;
        companiesList = this;
        initiateRecycle(view);

        return view;
    }

    private void initiateRecycle(View view) {
        reDepartment = (RecyclerView) view.findViewById(R.id.reDepartment);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        reDepartment.setLayoutManager(linearLayoutManager);
        companyModelArrayList = new ArrayList<>();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(reDepartment.getContext(),
                linearLayoutManager.getOrientation());
        reDepartment.addItemDecoration(dividerItemDecoration);
        addData();
        MainActivity mainActivity = (MainActivity) getActivity();
        ImageView imageView = (ImageView) mainActivity.findViewById(R.id.imgBack);
        imageView.setVisibility(View.VISIBLE);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(companiesList).commit();
            }
        });
        TextView textView = (TextView) mainActivity.findViewById(R.id.tvTitle);
        textView.setText(getResources().getString(R.string.companies));
    }
    private void addData(){
//        CompanyModel companyModel = new CompanyModel();
//        companyModel.setName("Company 1");
//        CompanyModel companyMode2 = new CompanyModel();
//        companyMode2.setName("Company 2");
//        CompanyModel companyMode3 = new CompanyModel();
//        companyMode3.setName("Company 3");
//        companyModelArrayList.add(0,companyModel);
//        companyModelArrayList.add(1,companyMode2);
//        companyModelArrayList.add(2,companyMode3);


        onLoadingComplete = new OnLoadingComplete() {
            @Override
            public void onSuccess(Object object) {
                CompanyModelMain companyModelMain = (CompanyModelMain) object;
                companyModelArrayList = companyModelMain.getData();
                reDepartment.setAdapter(new CompanyAdapter(getActivity(),companyModelArrayList,onDepartmentClick));
            }

            @Override
            public void onFailure() {

            }
        };
        GetCompany getCompany = new GetCompany(getActivity(),onLoadingComplete);
        getCompany.execute();
    }

    @Override
    public void onDep(int pos) {
        Intent intent = new Intent(getActivity(),ProfileCompany.class);
        intent.putExtra("id",companyModelArrayList.get(pos).getID());
        startActivity(intent);
    }
}
