package com.sebasaku.nestworkadmin.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sebasaku.nestworkadmin.R;
import com.sebasaku.nestworkadmin.api.model.ResponsPerusahaan;
import com.sebasaku.nestworkadmin.api.model.SlipGaji;
import com.sebasaku.nestworkadmin.api.service.UtilsApi;
import com.sebasaku.nestworkadmin.ui.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilPerusahaanActivity extends AppCompatActivity {

    EditText etNamaPerusahaan, etEmailPerusahaan, etProfilPerusahaan, etPeraturanPerusahaan;
    Button btnUpdate;
    UtilsApi utilsApi;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_perusahaan);
        initialized();
        actionCLicked();
        backButton();
        getDataPerusahaan();
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
        ResponsPerusahaan responsPerusahaan = new ResponsPerusahaan(id,etNamaPerusahaan.getText().toString(), etEmailPerusahaan.getText().toString(), etProfilPerusahaan.getText().toString(), etPeraturanPerusahaan.getText().toString(), "2018-05-09T09:54:19.271Z", "2018-05-09T09:54:19.271Z", 0);
        Call<ResponsPerusahaan> call = utilsApi.getAPIService().editProfilPerusahaan("Bearer "+accesToken,responsPerusahaan, id);
        call.enqueue(new Callback<ResponsPerusahaan>() {
            @Override
            public void onResponse(Call<ResponsPerusahaan> call, Response<ResponsPerusahaan> response) {
                if(response.isSuccessful()){
                    Intent i = new Intent(ProfilPerusahaanActivity.this,DashboardActivity.class);
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

    private void getDataPerusahaan(){
        SessionManager userPref = new SessionManager(getApplicationContext());
        String accesToken = userPref.getAccesToken();

        Call<List<ResponsPerusahaan>> call = UtilsApi.getAPIService().getDataProfilPerusahaan("Bearer "+accesToken);
        call.enqueue(new Callback<List<ResponsPerusahaan>>() {
            @Override
            public void onResponse(Call<List<ResponsPerusahaan>> call, Response<List<ResponsPerusahaan>> response) {
                List<ResponsPerusahaan> profil = response.body();
                for(int i = 0; i<1; i++){
                    etNamaPerusahaan.setText(profil.get(i).getNamaPerusahaan());
                    etEmailPerusahaan.setText(profil.get(i).getEmail());
                    etProfilPerusahaan.setText(profil.get(i).getProfil());
                    etPeraturanPerusahaan.setText(profil.get(i).getProfil());
                    id = profil.get(i).getId();
                }

            }

            @Override
            public void onFailure(Call<List<ResponsPerusahaan>> call, Throwable t) {
                Toast.makeText(ProfilPerusahaanActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //untuk enampilkan back button
    public void backButton(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profil Perusahaan");
    }

    //fungsi back ketika tombol back diklik
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
