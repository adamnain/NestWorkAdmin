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
import com.sebasaku.nestworkadmin.model.Karyawan;
import com.sebasaku.nestworkadmin.R;

import java.util.LinkedList;

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
    private final LinkedList<Karyawan> listKaryawan = new LinkedList<>();

    private RecyclerView mRecyclerView;
    private ListKaryawanAdapter mAdapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_karyawan);

        initialized();
        backButton();
        actionClicked();
        //retrofit
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://192.168.56.1:3000/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        EndPoints endPoints = retrofit.create(EndPoints.class);

        //final AllUser allUser = new AllUser();
        Call<ResponseBody> call = endPoints.getAllUser("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1MjQwNTkwMDcsImlhdCI6MTUyMTQ2NzAwNywic3ViIjoiNWFhNjY1N2Q0MzRiMjg0NDBlNGNhYThiIn0.2waDJts8RrstGYwtIJiKZu6I6EUPeV34Yff--M_wAXk");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    //String email = response.body().
                    listKaryawan.addLast(new Karyawan(email, R.drawable.jesica));
                }
                else {
                    Toast.makeText(ListKaryawanActivity.this, "not correct", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ListKaryawanActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

        //set data
        listKaryawan.addLast(new Karyawan("Lorem Ipsum", R.drawable.ic_history));


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
