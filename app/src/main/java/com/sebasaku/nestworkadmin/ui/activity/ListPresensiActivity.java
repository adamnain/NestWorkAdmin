package com.sebasaku.nestworkadmin.ui.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.sebasaku.nestworkadmin.R;
import com.sebasaku.nestworkadmin.api.model.AllUser;
import com.sebasaku.nestworkadmin.api.model.Present;
import com.sebasaku.nestworkadmin.api.service.UtilsApi;
import com.sebasaku.nestworkadmin.ui.SessionManager;
import com.sebasaku.nestworkadmin.ui.adapter.ListPresensiAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPresensiActivity extends AppCompatActivity {

    private List<Present> listPresensi;
    private RecyclerView mRecyclerView;
    private ListPresensiAdapter mAdapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_presensi);
        listPresensi = new ArrayList<>();
        backButton();

        SessionManager userPref = new SessionManager(getApplicationContext());
        final String accesToken = userPref.getAccesToken();

        Call<List<Present>> call = UtilsApi.getAPIService().getAllPresent("Bearer "+accesToken);
        call.enqueue(new Callback<List<Present>>() {
            @Override
            public void onResponse(Call<List<Present>> call, Response<List<Present>> response) {
                if (response.code()==200){
                    List<Present> allUser = response.body();
                    for(int i = 0; i<allUser.size(); i++){
                        String id = allUser.get(i).getId();
                        String email = allUser.get(i).getEmail();
                        String nama = allUser.get(i).getNama();
                        String statusPrs = allUser.get(i).getStatusPrs();
                        String backlog = allUser.get(i).getBacklog();
                        String task = allUser.get(i).getTask();
                        String note = allUser.get(i).getNote();
                        String createdAt = allUser.get(i).getCreatedAt();
                        String updatedAt = allUser.get(i).getUpdatedAt();
                        int v = allUser.get(i).getV();
                        listPresensi.add(new Present(id, email, nama, backlog, statusPrs, task, note, createdAt, updatedAt, v));
                        mAdapter.notifyDataSetChanged();
                    }
                }
                else {
                    Toast.makeText(ListPresensiActivity.this, "not correct", Toast.LENGTH_SHORT).show();
                    logoutUser();
                    Intent i = new Intent(ListPresensiActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<Present>> call, Throwable t) {
                Toast.makeText(ListPresensiActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });


        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list_presensi);

        mAdapter = new ListPresensiAdapter(getApplicationContext(), listPresensi);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    //untuk enampilkan back button
    public void backButton(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("List Presensi");
    }

    //fungsi back ketika tombol back diklik
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void logoutUser(){
        SessionManager session = new SessionManager(getApplicationContext());
        session.logoutUser();
    }




}
