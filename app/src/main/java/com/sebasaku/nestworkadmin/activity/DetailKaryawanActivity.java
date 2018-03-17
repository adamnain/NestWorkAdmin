package com.sebasaku.nestworkadmin.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.widget.TextView;

import com.sebasaku.nestworkadmin.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailKaryawanActivity extends AppCompatActivity {

    CircleImageView avaUser;
    TextView namaUser;
    CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_karyawan);

        backButton();
        intializedObject();

        avaUser.setElevation(10);
        cardView.setElevation(5);

        avaUser.setImageResource(getIntent().getIntExtra("avaUser",0));
        namaUser.setText(getIntent().getStringExtra("namaUser"));
    }

    public void intializedObject(){
        avaUser = (CircleImageView) findViewById(R.id.avaUser);
        namaUser = (TextView) findViewById(R.id.namaUser);
        cardView = (CardView) findViewById(R.id.cardView);
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
