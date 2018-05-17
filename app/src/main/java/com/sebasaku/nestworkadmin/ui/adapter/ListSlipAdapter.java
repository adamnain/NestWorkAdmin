package com.sebasaku.nestworkadmin.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sebasaku.nestworkadmin.R;
import com.sebasaku.nestworkadmin.api.model.AllUser;
import com.sebasaku.nestworkadmin.api.model.ResponsCuti;
import com.sebasaku.nestworkadmin.api.model.SlipGaji;
import com.sebasaku.nestworkadmin.api.service.UtilsApi;
import com.sebasaku.nestworkadmin.ui.SessionManager;
import com.sebasaku.nestworkadmin.ui.activity.DashboardActivity;
import com.sebasaku.nestworkadmin.ui.activity.DetailKaryawanActivity;
import com.sebasaku.nestworkadmin.ui.activity.DetailSlipActivity;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSlipAdapter extends RecyclerView.Adapter<ListSlipAdapter.ListSlipGajiViewHolder>{
    //deklarasi global variabel
    private Context context;
    private final List<SlipGaji> listSlipGaji;

    //konstruktor untuk menerima data adapter
    public ListSlipAdapter(Context context, List<SlipGaji> listSlipGaji) {
        this.context = context;
        this.listSlipGaji = listSlipGaji;
    }

    //view holder berfungsi untuk setting list item yang digunakan
    @Override
    public ListSlipAdapter.ListSlipGajiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_slip, null, false);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mItemView.setLayoutParams(layoutParams);

        return new ListSlipGajiViewHolder(mItemView,this);
    }

    //bind view holder berfungsi untuk set data ke view yang ditampilkan pada list item
    @Override
    public void onBindViewHolder(ListSlipAdapter.ListSlipGajiViewHolder holder, int position) {
        final SlipGaji mCurrent = listSlipGaji.get(position);

        String tgl = mCurrent.getCreatedAt();
        String[]parts = tgl.split("T");
        String tgls = parts[0];
        String[]partss = tgls.split("-");
        String tglss = partss[2];
        String bln = partss[1];
        int tanggal = Integer.parseInt(tglss);
        int bulan = Integer.parseInt(bln);
        String sesi = "";

        if (bulan == 1){
            sesi = sesi+"Januari ";
        }
        else if (bulan == 2){
            sesi = sesi+"Februari ";

        }
        else if (bulan == 3){
            sesi = sesi+"Maret ";

        }
        else if (bulan == 4){
            sesi = sesi+"April ";

        }
        else if (bulan == 5){
            sesi = sesi+"Mei ";

        }
        else if (bulan == 6){
            sesi = sesi+"Juni ";

        }
        else if (bulan == 7){
            sesi = sesi+"Juli ";

        }
        else if (bulan == 8){
            sesi = sesi+"Agustus ";

        }
        else if (bulan == 9){
            sesi = sesi+"September ";

        }
        else if (bulan == 10){
            sesi = sesi+"Oktober ";

        }
        else if (bulan == 11){
            sesi = sesi+"November ";

        }
        else if (bulan == 12){
            sesi = sesi+"Desember ";

        }

        if (tanggal<=15){
            sesi = sesi+"Sesi Satu";
            holder.tvSesi.setText(sesi);
        }
        else if (tanggal > 15){
            sesi = sesi+"Sesi Dua";
            holder.tvSesi.setText(sesi);
        }

        holder.tvNamaUser.setText(mCurrent.getEmail());

    }

    //untuk menghitung jumlah data yang ada pada list
    @Override
    public int getItemCount() {
        return listSlipGaji.size();
    }

    public class ListSlipGajiViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener  {
        private TextView tvNamaUser, tvSesi;
        private Button btnKirimSlip;

        final ListSlipAdapter mAdapter;

        //untuk casting view yang digunakan pada list item
        public ListSlipGajiViewHolder(View itemView, ListSlipAdapter adapter) {
            super(itemView);
            context = itemView.getContext();
            tvNamaUser = itemView.findViewById(R.id.tv_namaUser_slip);
            tvSesi = itemView.findViewById(R.id.tv_sesiGaji);
            btnKirimSlip = itemView.findViewById(R.id.btn_kirimSLip);

            btnKirimSlip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int mPosition = getLayoutPosition();
                    SlipGaji element = listSlipGaji.get(mPosition);

                    //intent ke main activity dengan passing data
                    Intent i = new Intent(context, DetailSlipActivity.class);
                    i.putExtra("status", element.getStatus());
                    i.putExtra("jumlahTask", element.getJumlahTask());
                    i.putExtra("id", element.getId());
                    i.putExtra("email", element.getEmail());
                    i.putExtra("gaji",element.getGaji());
                    i.putExtra("waktu",element.getWaktu());
                    i.putExtra("createdAt",element.getCreatedAt());
                    i.putExtra("updateAt",element.getUpdatedAt());
                    i.putExtra("v",element.getV());

                    context.startActivity(i);
                    mAdapter.notifyDataSetChanged();
                }
            });

            this.mAdapter = adapter;
            itemView.setOnLongClickListener(this);

        }

        @Override
        public boolean onLongClick(View view) {
            int mPosition = getLayoutPosition();
            final SlipGaji element = listSlipGaji.get(mPosition);

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
            SlipGaji element = listSlipGaji.get(mPosition);
            SessionManager userPref = new SessionManager(context);
            String accesToken = userPref.getAccesToken();
            Call<ResponseBody> call = UtilsApi.getAPIService().deleteSlipById("Bearer " + accesToken, id);
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
