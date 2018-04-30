//package com.sebasaku.nestworkadmin.ui.adapter;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.sebasaku.nestworkadmin.model.Presensi;
//import com.sebasaku.nestworkadmin.R;
//
//import java.util.LinkedList;
//
///**
// * Created by adam on 2/27/18.
// */
//
//public class ListPresensiAdapter extends RecyclerView.Adapter<ListPresensiAdapter.ListPresensiViewHolder> {
//    //deklarasi global variabel
//    private Context context;
//    private final LinkedList<Presensi> listPresensi;
//
//    //konstruktor untuk menerima data adapter
//    public ListPresensiAdapter(Context context, LinkedList<Presensi> listPresensi) {
//        this.context = context;
//        this.listPresensi = listPresensi;
//    }
//
//    //view holder berfungsi untuk setting list item yang digunakan
//    @Override
//    public ListPresensiAdapter.ListPresensiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_presensi, null, false);
//
//        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        mItemView.setLayoutParams(layoutParams);
//
//        return new ListPresensiAdapter.ListPresensiViewHolder(mItemView, this);
//    }
//
//    //bind view holder berfungsi untuk set data ke view yang ditampilkan pada list item
//    @Override
//    public void onBindViewHolder(ListPresensiAdapter.ListPresensiViewHolder holder, int position) {
//        final Presensi mCurrent = listPresensi.get(position);
//        holder.namaKaryawan.setText(mCurrent.getNamaKaryawan());
//    }
//
//    //untuk menghitung jumlah data yang ada pada list
//    @Override
//    public int getItemCount() {
//        return listPresensi.size();
//    }
//
//    public class ListPresensiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        private TextView namaKaryawan;
//
//        final ListPresensiAdapter mAdapter;
//
//        //untuk casting view yang digunakan pada list item
//        public ListPresensiViewHolder(View itemView, ListPresensiAdapter adapter) {
//            super(itemView);
//            context = itemView.getContext();
//            namaKaryawan = (TextView) itemView.findViewById(R.id.namaKaryawan);
//            this.mAdapter = adapter;
//            itemView.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View v) {
//
//        }
//
//        //untuk menambah action click pada list item
////        @Override
////        public void onClick(View view) {
////            int mPosition = getLayoutPosition();
////            Presensi element = listPresensi.get(mPosition);
////
////            //intent ke main activity dengan passing data
////            Intent i = new Intent(context, DetailPresensiActivity.class);
////            i.putExtra("namaUser", element.getNamaPresensi());
////            i.putExtra("avaUser", element.getAvaPresensi());
////            context.startActivity(i);
////            mAdapter.notifyDataSetChanged();
////        }
//    }
//}
