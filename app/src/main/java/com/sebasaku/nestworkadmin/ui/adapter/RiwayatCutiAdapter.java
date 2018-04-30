package com.sebasaku.nestworkadmin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sebasaku.nestworkadmin.R;
import com.sebasaku.nestworkadmin.api.model.ResponsCuti;

import java.util.List;

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
        //holder.tlKet.setText("Keterangan : ");
    }

    //untuk menghitung jumlah data yang ada pada list
    @Override
    public int getItemCount() {
        return listCuti.size();
    }

    public class ListCutiViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/ {
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

        //untuk casting view yang digunakan pada list item
        public ListCutiViewHolder(View itemView, RiwayatCutiAdapter adapter) {
            super(itemView);
            context = itemView.getContext();
            awalCuti = itemView.findViewById(R.id.tv_awal_cuti);
            akhirCuti = itemView.findViewById(R.id.tv_akhir_cuti);
            keterangan = itemView.findViewById(R.id.tv_keterangan);
            tlAwalCuti = itemView.findViewById(R.id.tl_awal_cuti);
            tlAkhirCuti = itemView.findViewById(R.id.tv_akhir_cuti);
            tlCard = itemView.findViewById(R.id.cd_view);
            tlKet = itemView.findViewById(R.id.tl_keterangan);
            ivStatusCuti = itemView.findViewById(R.id.iv_status);
            tvNamaKaryawan = itemView.findViewById(R.id.tv_nama_karyawan);

            this.mAdapter = adapter;
            //itemView.setOnClickListener(this);
        }
    }
}