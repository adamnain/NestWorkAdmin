package com.sebasaku.nestworkadmin.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sebasaku.nestworkadmin.R;
import com.sebasaku.nestworkadmin.api.model.ResponsCuti;
import com.sebasaku.nestworkadmin.api.service.UtilsApi;
import com.sebasaku.nestworkadmin.ui.SessionManager;
import com.sebasaku.nestworkadmin.ui.activity.DashboardActivity;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatCutiAdapter extends RecyclerView.Adapter<RiwayatCutiAdapter.ListCutiViewHolder> {
    //private LinkedList<RiwayatCuti> listCuti;
    private final List<ResponsCuti> listCuti;

    //deklarasi global variabel
    private Context context;

    //konstruktor untuk menerima data adapter
    public RiwayatCutiAdapter(Context context, List<ResponsCuti> listCuti) {
        this.context = context;
        this.listCuti = listCuti;
    }

    //view holder berfungsi untuk setting list item yang digunakan
    @Override
    public ListCutiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_riwayat_cuti, null, false);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mItemView.setLayoutParams(layoutParams);

        return new ListCutiViewHolder(mItemView, this);
    }

    //bind view holder berfungsi untuk set data ke view yang ditampilkan pada list item
    @Override
    public void onBindViewHolder(ListCutiViewHolder holder, int position) {
        final ResponsCuti mCurrent = listCuti.get(position);
        holder.tvNamaKaryawan.setText(mCurrent.getNama());
        holder.awalCuti.setText(mCurrent.getAwalCuti());
        holder.akhirCuti.setText(mCurrent.getAkhirCuti());
        holder.keterangan.setText(mCurrent.getKeterangan());
        if(mCurrent.getStatus() == 1){
            holder.ivStatusCuti.setImageResource(R.drawable.ic_check);
        }
        else if (mCurrent.getStatus() == 0){
            holder.ivStatusCuti.setImageResource(R.drawable.ic_decline);
        }
    }

    //untuk menghitung jumlah data yang ada pada list
    @Override
    public int getItemCount() {
        return listCuti.size();
    }

    public class ListCutiViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        final RiwayatCutiAdapter mAdapter;
        private TextView awalCuti;
        private TextView akhirCuti;
        private TextView keterangan;
        private TextView tlKet;
        private CardView tlCard;
        private TextView tlAwalCuti;
        private TextView tlAkhirCuti;
        private TextView tvNamaKaryawan;
        private ImageView ivStatusCuti;
        private CardView cvRiwayat;

        //untuk casting view yang digunakan pada list item
        public ListCutiViewHolder(View itemView, RiwayatCutiAdapter adapter) {
            super(itemView);
            context = itemView.getContext();
            awalCuti = itemView.findViewById(R.id.tv_awal_cuti);
            akhirCuti = itemView.findViewById(R.id.tv_akhir_cuti);
            keterangan = itemView.findViewById(R.id.tv_keterangan);
            tlAwalCuti = itemView.findViewById(R.id.tl_awal_cuti);
            tlAkhirCuti = itemView.findViewById(R.id.tv_akhir_cuti);
            tlKet = itemView.findViewById(R.id.tl_keterangan);
            ivStatusCuti = itemView.findViewById(R.id.iv_status);
            tvNamaKaryawan = itemView.findViewById(R.id.tv_nama_karyawan);
            cvRiwayat = itemView.findViewById(R.id.cv_view_riwayat_cuti);

            this.mAdapter = adapter;
            cvRiwayat.setOnLongClickListener(this);
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
                    Toast.makeText(context, "Data Berhasil Diupdate", Toast.LENGTH_SHORT).show();

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

        @Override
        public boolean onLongClick(View view) {
            int mPosition = getLayoutPosition();
            final ResponsCuti element = listCuti.get(mPosition);

            //alert dialog
            AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
            builder2.setMessage("Hapus data ini?");
            builder2.setCancelable(true);

            builder2.setPositiveButton(
                    "Ya",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            hapusData(element.getId());
                        }
                    });

            builder2.setNegativeButton(
                    "Tidak",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert12 = builder2.create();
            alert12.show();
            return false;
        }
    }


}