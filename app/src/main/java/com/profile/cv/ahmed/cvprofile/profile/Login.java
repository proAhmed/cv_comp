package com.profile.cv.ahmed.cvprofile.profile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.profile.cv.ahmed.cvprofile.R;
import com.profile.cv.ahmed.cvprofile.interfaces.OnLoadingComplete;
import com.profile.cv.ahmed.cvprofile.main.MainActivity;

public class Login extends AppCompatActivity {

    EditText edPassword,edUserName;
    Button btnLogin;
    OnLoadingComplete onLoadingComplete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        declare();
        action();
    }

    private void declare(){
        btnLogin = (Button) findViewById(R.id.btnLogin);
        edPassword = (EditText) findViewById(R.id.edPassword);
        edUserName = (EditText) findViewById(R.id.edUserName);

    }
    private void action(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void signIn(){
        onLoadingComplete = new OnLoadingComplete() {
            @Override
            public void onSuccess(Object object) {

            }

            @Override
            public void onFailure() {

            }
        };
    }
}
