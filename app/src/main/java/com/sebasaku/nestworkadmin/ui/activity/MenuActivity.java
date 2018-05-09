package com.sebasaku.nestworkadmin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.sebasaku.nestworkadmin.ui.SessionManager;
import com.sebasaku.nestworkadmin.ui.adapter.SlidingImageAdapter;
import com.sebasaku.nestworkadmin.R;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MenuActivity extends AppCompatActivity {
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.jesica,R.drawable.jesica,R.drawable.jesica};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    CardView karyawan ,slipGaji,Cuti,infoPerusahaan,presensiHarian;

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initSlider();
        initializedObject();
        actionClicked();
        sessionCheck();
    }


    private void initSlider() {
        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImageAdapter(MenuActivity.this,ImagesArray));

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(5 * density);

        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }

    public void initializedObject(){
        karyawan = findViewById(R.id.karyawan);
        slipGaji = findViewById(R.id.slip_gaji);
        Cuti = findViewById(R.id.cuti);
        infoPerusahaan = findViewById(R.id.informasi_perusahaan);
        presensiHarian = findViewById(R.id.presensi_harian);
    }

    public void actionClicked() {
        karyawan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, ListKaryawanActivity.class);
                startActivity(i);
            }
        });

        presensiHarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this,ListPresensiActivity.class);
                startActivity(i);
            }
        });

        slipGaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this,ListSlipActivity.class);
                startActivity(i);
            }
        });

        infoPerusahaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this,ProfilPerusahaanActivity.class);
                startActivity(i);
            }
        });

        Cuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this,CutiActivity.class);
                startActivity(i);
            }
        });
    }

    public void sessionCheck(){
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        String token = session.getAccesToken();
        Toast.makeText(MenuActivity.this, token, Toast.LENGTH_LONG).show();

    }

}
