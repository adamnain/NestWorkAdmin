package com.sebasaku.nestworkadmin.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sebasaku.nestworkadmin.R;

public class LoginActivity extends AppCompatActivity {

    Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialized();
        actionClicked();
    }
    public void initialized(){
        Login = findViewById(R.id.btn_login);
    }
    public void actionClicked(){
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,MenuActivity.class);
                startActivity(i);
            }
        });
    }
}
