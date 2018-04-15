package com.sebasaku.nestworkadmin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sebasaku.nestworkadmin.activity.DetailKaryawanActivity;
import com.sebasaku.nestworkadmin.api.model.AllUser;
import com.sebasaku.nestworkadmin.model.Karyawan;
import com.sebasaku.nestworkadmin.R;

import java.util.LinkedList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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

    public class ListAllUserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            context.startActivity(i);
            mAdapter.notifyDataSetChanged();
        }
    }

}
