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
import com.sebasaku.nestworkadmin.api.model.SlipGaji;
import com.sebasaku.nestworkadmin.api.service.UtilsApi;
import com.sebasaku.nestworkadmin.ui.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSlipActivity extends AppCompatActivity {

    TextView tvNamaKaryawan, tvSesiGaji;
    EditText etJumlahTask, etGaji;
    Button btnKirimSlip;
    UtilsApi utilsApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_slip);

        backButton();
        intializedObject();
        getIntentData();
        actionClicked();
    }

    private void getIntentData(){
        tvNamaKaryawan.setText(getIntent().getStringExtra("email"));

        //parse string
        String tgl = getIntent().getStringExtra("createdAt");
        String[]parts = tgl.split("T");
        String tgls = parts[0];
        String[]partss = tgls.split("-");
        String tglss = partss[2];
        String bln = partss[1];
        int tanggal = Integer.parseInt(tglss);
        int bulan = Integer.parseInt(bln);
        String sesi = "";

        if (bulan == 1){
            sesi = sesi+"Januari ";
        }
        else if (bulan == 2){
            sesi = sesi+"Februari ";

        }
        else if (bulan == 3){
            sesi = sesi+"Maret ";

        }
        else if (bulan == 4){
            sesi = sesi+"April ";

        }
        else if (bulan == 5){
            sesi = sesi+"Mei ";

        }
        else if (bulan == 6){
            sesi = sesi+"Juni ";

        }
        else if (bulan == 7){
            sesi = sesi+"Juli ";

        }
        else if (bulan == 8){
            sesi = sesi+"Agustus ";

        }
        else if (bulan == 9){
            sesi = sesi+"September ";

        }
        else if (bulan == 10){
            sesi = sesi+"Oktober ";

        }
        else if (bulan == 11){
            sesi = sesi+"November ";

        }
        else if (bulan == 12){
            sesi = sesi+"Desember ";

        }

        if (tanggal<=15){
            sesi = sesi+"Sesi Satu";
            tvSesiGaji.setText(sesi);
        }
        else if (tanggal > 15){
            sesi = sesi+"Sesi Dua";
            tvSesiGaji.setText(sesi);
        }

    }

    private void intializedObject(){
        tvNamaKaryawan = findViewById(R.id.tv_nama_detailSlip);
        tvSesiGaji = findViewById(R.id.tv_sesi_detailSlip);
        etJumlahTask = findViewById(R.id.et_jumlahTask_detailSlip);
        etGaji = findViewById(R.id.et_totalGaji_detailSlip);
        btnKirimSlip = findViewById(R.id.btn_kirimSLip_detailSlip);
    }

    private void actionClicked(){
        btnKirimSlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });
    }

    private void updateData(){
        utilsApi = new UtilsApi();
        SessionManager userPref = new SessionManager(getApplicationContext());
        final String accesToken = userPref.getAccesToken();
        final String id = getIntent().getStringExtra("id");
        final String status = "1";
        final String email = getIntent().getStringExtra("email");
        final String waktu = getIntent().getStringExtra("waktu");
        final String createdAt = getIntent().getStringExtra("createdAt");
        final String updatedAt = getIntent().getStringExtra("updatedAt");
        final int jumlahTask = Integer.parseInt(etJumlahTask.getText().toString());
        final int gaji = Integer.parseInt(etGaji.getText().toString());
        final int v = getIntent().getIntExtra("v", 0);
        SlipGaji slipGaji = new SlipGaji(status, jumlahTask, id, email, waktu, gaji, createdAt, updatedAt, v);

        Call<List<SlipGaji>> call = UtilsApi.getAPIService().updateSlipKaryawan("Bearer "+accesToken, id, slipGaji );
        call.enqueue(new Callback<List<SlipGaji>>() {
            @Override
            public void onResponse(Call<List<SlipGaji>> call, Response<List<SlipGaji>> response) {
                if(response.isSuccessful()){
                    Intent i = new Intent(DetailSlipActivity.this,DashboardActivity.class);
                    Toast.makeText(DetailSlipActivity.this, "Data berhasil Diupdate", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                }
                else{
                    Toast.makeText(DetailSlipActivity.this, "login not correct", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SlipGaji>> call, Throwable t) {

            }
        });
    }



    private void backButton(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Kirim Slip Gaji");
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
