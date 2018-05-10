package com.sebasaku.nestworkadmin.ui.adapter;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.sebasaku.nestworkadmin.R;
import com.sebasaku.nestworkadmin.api.model.AccCuti;
import com.sebasaku.nestworkadmin.api.model.DecCuti;
import com.sebasaku.nestworkadmin.api.model.ResponsCuti;
//import com.sebasaku.nestworkadmin.ui.AccCutiService;
import com.sebasaku.nestworkadmin.api.service.UtilsApi;
import com.sebasaku.nestworkadmin.ui.SessionManager;
import com.sebasaku.nestworkadmin.ui.activity.CutiActivity;
import com.sebasaku.nestworkadmin.ui.activity.DashboardActivity;
import com.sebasaku.nestworkadmin.ui.fragment.DetailPermintaanCutiFragment;
import com.sebasaku.nestworkadmin.ui.fragment.PermintaanCutiFragment;

import android.content.Intent;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RequestCutiAdapter extends RecyclerView.Adapter<RequestCutiAdapter.ListCutiViewHolder>  {
    //deklarasi global variabel
    private Context context;
    private final List<ResponsCuti> listCuti;


    //konstruktor untuk menerima data adapter
    public RequestCutiAdapter(Context context, List<ResponsCuti> listCuti) {
        this.context = context;
        this.listCuti = listCuti;
    }

    //view holder berfungsi untuk setting list item yang digunakan
    @Override
    public RequestCutiAdapter.ListCutiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cuti, null, false);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mItemView.setLayoutParams(layoutParams);

        return new RequestCutiAdapter.ListCutiViewHolder(mItemView, this);
    }

    //bind view holder berfungsi untuk set data ke view yang ditampilkan pada list item
    @Override
    public void onBindViewHolder(RequestCutiAdapter.ListCutiViewHolder holder, int position) {
        final ResponsCuti mCurrent = listCuti.get(position);
        holder.namaKaryawan.setText(mCurrent.getNama());
        holder.awalCuti.setText(mCurrent.getAwalCuti());
        holder.akhirCuti.setText(mCurrent.getAkhirCuti());
        holder.keterangaCuti.setText(mCurrent.getKeterangan());
    }

    //untuk menghitung jumlah data yang ada pada list
    @Override
    public int getItemCount() {
        return listCuti.size();
    }

    public class ListCutiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView namaKaryawan, awalCuti, akhirCuti, keterangaCuti;
        private CircleImageView avaUser;
        private Button btnAcc, btnDec;

        final RequestCutiAdapter mAdapter;

        //untuk casting view yang digunakan pada list item
        public ListCutiViewHolder(View itemView, RequestCutiAdapter adapter) {
            super(itemView);
            context = itemView.getContext();
            namaKaryawan = itemView.findViewById(R.id.tv_cuti_namaKaryawan);
            awalCuti = itemView.findViewById(R.id.tv_cuti_awalCuti);
            akhirCuti = itemView.findViewById(R.id.tv_cuti_akhirCuti);
            keterangaCuti = itemView.findViewById(R.id.tv_cuti_keterangan);
            btnAcc = itemView.findViewById(R.id.btn_acc);
            btnDec = itemView.findViewById(R.id.btn_dec);
            this.mAdapter = adapter;
            //itemView.setOnClickListener(this);


            btnAcc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    int mPosition = getLayoutPosition();
                    ResponsCuti element = listCuti.get(mPosition);
                    Toast.makeText(view.getContext(), element.getId(), Toast.LENGTH_SHORT).show();
                    SessionManager userPref = new SessionManager(context);
                    String accesToken = userPref.getAccesToken();
                    Call<AccCuti> call = UtilsApi.getAPIService().accCuti("Bearer " + accesToken, element.getId());
                    call.enqueue(new Callback<AccCuti>() {
                        @Override
                        public void onResponse(Call<AccCuti> call, Response<AccCuti> response) {
                            mAdapter.notifyDataSetChanged();
                            Intent i = new Intent(context,DashboardActivity.class);
                            Toast.makeText(context, "Data Berhasil Diuodate", Toast.LENGTH_SHORT).show();

                            context.startActivities(new Intent[]{i});
                        }

                        @Override
                        public void onFailure(Call<AccCuti> call, Throwable t) {
                            mAdapter.notifyDataSetChanged();
                            Intent i = new Intent(context,DashboardActivity.class);
                            Toast.makeText(context, "Data Berhasil Diuodate", Toast.LENGTH_SHORT).show();

                            context.startActivities(new Intent[]{i});
                        }
                    });

                }
            });

            btnDec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int mPosition = getLayoutPosition();
                    ResponsCuti element = listCuti.get(mPosition);
                    Toast.makeText(view.getContext(), element.getId(), Toast.LENGTH_SHORT).show();
                    SessionManager userPref = new SessionManager(context);
                    String accesToken = userPref.getAccesToken();
                    Call<DecCuti> call = UtilsApi.getAPIService().decCuti("Bearer " + accesToken, element.getId());
                    call.enqueue(new Callback<DecCuti>() {
                        @Override
                        public void onResponse(Call<DecCuti> call, Response<DecCuti> response) {
//                            Intent i = new Intent(context,CutiActivity.class);
//                            context.startActivities(new Intent[]{i});
                            mAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<DecCuti> call, Throwable t) {

                        }
                    });
                }
            });
        }

        @Override
        public void onClick(View view) {

        }




    }
}
