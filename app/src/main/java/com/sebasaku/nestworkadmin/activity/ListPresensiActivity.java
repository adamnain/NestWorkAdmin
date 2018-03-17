package com.sebasaku.nestworkadmin.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.sebasaku.nestworkadmin.adapter.ListPresensiAdapter;
import com.sebasaku.nestworkadmin.model.Presensi;
import com.sebasaku.nestworkadmin.R;

import java.util.LinkedList;

public class ListPresensiActivity extends AppCompatActivity {

    private final LinkedList<Presensi> listPresensi = new LinkedList<>();

    private RecyclerView mRecyclerView;
    private ListPresensiAdapter mAdapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_presensi);

        backButton();

        //set data
        listPresensi.addLast(new Presensi("Jesica Henwick","a","a","a","a"));
        listPresensi.addLast(new Presensi("Henwick","a","a","a","a"));


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

}
