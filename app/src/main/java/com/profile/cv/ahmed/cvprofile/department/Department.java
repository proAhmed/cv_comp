package com.profile.cv.ahmed.cvprofile.department;

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
import com.profile.cv.ahmed.cvprofile.adapter.DepartmentAdapter;
import com.profile.cv.ahmed.cvprofile.api.GetDepartment;
import com.profile.cv.ahmed.cvprofile.company_profile.CompaniesList;
import com.profile.cv.ahmed.cvprofile.controller.Utility;
import com.profile.cv.ahmed.cvprofile.interfaces.OnDepartmentClick;
import com.profile.cv.ahmed.cvprofile.interfaces.OnLoadingComplete;
import com.profile.cv.ahmed.cvprofile.main.MainActivity;
import com.profile.cv.ahmed.cvprofile.model.DepartmentModel;
import com.profile.cv.ahmed.cvprofile.model.DepartmentModelMain;
import com.profile.cv.ahmed.cvprofile.model.DepartmentsModel;

import java.util.ArrayList;

/**
 * Created by ahmed on 1/2/2017.
 */
public class Department extends Fragment implements OnDepartmentClick {

    RecyclerView reDepartment;
    ArrayList<DepartmentModel> departmentModelArrayList;
    OnDepartmentClick onDepartmentClick;
    OnLoadingComplete onLoadingComplete;
    Department department;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.department,container,false);
        onDepartmentClick = this;
        department= this;
        initiateRecycle(view);

        return view;
    }

    private void initiateRecycle(View view) {
        reDepartment = (RecyclerView) view.findViewById(R.id.reDepartment);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        reDepartment.setLayoutManager(linearLayoutManager);
        departmentModelArrayList = new ArrayList<>();


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(reDepartment.getContext(),
                linearLayoutManager.getOrientation());
        reDepartment.addItemDecoration(dividerItemDecoration);

        addData();

    }
    private void addData(){
        DepartmentsModel departmentModel = new DepartmentsModel();
        departmentModel.setName("Department 1");
        DepartmentsModel departmentMode2 = new DepartmentsModel();
        departmentMode2.setName("Department 2");
        DepartmentsModel departmentMode3 = new DepartmentsModel();
        departmentMode3.setName("Department 3");
//        departmentModelArrayList.add(0,departmentModel);
//        departmentModelArrayList.add(1,departmentMode2);
//        departmentModelArrayList.add(2,departmentMode3);
        reDepartment.setAdapter(new DepartmentAdapter(getActivity(),departmentModelArrayList,onDepartmentClick));
        if (Utility.isNetworkConnected(getActivity())) {
        onLoadingComplete = new OnLoadingComplete() {
            @Override
            public void onSuccess(Object object) {
                DepartmentModelMain departmentModelMain = (DepartmentModelMain) object;
                departmentModelArrayList = departmentModelMain.getData();
                reDepartment.setAdapter(new DepartmentAdapter(getActivity(),departmentModelArrayList,onDepartmentClick));
            }

            @Override
            public void onFailure() {

            }
        };
            GetDepartment task= new  GetDepartment(getActivity(), onLoadingComplete);
            task.execute();
        }else{
            Utility.showValidateDialog(
                    getResources().getString(R.string.failure_ws),
                    getActivity());
        }

        MainActivity mainActivity = (MainActivity) getActivity();
        ImageView imageView = (ImageView) mainActivity.findViewById(R.id.imgBack);
        imageView.setVisibility(View.VISIBLE);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(department).commit();
            }
        });
        TextView textView = (TextView) mainActivity.findViewById(R.id.tvTitle);
        textView.setText(getResources().getString(R.string.department));
    }

    @Override
    public void onDep(int pos) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.main,new CompaniesList()).addToBackStack("").commit();

    }


}
