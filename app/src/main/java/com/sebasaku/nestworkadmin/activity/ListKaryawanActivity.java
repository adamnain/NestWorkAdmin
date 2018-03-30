package com.sebasaku.nestworkadmin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sebasaku.nestworkadmin.adapter.ListKaryawanAdapter;
import com.sebasaku.nestworkadmin.api.model.AllUser;
import com.sebasaku.nestworkadmin.api.model.TokenLogin;
import com.sebasaku.nestworkadmin.api.service.EndPoints;
import com.sebasaku.nestworkadmin.api.service.UtilsApi;
import com.sebasaku.nestworkadmin.model.Karyawan;
import com.sebasaku.nestworkadmin.R;

import java.util.LinkedList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by adam on 2/23/18.
 */

public class ListKaryawanActivity extends AppCompatActivity {
    private final LinkedList<AllUser> listKaryawan = new LinkedList<>();

    private RecyclerView mRecyclerView;
    private ListKaryawanAdapter mAdapter;
    FloatingActionButton fab;
    UtilsApi utilsApi = new UtilsApi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_karyawan);

        initialized();
        backButton();
        actionClicked();

        Call<AllUser> call = utilsApi.getAPIService().getAllUser("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1MjQ5MTA2MzYsImlhdCI6MTUyMjMxODYzNiwic3ViIjoiNWFiY2I2ZDhkMTQ2YWI2OTJmY2UzZGIwIn0.m2Hzc2xAb9tB4ERVMwX9oMyuXh6JHfP6tAoDlZI2mHE");
        call.enqueue(new Callback<AllUser>() {
            @Override
            public void onResponse(Call<AllUser> call, Response<AllUser> response) {
                if (response.isSuccessful()){
                    String id = response.body().getId();
                    String email = response.body().getEmail();
                    String nama = response.body().getNama();
                    String posisi = response.body().getPosisi();
                    String noHp = response.body().getNoHp();
                    int gaji = response.body().getGaji();
                    String createdAt = response.body().getCreatedAt();
                    String level = response.body().getLevel();
                    String picture = response.body().getPicture();

                    listKaryawan.add(new AllUser(id, email, nama, posisi, noHp, gaji, createdAt, level, picture));
                    //listKaryawan.addLast(new Karyawan(, R.drawable.jesica));
                    Toast.makeText(ListKaryawanActivity.this, "harusnya bener", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(ListKaryawanActivity.this, "not correct", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AllUser> call, Throwable t) {
                Toast.makeText(ListKaryawanActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

        //set data
        //listKaryawan.addLast(new A("Lorem Ipsum", R.drawable.ic_history));


        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list_karyawan);

        mAdapter = new ListKaryawanAdapter(getApplicationContext(), listKaryawan);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    //untuk enampilkan back button
    public void backButton(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("List Karyawan");
    }

    //fungsi back ketika tombol back diklik
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void initialized(){
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    public void actionClicked(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListKaryawanActivity.this, TambahKaryawanActivity.class);
                startActivity(i);
            }
        });
    }

}
