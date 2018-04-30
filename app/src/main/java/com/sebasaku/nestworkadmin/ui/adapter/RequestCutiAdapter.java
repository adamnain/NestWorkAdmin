package com.sebasaku.nestworkadmin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sebasaku.nestworkadmin.R;
import com.sebasaku.nestworkadmin.api.model.ResponsCuti;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;



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

        final RequestCutiAdapter mAdapter;

        //untuk casting view yang digunakan pada list item
        public ListCutiViewHolder(View itemView, RequestCutiAdapter adapter) {
            super(itemView);
            context = itemView.getContext();
            namaKaryawan = itemView.findViewById(R.id.tv_cuti_namaKaryawan);
            awalCuti = itemView.findViewById(R.id.tv_cuti_awalCuti);
            akhirCuti = itemView.findViewById(R.id.tv_cuti_akhirCuti);
            keterangaCuti = itemView.findViewById(R.id.tv_cuti_keterangan);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        //untuk menambah action click pada list item
        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            ResponsCuti element = listCuti.get(mPosition);

            //intent ke main activity dengan passing data
//            Intent i = new Intent(context, DetailCutiActivity.class);
//            i.putExtra("namaUser", element.getNamaCuti());
//            i.putExtra("avaUser", element.getAvaCuti());
//            context.startActivity(i);
//            mAdapter.notifyDataSetChanged();
        }
    }

}
