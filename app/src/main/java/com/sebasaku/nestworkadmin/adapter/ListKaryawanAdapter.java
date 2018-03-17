package com.sebasaku.nestworkadmin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sebasaku.nestworkadmin.activity.DetailKaryawanActivity;
import com.sebasaku.nestworkadmin.model.Karyawan;
import com.sebasaku.nestworkadmin.R;

import java.util.LinkedList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by adam on 2/23/18.
 */

public class ListKaryawanAdapter extends RecyclerView.Adapter<ListKaryawanAdapter.ListKaryawanViewHolder> {
    //deklarasi global variabel
    private Context context;
    private final LinkedList<Karyawan> listKaryawan;

    //konstruktor untuk menerima data adapter
    public ListKaryawanAdapter(Context context, LinkedList<Karyawan> listKaryawan) {
        this.context = context;
        this.listKaryawan = listKaryawan;
    }

    //view holder berfungsi untuk setting list item yang digunakan
    @Override
    public ListKaryawanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_karyawan, null, false);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mItemView.setLayoutParams(layoutParams);

        return new ListKaryawanViewHolder(mItemView, this);
    }

    //bind view holder berfungsi untuk set data ke view yang ditampilkan pada list item
    @Override
    public void onBindViewHolder(ListKaryawanViewHolder holder, int position) {
        final Karyawan mCurrent = listKaryawan.get(position);
        holder.namaUser.setText(mCurrent.getNamaKaryawan());
        holder.avaUser.setImageResource(mCurrent.getAvaKaryawan());
    }

    //untuk menghitung jumlah data yang ada pada list
    @Override
    public int getItemCount() {
        return listKaryawan.size();
    }

    public class ListKaryawanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView namaUser;
        private CircleImageView avaUser;

        final ListKaryawanAdapter mAdapter;

        //untuk casting view yang digunakan pada list item
        public ListKaryawanViewHolder(View itemView, ListKaryawanAdapter adapter) {
            super(itemView);
            context = itemView.getContext();
            namaUser = (TextView) itemView.findViewById(R.id.namaUser);
            avaUser = (CircleImageView) itemView.findViewById(R.id.avaUser);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        //untuk menambah action click pada list item
        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            Karyawan element = listKaryawan.get(mPosition);

            //intent ke main activity dengan passing data
            Intent i = new Intent(context, DetailKaryawanActivity.class);
            i.putExtra("namaUser", element.getNamaKaryawan());
            i.putExtra("avaUser", element.getAvaKaryawan());
            context.startActivity(i);
            mAdapter.notifyDataSetChanged();
        }
    }

}
