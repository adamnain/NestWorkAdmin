package com.sebasaku.nestworkadmin.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sebasaku.nestworkadmin.R;
import com.sebasaku.nestworkadmin.api.model.Login;
import com.sebasaku.nestworkadmin.api.model.ResponsPerusahaan;
import com.sebasaku.nestworkadmin.api.model.Token;
import com.sebasaku.nestworkadmin.api.model.TokenLogin;
import com.sebasaku.nestworkadmin.api.service.UtilsApi;
import com.sebasaku.nestworkadmin.ui.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilPerusahaanActivity extends AppCompatActivity {

    EditText etNamaPerusahaan, etEmailPerusahaan, etProfilPerusahaan, etPeraturanPerusahaan;
    Button btnUpdate;
    UtilsApi utilsApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_perusahaan);
        initialized();
        actionCLicked();
        backButton();
    }

    public void initialized(){
        etNamaPerusahaan = findViewById(R.id.et_namaPerusahaan);
        etEmailPerusahaan = findViewById(R.id.et_emailPerusahaan);
        etProfilPerusahaan = findViewById(R.id.et_profilPerusahaan);
        etPeraturanPerusahaan = findViewById(R.id.et_peraturanPerusahaan);
        btnUpdate = findViewById(R.id.btn_updatePerusahaan);
    }

    public void actionCLicked(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePerusahaan();
            }
        });
    }

    public void updatePerusahaan(){
        utilsApi = new UtilsApi();
        SessionManager userPref = new SessionManager(getApplicationContext());
        final String accesToken = userPref.getAccesToken();
        ResponsPerusahaan responsPerusahaan = new ResponsPerusahaan("5af2dedd0d94d40c07dda019",etNamaPerusahaan.getText().toString(), etEmailPerusahaan.getText().toString(), etProfilPerusahaan.getText().toString(), etPeraturanPerusahaan.getText().toString());
        Call<ResponsPerusahaan> call = utilsApi.getAPIService().editProfil("Bearer "+accesToken,responsPerusahaan);
        call.enqueue(new Callback<ResponsPerusahaan>() {
            @Override
            public void onResponse(Call<ResponsPerusahaan> call, Response<ResponsPerusahaan> response) {
                if(response.isSuccessful()){
                    Intent i = new Intent(ProfilPerusahaanActivity.this,MenuActivity.class);
                    Toast.makeText(ProfilPerusahaanActivity.this, "Data berhasil Diupdate", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                }
                else{
                    Toast.makeText(ProfilPerusahaanActivity.this, "login not correct", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsPerusahaan> call, Throwable t) {
                Toast.makeText(ProfilPerusahaanActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //untuk enampilkan back button
    public void backButton(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profil Perusahaan");
    }

}
