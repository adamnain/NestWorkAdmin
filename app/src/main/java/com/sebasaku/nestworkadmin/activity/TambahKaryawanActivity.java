package com.sebasaku.nestworkadmin.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.sebasaku.nestworkadmin.R;
import com.sebasaku.nestworkadmin.api.model.RegisUser;
import com.sebasaku.nestworkadmin.api.model.Token;
import com.sebasaku.nestworkadmin.api.model.TokenLogin;
import com.sebasaku.nestworkadmin.api.model.User;
import com.sebasaku.nestworkadmin.api.service.UtilsApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahKaryawanActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText etNama, etTanggalLahir, etBidang, etEmail, etHp,  etPassword, etRepassword;
    Button btSubmit;
    Calendar myCalendar;
    UtilsApi utilsApi;
    RegisUser regisUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_karyawan);

        initialized();
        actionClicked();

    }


    public void actionClicked() {
        etTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(TambahKaryawanActivity.this, TambahKaryawanActivity.this, year,month,day);
                datePickerDialog.show();
            }
        });

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!isEmptyField(etNama.getText().toString()) || !isEmptyField(etTanggalLahir.getText().toString())
                        || !isEmptyField(etBidang.getText().toString()) || !isEmptyField(etEmail.getText().toString())
                        || !isEmptyField(etHp.getText().toString())){
                    Toast.makeText(TambahKaryawanActivity.this, "Harap legkapi semua data",Toast.LENGTH_SHORT).show();
                }
                if (!isValidateEmail(etEmail.getText().toString())){
                    Toast.makeText(TambahKaryawanActivity.this, "Email kosong atau salah",Toast.LENGTH_SHORT).show();
                }
                if (!isMatch(etPassword.getText().toString(),etRepassword.getText().toString())){
                    Toast.makeText(TambahKaryawanActivity.this, "Password dan Re-Password tidak sama",Toast.LENGTH_SHORT).show();
                }
                else{
                    tambahKaryawan();
                }




            }
        });

    }

    public void initialized(){
        myCalendar = Calendar.getInstance();
        etNama = findViewById(R.id.et_nama);
        etTanggalLahir = findViewById(R.id.et_tanggalLahir);
        etBidang = findViewById(R.id.et_bidang);
        etEmail = findViewById(R.id.et_email);
        etHp = findViewById(R.id.et_hp);
        etPassword = findViewById(R.id.et_password);
        etRepassword = findViewById(R.id.et_repassword);
        btSubmit = findViewById(R.id.bt_submit);
    }

    //date picker
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        int yearFinal = i;
        int monthFinal = i1+1;
        int dayFinal = i2;
        etTanggalLahir.setText(""+yearFinal+"-"+monthFinal+"-"+dayFinal);
    }

    private void tambahKaryawan(){
        utilsApi = new UtilsApi();
        regisUser = new RegisUser(etEmail.getText().toString(), etPassword.getText().toString(), etNama.getText().toString(), etBidang.getText().toString(), etTanggalLahir.getText().toString(), etHp.getText().toString(), "user", 0);
        Call<TokenLogin> call = utilsApi.getAPIService().regisUser(regisUser);
        call.enqueue(new Callback<TokenLogin>() {
            @Override
            public void onResponse(Call<TokenLogin> call, Response<TokenLogin> response) {
                if(response.isSuccessful()){
                    User user = response.body().getUser();
                    String nama = user.getNama();
                    Toast.makeText(TambahKaryawanActivity.this, "sukses", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(TambahKaryawanActivity.this, ListKaryawanActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(TambahKaryawanActivity.this, "gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TokenLogin> call, Throwable t) {
                Toast.makeText(TambahKaryawanActivity.this, "tidak menemukan koeneksi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //form validation
    private boolean isValidateEmail(String email){
        return !TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean isEmptyField(String yourField){
        return !TextUtils.isEmpty(yourField);
    }
    private boolean isMatch(String password, String retypePassword){
        return password.equals(retypePassword);
    }
}
