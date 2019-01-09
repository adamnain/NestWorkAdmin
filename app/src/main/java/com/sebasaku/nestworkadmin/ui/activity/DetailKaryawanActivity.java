package com.sebasaku.nestworkadmin.ui.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.widget.TextView;

import com.sebasaku.nestworkadmin.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailKaryawanActivity extends AppCompatActivity {

    CircleImageView avaUser;
    TextView namaUser, jobUser, email, tanggalLahir, hp;
    CardView cardView;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_karyawan);

        backButton();
        intializedObject();
        getIntentData();

        avaUser.setElevation(10);
        cardView.setElevation(5);


    }
    private void getIntentData(){
        avaUser.setImageResource(getIntent().getIntExtra("avaUser",0));
        namaUser.setText(getIntent().getStringExtra("namaUser"));
        jobUser.setText(getIntent().getStringExtra("posisi"));
        email.setText(getIntent().getStringExtra("email"));
        //tanggalLahir.setText(getIntent().getStringExtra("tanggalLahir"));
        hp.setText(getIntent().getStringExtra("hp"));
    }

    public void intializedObject(){
        avaUser = (CircleImageView) findViewById(R.id.avaUser);
        namaUser = (TextView) findViewById(R.id.namaUser);
        cardView = (CardView) findViewById(R.id.cardView);
        jobUser = (TextView) findViewById(R.id.jobUser);
        email = (TextView) findViewById(R.id.email);
        //tanggalLahir = (TextView) findViewById(R.id.tanggalLahir);
        hp = (TextView) findViewById(R.id.hp);

    }

    //untuk enampilkan back button
    public void backButton(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Karyawan");
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
