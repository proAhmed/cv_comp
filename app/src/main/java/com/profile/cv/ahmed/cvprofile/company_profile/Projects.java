package com.profile.cv.ahmed.cvprofile.company_profile;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.profile.cv.ahmed.cvprofile.R;
import com.profile.cv.ahmed.cvprofile.adapter.ImageAdapter;
import com.profile.cv.ahmed.cvprofile.adapter.ProjectImageAdapter;
import com.profile.cv.ahmed.cvprofile.adapter.ProjectImageDetailsAdapter;
import com.profile.cv.ahmed.cvprofile.controller.StoreData;
import com.profile.cv.ahmed.cvprofile.controller.Utility;
import com.profile.cv.ahmed.cvprofile.interfaces.OnImageClick;
import com.profile.cv.ahmed.cvprofile.interfaces.OnLoadingComplete;
import com.profile.cv.ahmed.cvprofile.model.ProjectsClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Projects extends Activity implements OnImageClick{
   RecyclerView reImage;
    ArrayList<Integer>arrayList;
    Button btnBack;
     ProjectsClass projectsClass;
    ImageView imgProject;
    TextView tvTitle,tvDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utility utility = new Utility();
        utility.langChoosen(this,new StoreData(this).getLang());
        setContentView(R.layout.activity_projects);
        projectsClass = getIntent().getExtras().getParcelable("data");
        assert projectsClass != null;
        Log.d("ppp",projectsClass.getName());
        initialize();
    }

    private void initialize(){
        btnBack = (Button) findViewById(R.id.btnBack);
        imgProject = (ImageView) findViewById(R.id.imgProject);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        reImage = (RecyclerView) findViewById(R.id.reImage);
        reImage.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        init();

        Picasso.with(Projects.this).load("http://cv-campany.com/API/ar/general/thumb?url="
                +projectsClass.getPicture() + "&width="+ Utility.widthScreen(Projects.this)[0]+"&height="+Utility.widthScreen(Projects.this)[1]/2)
                .into(imgProject);

        tvTitle.setText(projectsClass.getName());
        tvDescription.setText(projectsClass.getDescription());
       // reImage.setAdapter(new ProjectImageDetailsAdapter(this,arrayList,this));
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void init(){
        arrayList = new ArrayList<>();
//        arrayList.add(R.drawable.work);
//        arrayList.add(R.drawable.work);
//        arrayList.add(R.drawable.work);

    }

    @Override
    public void onImage(int pos) {

    }
}
