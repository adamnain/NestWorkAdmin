package com.sebasaku.nestworkadmin.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sebasaku.nestworkadmin.api.model.AccCuti;
import com.sebasaku.nestworkadmin.api.model.AllUser;
import com.sebasaku.nestworkadmin.api.model.Present;
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

/**
 * Created by adam on 2/23/18.
 */

public class ListPresensiAdapter extends RecyclerView.Adapter<ListPresensiAdapter.ListPresentViewHolder> {
    //deklarasi global variabel
    private Context context;
    private final List<Present> listPresent;


    //konstruktor untuk menerima data adapter
    public ListPresensiAdapter(Context context, List<Present> listPresent) {
        this.context = context;
        this.listPresent = listPresent;
    }

    //view holder berfungsi untuk setting list item yang digunakan
    @Override
    public ListPresentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_presensi, null, false);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mItemView.setLayoutParams(layoutParams);

        return new ListPresentViewHolder(mItemView, this);
    }

    //bind view holder berfungsi untuk set data ke view yang ditampilkan pada list item
    @Override
    public void onBindViewHolder(ListPresentViewHolder holder, int position) {
        final Present mCurrent = listPresent.get(position);
        holder.tvNamaUser.setText(mCurrent.getNama());
        holder.tvTanggal.setText(mCurrent.getCreatedAt());
        holder.tvBacklog.setText(mCurrent.getBacklog());
        holder.tvTask.setText(mCurrent.getTask());
        holder.tvNote.setText(mCurrent.getNote());

        if (mCurrent.getStatusPrs()=="0"){
            holder.tvStatus.setText("Will Do");
        }
        else if(mCurrent.getStatusPrs()=="1"){
            holder.tvStatus.setText("Done");
        }
        else if(mCurrent.getStatusPrs()=="2"){
            holder.tvStatus.setText("Obstacle");
        }


    }

    //untuk menghitung jumlah data yang ada pada list
    @Override
    public int getItemCount() {
        return listPresent.size();
    }

    public class ListPresentViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        private TextView tvNamaUser, tvStatus, tvTanggal, tvBacklog, tvTask, tvNote;

        final ListPresensiAdapter mAdapter;

        //untuk casting view yang digunakan pada list item
        public ListPresentViewHolder(View itemView, ListPresensiAdapter adapter) {
            super(itemView);
            context = itemView.getContext();
            tvNamaUser = (TextView) itemView.findViewById(R.id.tv_nama_karyawan_presensi);
            tvStatus = (TextView) itemView.findViewById(R.id.tv_status);
            tvTanggal = (TextView) itemView.findViewById(R.id.tv_tanggal);
            tvBacklog = (TextView) itemView.findViewById(R.id.tv_backlog);
            tvTask = (TextView) itemView.findViewById(R.id.tv_task);
            tvNote = (TextView) itemView.findViewById(R.id.tv_note);
            this.mAdapter = adapter;
            itemView.setOnLongClickListener(this);
        }


        @Override
        public boolean onLongClick(View view) {
            int mPosition = getLayoutPosition();
            final Present element = listPresent.get(mPosition);

            Toast.makeText(context ,element.getNama(), Toast.LENGTH_SHORT).show();

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
            Present element = listPresent.get(mPosition);
            SessionManager userPref = new SessionManager(context);
            String accesToken = userPref.getAccesToken();
            Call<ResponseBody> call = UtilsApi.getAPIService().deletePresensiById("Bearer " + accesToken, id);
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
                    Toast.makeText(context, "Data Berhasil Diuodate", Toast.LENGTH_SHORT).show();

                    context.startActivities(new Intent[]{i});
                }
            });
        }
    }

}
