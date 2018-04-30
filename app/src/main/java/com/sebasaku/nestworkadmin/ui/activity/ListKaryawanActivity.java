package com.sebasaku.nestworkadmin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sebasaku.nestworkadmin.ui.SessionManager;
import com.sebasaku.nestworkadmin.ui.adapter.ListKaryawanAdapter;
import com.sebasaku.nestworkadmin.api.model.AllUser;
import com.sebasaku.nestworkadmin.api.service.UtilsApi;
import com.sebasaku.nestworkadmin.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by adam on 2/23/18.
 */

public class ListKaryawanActivity extends AppCompatActivity {
    private List<AllUser> listKaryawan;
    private RecyclerView mRecyclerView;
    private ListKaryawanAdapter mAdapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_karyawan);

        listKaryawan = new ArrayList<>();

        initialized();
        backButton();
        actionClicked();

        SessionManager userPref = new SessionManager(getApplicationContext());
        String accesToken = userPref.getAccesToken();
        //java.lang.String accesToken ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1MjUxNDcwMjcsImlhdCI6MTUyMjU1NTAyNywic3ViIjoiNWFhNjY1N2Q0MzRiMjg0NDBlNGNhYThiIn0.sl-IiWn4QWao_d2bzF6UW9-m9mIBPcIANyZAn5XGeks";

        Call<List<AllUser>> call = UtilsApi.getAPIService().getAllUser("Bearer "+accesToken);
        call.enqueue(new Callback<List<AllUser>>() {
            @Override
            public void onResponse(Call<List<AllUser>> call, Response<List<AllUser>> response) {
                Toast.makeText(ListKaryawanActivity.this, "harusnya bener", Toast.LENGTH_SHORT).show();
                if (response.code()==200){
                    List<AllUser> allUser = response.body();
                    for(int i = 0; i<allUser.size(); i++){
                        String id = allUser.get(i).getId();
                        String email = allUser.get(i).getEmail();
                        String nama = allUser.get(i).getNama();
                        String posisi = allUser.get(i).getPosisi();
                        String noHp = allUser.get(i).getNoHp();
                        int gaji = allUser.get(i).getGaji();
                        String createdAt = allUser.get(i).getCreatedAt();
                        listKaryawan.add(new AllUser(id, email, nama, posisi, noHp, gaji, createdAt));
                        mAdapter.notifyDataSetChanged();
                    }
                }
                else {
                    Toast.makeText(ListKaryawanActivity.this, "not correct", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AllUser>> call, Throwable t) {
                Toast.makeText(ListKaryawanActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });


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
