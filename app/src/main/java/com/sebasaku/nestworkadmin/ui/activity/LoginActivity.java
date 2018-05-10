package com.sebasaku.nestworkadmin.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sebasaku.nestworkadmin.ui.SessionManager;
import com.sebasaku.nestworkadmin.api.model.TokenLogin;
import com.sebasaku.nestworkadmin.api.model.Login;
import com.sebasaku.nestworkadmin.api.model.Token;
import com.sebasaku.nestworkadmin.R;
import com.sebasaku.nestworkadmin.api.service.UtilsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    SessionManager session;
    UtilsApi utilsApi;
    Button login;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialized();
        actionClicked();
        session  = new SessionManager(getApplicationContext());
        session.checkLoginPage();

    }

    public void initialized(){
        login = findViewById(R.id.btn_login);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
    }

    public void actionClicked(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em = email.getText().toString();
                String pass = password.getText().toString();
                login(em, pass);
            }
        });
    }

    private static String token;

    private void login(String em, String pass){
        utilsApi = new UtilsApi();
        Login login = new Login(em, pass);
        Call<TokenLogin> call = utilsApi.getAPIService().login(login);
        call.enqueue(new Callback<TokenLogin>() {
            @Override
            public void onResponse(Call<TokenLogin> call, Response<TokenLogin> response) {
                if(response.isSuccessful()){
                    Token token = response.body().getToken();
                    String accesToken = token.getAccessToken();
                    session.createLoginSession(accesToken);
                    //String id = response.body().getToken().toString();
                    //Toast.makeText(LoginActivity.this, accesToken, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this,DashboardActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(LoginActivity.this, "login not correct", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TokenLogin> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

    }



}
