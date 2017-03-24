package com.profile.cv.ahmed.cvprofile.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.profile.cv.ahmed.cvprofile.R;
import com.profile.cv.ahmed.cvprofile.controller.StoreData;
import com.profile.cv.ahmed.cvprofile.controller.Utility;

public class LangActivity extends AppCompatActivity {
    RelativeLayout reArabic,reEnglish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utility utility = new Utility();
        utility.langChoosen(LangActivity.this,new StoreData(this).getLang());
        setContentView(R.layout.activity_lang);

        reArabic = (RelativeLayout) findViewById(R.id.reArabic);
        reEnglish = (RelativeLayout) findViewById(R.id.reEnglish);

        reArabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new StoreData(LangActivity.this).setLang("ar");
                Intent intent = new Intent(LangActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        reEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new StoreData(LangActivity.this).setLang("en");
                Intent intent = new Intent(LangActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
