package com.sebasaku.nestworkadmin.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.sebasaku.nestworkadmin.Adapter.ListKaryawanAdapter;
import com.sebasaku.nestworkadmin.Model.Karyawan;
import com.sebasaku.nestworkadmin.R;

import java.util.LinkedList;

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

        //set data
        listKaryawan.addLast(new Karyawan("Jesica Henwick", R.drawable.jesica));
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
