package com.sebasaku.nestworkadmin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sebasaku.nestworkadmin.api.model.Present;
import com.sebasaku.nestworkadmin.R;

import java.util.List;

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

    public class ListPresentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            itemView.setOnClickListener(this);
        }

        //untuk menambah action click pada list item
        @Override
        public void onClick(View view) {

        }
    }

}
