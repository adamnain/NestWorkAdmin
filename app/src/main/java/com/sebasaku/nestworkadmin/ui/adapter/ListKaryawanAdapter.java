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

import com.sebasaku.nestworkadmin.api.model.ResponsCuti;
import com.sebasaku.nestworkadmin.api.model.User;
import com.sebasaku.nestworkadmin.api.service.UtilsApi;
import com.sebasaku.nestworkadmin.ui.SessionManager;
import com.sebasaku.nestworkadmin.ui.activity.DashboardActivity;
import com.sebasaku.nestworkadmin.ui.activity.DetailKaryawanActivity;
import com.sebasaku.nestworkadmin.api.model.AllUser;
import com.sebasaku.nestworkadmin.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by adam on 2/23/18.
 */

public class ListKaryawanAdapter extends RecyclerView.Adapter<ListKaryawanAdapter.ListAllUserViewHolder> {
    //deklarasi global variabel
    private Context context;
    private final List<AllUser> listAllUser;

    //konstruktor untuk menerima data adapter
    public ListKaryawanAdapter(Context context, List<AllUser> listAllUser) {
        this.context = context;
        this.listAllUser = listAllUser;
    }

    //view holder berfungsi untuk setting list item yang digunakan
    @Override
    public ListAllUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_karyawan, null, false);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mItemView.setLayoutParams(layoutParams);

        return new ListAllUserViewHolder(mItemView, this);
    }

    //bind view holder berfungsi untuk set data ke view yang ditampilkan pada list item
    @Override
    public void onBindViewHolder(ListAllUserViewHolder holder, int position) {
        final AllUser mCurrent = listAllUser.get(position);
        holder.namaUser.setText(mCurrent.getNama());
        holder.avaUser.setImageResource(R.drawable.user);
        holder.jobUser.setText(mCurrent.getPosisi());
    }

    //untuk menghitung jumlah data yang ada pada list
    @Override
    public int getItemCount() {
        return listAllUser.size();
    }

    public class ListAllUserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private TextView namaUser, jobUser;
        private CircleImageView avaUser;

        final ListKaryawanAdapter mAdapter;

        //untuk casting view yang digunakan pada list item
        public ListAllUserViewHolder(View itemView, ListKaryawanAdapter adapter) {
            super(itemView);
            context = itemView.getContext();
            namaUser = (TextView) itemView.findViewById(R.id.namaUser);
            avaUser = (CircleImageView) itemView.findViewById(R.id.avaUser);
            jobUser = (TextView) itemView.findViewById(R.id.jobUser);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        //untuk menambah action click pada list item
        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            AllUser element = listAllUser.get(mPosition);

            //intent ke main activity dengan passing data
            Intent i = new Intent(context, DetailKaryawanActivity.class);
            i.putExtra("namaUser", element.getNama());
            i.putExtra("avaUser", R.drawable.user);
            i.putExtra("posisi", element.getPosisi());
            i.putExtra("email",element.getEmail());
            i.putExtra("tanggalLahir",element.getNoHp());
            i.putExtra("hp",element.getNoHp());
            i.putExtra("id",element.getId());
            context.startActivity(i);
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public boolean onLongClick(View view) {
            int mPosition = getLayoutPosition();
            final AllUser element = listAllUser.get(mPosition);

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
            AllUser element = listAllUser.get(mPosition);
            SessionManager userPref = new SessionManager(context);
            String accesToken = userPref.getAccesToken();
            Call<ResponseBody> call = UtilsApi.getAPIService().deleteUserById("Bearer " + accesToken, id);
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
