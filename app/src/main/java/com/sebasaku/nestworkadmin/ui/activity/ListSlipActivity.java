package com.sebasaku.nestworkadmin.ui.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.sebasaku.nestworkadmin.R;
import com.sebasaku.nestworkadmin.api.model.AllUser;
import com.sebasaku.nestworkadmin.api.model.SlipGaji;
import com.sebasaku.nestworkadmin.api.service.UtilsApi;
import com.sebasaku.nestworkadmin.ui.SessionManager;
import com.sebasaku.nestworkadmin.ui.adapter.ListSlipAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSlipActivity extends AppCompatActivity {

    private List<SlipGaji> listSlip;
    private RecyclerView mRecyclerView;
    private ListSlipAdapter mAdapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_slip);

        listSlip = new ArrayList<>();

        backButton();

        SessionManager userPref = new SessionManager(getApplicationContext());
        String accesToken = userPref.getAccesToken();

        Call<List<SlipGaji>> call = UtilsApi.getAPIService().getAllSlip("Bearer "+accesToken);
        call.enqueue(new Callback<List<SlipGaji>>() {
            @Override
            public void onResponse(Call<List<SlipGaji>> call, Response<List<SlipGaji>> response) {
                if (response.code()==200){
                    List<SlipGaji> slip = response.body();
                    for(int i = 0; i<slip.size(); i++){
                        String status = slip.get(i).getStatus();

                        if (status.equals("0")){
                            int jumlahTask = slip.get(i).getJumlahTask();
                            String id = slip.get(i).getId();
                            String email = slip.get(i).getEmail();
                            String waktu = slip.get(i).getWaktu();
                            int gaji = slip.get(i).getGaji();
                            String createdAt = slip.get(i).getCreatedAt();
                            String updatedAt = slip.get(i).getUpdatedAt();
                            int v = slip.get(i).getV();


                            listSlip.add(new SlipGaji(status, jumlahTask, id, email, waktu, gaji, createdAt, updatedAt, v));
                            mAdapter.notifyDataSetChanged();
                        }

                    }
                }
                else {
                    Toast.makeText(ListSlipActivity.this, "not correct", Toast.LENGTH_SHORT).show();
                    logoutUser();
                    Intent i = new Intent(ListSlipActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<SlipGaji>> call, Throwable t) {
                Toast.makeText(ListSlipActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });


        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list_slip);

        mAdapter = new ListSlipAdapter(getApplicationContext(), listSlip);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    //untuk enampilkan back button
    public void backButton(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("List Slip Gaji");
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
