package com.sebasaku.nestworkadmin.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
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
import com.sebasaku.nestworkadmin.api.service.UtilsApi;
import com.sebasaku.nestworkadmin.ui.SessionManager;
import com.sebasaku.nestworkadmin.ui.activity.DashboardActivity;

import android.content.Intent;
import android.widget.Toast;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
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

    public class ListCutiViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        private TextView namaKaryawan, awalCuti, akhirCuti, keterangaCuti;
        private CircleImageView avaUser;
        private Button btnAcc, btnDec;
        private CardView cvPermintaanCuti;

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
            cvPermintaanCuti = itemView.findViewById(R.id.cv_permintaan_cuti);
            this.mAdapter = adapter;
            itemView.setOnLongClickListener(this);


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
                            Toast.makeText(context, "Data Gagal Diuodate", Toast.LENGTH_SHORT).show();

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
                            mAdapter.notifyDataSetChanged();
                            Intent i = new Intent(context,DashboardActivity.class);
                            Toast.makeText(context, "Data Berhasil Diuodate", Toast.LENGTH_SHORT).show();

                            context.startActivities(new Intent[]{i});
                        }

                        @Override
                        public void onFailure(Call<DecCuti> call, Throwable t) {
                            mAdapter.notifyDataSetChanged();
                            Intent i = new Intent(context,DashboardActivity.class);
                            Toast.makeText(context, "Data Berhasil Diuodate", Toast.LENGTH_SHORT).show();

                            context.startActivities(new Intent[]{i});
                        }
                    });
                }
            });
        }

        @Override
        public boolean onLongClick(View view) {
            int mPosition = getLayoutPosition();
            final ResponsCuti element = listCuti.get(mPosition);

            //alert dialog
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("Hapus data ini?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Ya",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            hapusData(element.getId());
                        }
                    });

            builder1.setNegativeButton(
                    "Tidak",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
            return false;
        }

        private void hapusData(String id){
            int mPosition = getLayoutPosition();
            ResponsCuti element = listCuti.get(mPosition);
            SessionManager userPref = new SessionManager(context);
            String accesToken = userPref.getAccesToken();
            Call<ResponseBody> call = UtilsApi.getAPIService().deleteCutiById("Bearer " + accesToken, id);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    mAdapter.notifyDataSetChanged();
                    Intent i = new Intent(context,DashboardActivity.class);
                    Toast.makeText(context, "Data Berhasil Diuodate", Toast.LENGTH_SHORT).show();

                    context.startActivities(new Intent[]{i});
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    mAdapter.notifyDataSetChanged();
                    Intent i = new Intent(context,DashboardActivity.class);
                    Toast.makeText(context, "Data Berhasil Diupdate", Toast.LENGTH_SHORT).show();

                    context.startActivities(new Intent[]{i});
                }
            });
        }




    }
}
