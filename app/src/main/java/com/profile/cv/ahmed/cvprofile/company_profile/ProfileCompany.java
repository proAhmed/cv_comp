package com.profile.cv.ahmed.cvprofile.company_profile;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.intrusoft.squint.DiagonalView;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.profile.cv.ahmed.cvprofile.R;
import com.profile.cv.ahmed.cvprofile.adapter.BranchAdapter;
import com.profile.cv.ahmed.cvprofile.adapter.ContactImageAdapter;
import com.profile.cv.ahmed.cvprofile.adapter.ImageAdapter;
import com.profile.cv.ahmed.cvprofile.adapter.ProjectImageAdapter;
import com.profile.cv.ahmed.cvprofile.api.GetSingleCompany;
import com.profile.cv.ahmed.cvprofile.controller.StoreData;
import com.profile.cv.ahmed.cvprofile.controller.Utility;
import com.profile.cv.ahmed.cvprofile.interfaces.OnImageClick;
import com.profile.cv.ahmed.cvprofile.interfaces.OnLoadingComplete;
import com.profile.cv.ahmed.cvprofile.interfaces.OnSocialClick;
import com.profile.cv.ahmed.cvprofile.main.MapsActivity;
import com.profile.cv.ahmed.cvprofile.model.CompanySingleMain;
import com.profile.cv.ahmed.cvprofile.model.CompanySingleModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProfileCompany extends AppCompatActivity implements OnImageClick,OnSocialClick {
    RecyclerView reProject,reBranches,reContactUs;
    ArrayList<Integer>arrayList;
    ArrayList<String>arrayListBranch;
    ArrayList<Integer>arrayListSocial;
    ImageView imgBack,imgCall;
    OnLoadingComplete onLoadingComplete;
     CompanySingleModel  companySingleModel;
    ArrayList<String> social;
    OnSocialClick onSocialClick;
    OnImageClick onImageClick;
    DiagonalView diagonalView;
    CircularImageView circularImageView;
    int id;
    TextView tvTitle,tvDescription,tvPhoneNo,tvEmail;
    LinearLayout linViewMap;

    int projects;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utility utility = new Utility();
        utility.langChoosen(this,new StoreData(this).getLang());
        setContentView(R.layout.activity_profile_company);
        initiateRecycle();
        id = getIntent().getExtras().getInt("id",0);
        try {
            if (getIntent().getExtras().getString("projects").equals("projects")) {
                projects=1;
                pos = getIntent().getExtras().getInt("pos");
            }
        }catch (Exception e){

        }
            add();
    }
    private void initiateRecycle() {
        social = new ArrayList<>();
        onSocialClick = this;
        onImageClick = this;
        imgBack = (ImageView) findViewById(R.id.imgBack);
        imgCall = (ImageView) findViewById(R.id.imgCall);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Utility utility = new Utility();
                utility.confirmDialog(ProfileCompany.this,companySingleModel.getPhone());
            }
        });

        linViewMap = (LinearLayout) findViewById(R.id.linViewMap);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        diagonalView = (DiagonalView) findViewById(R.id.diagonal);
        circularImageView = (CircularImageView) findViewById(R.id.circularImageView);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvPhoneNo = (TextView) findViewById(R.id.tvPhoneNo);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        reProject = (RecyclerView) findViewById(R.id.reProject);
        reBranches = (RecyclerView) findViewById(R.id.reBranches);
        reContactUs = (RecyclerView) findViewById(R.id.reContactUs);
        reProject.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        reBranches.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        reContactUs.setLayoutManager(new GridLayoutManager(this, 2));
        init();
        initBranch();
        initSocial();
        tvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailintent = new Intent(android.content.Intent.ACTION_SEND);
                emailintent.setType("plain/text");
                emailintent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] {companySingleModel.getEmail() });
                emailintent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
                emailintent.putExtra(android.content.Intent.EXTRA_TEXT,"");
                startActivity(Intent.createChooser(emailintent, "Send mail..."));
            }
        });
        reContactUs.setAdapter(new ContactImageAdapter(this,arrayListSocial,onSocialClick));
    }
    private void init(){
        arrayList = new ArrayList<>();
        linViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double lat = companySingleModel.getLatitude();
                double lng = companySingleModel.getLongitude();
                Intent intent = new Intent(ProfileCompany.this, MapsActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lng",lng);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onImage(int pos) {
        Intent intent = new Intent(this,Projects.class);
        intent.putExtra("data",companySingleModel.getProjects().get(pos));
         startActivity(intent);


    }

    private void initBranch(){
        arrayListBranch = new ArrayList<>();
        arrayListBranch.add("Cairo");
        arrayListBranch.add("Kuwit");
        arrayListBranch.add("Suadi");
    }

    private void initSocial(){
        arrayListSocial = new ArrayList<>();
        arrayListSocial.add(R.drawable.facebook);
        arrayListSocial.add(R.drawable.twitter);
        arrayListSocial.add(R.drawable.youtube);
        arrayListSocial.add(R.drawable.insta);
    }

    private void add(){
        onLoadingComplete = new OnLoadingComplete() {
            @Override
            public void onSuccess(Object object) {
                CompanySingleMain companySingleMain = (CompanySingleMain) object;
                companySingleModel = companySingleMain.getData();

                social.add(companySingleModel.getFacebook());
                social.add(companySingleModel.getTwitter());
                social.add(companySingleModel.getYoutube());
                social.add(companySingleModel.getInstagram());
                reProject.setAdapter(new ProjectImageAdapter(ProfileCompany.this,companySingleModel.getProjects(),onImageClick));
                reBranches.setAdapter(new BranchAdapter(ProfileCompany.this,companySingleModel.getBranches(),onImageClick));
                tvTitle.setText(companySingleModel.getName());
                tvDescription.setText(companySingleModel.getDescription());
                tvEmail.setText(companySingleModel.getEmail());
                tvPhoneNo.setText(companySingleModel.getPhone());
                Picasso.with(ProfileCompany.this).load("http://cv-campany.com/API/ar/general/thumb?url="
                        +companySingleModel.getPicture() + "&width="+ Utility.widthScreen(ProfileCompany.this)[0]+"&height="+Utility.widthScreen(ProfileCompany.this)[1]/3)
                        .into(diagonalView);
                Picasso.with(ProfileCompany.this).load("http://cv-campany.com/API/ar/general/thumb?url="
                        +companySingleModel.getPicture() + "&width="+ Utility.widthScreen(ProfileCompany.this)[0]/4+"&height="+
                        Utility.widthScreen(ProfileCompany.this)[0]/4)
                        .into(circularImageView);
                if(projects==1){
                    try {
                        Intent intent = new Intent(ProfileCompany.this, Projects.class);
                        intent.putExtra("data", companySingleModel.getProjects().get(pos));
                        startActivity(intent);
                    }catch (Exception e){

                    }
                }
            }

            @Override
            public void onFailure() {

            }
        };
        GetSingleCompany getSingleCompany = new GetSingleCompany(ProfileCompany.this,onLoadingComplete,id);
        getSingleCompany.execute();
    }


    @Override
    public void onSocial(int pos) {
        if (pos==0){
            String face;
            Intent intent = null;
            PackageManager packageManager = getPackageManager();
            try {
                int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
                if (versionCode >= 3002850) { //newer versions of fb app
                    face =  "fb://facewebmodal/f?href=" + companySingleModel.getFacebook();

                } else { //older versions of fb app
                    face = "fb://page/" + companySingleModel.getFacebook();
                }
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = face;
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            } catch (Exception e) {
                // no Twitter app, revert to browser

                 new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"+companySingleModel.getFacebook()));
            }


        }else  if (pos==1){
            Intent intent = null;
            try {
                // get the Twitter app if possible
                getPackageManager().getPackageInfo("com.twitter.android", 0);
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id="+companySingleModel.getTwitter()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            } catch (Exception e) {
                // no Twitter app, revert to browser
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/"+companySingleModel.getTwitter()));
            }
            this.startActivity(intent);
        }else  if (pos==2){
            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + companySingleModel.getYoutube()));
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + companySingleModel.getYoutube()));
            try {
                startActivity(appIntent);
            } catch (ActivityNotFoundException ex) {
                startActivity(webIntent);
            }
        }else  if (pos==3){
            Uri uri = Uri.parse("http://instagram.com/_u/"+ companySingleModel.getInstagram());
            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

            likeIng.setPackage("com.instagram.android");

            try {
                startActivity(likeIng);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://instagram.com/"+ companySingleModel.getInstagram())));
            }
        }
    }
}
