/*
package com.sebasaku.nestworkadmin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sebasaku.nestworkadmin.Activity.DetailKaryawanActivity;
import com.sebasaku.nestworkadmin.Model.Cuti;
import com.sebasaku.nestworkadmin.R;

import java.util.LinkedList;

import de.hdodenhof.circleimageview.CircleImageView;

*/
/**
 * Created by adamnain on 3/13/18.
 *//*


public class ListCutiAdapter extends RecyclerView.Adapter<ListCutiAdapter.ListCutiViewHolder>  {
    //deklarasi global variabel
    private Context context;
    private final LinkedList<Cuti> listCuti;

    //konstruktor untuk menerima data adapter
    public ListCutiAdapter(Context context, LinkedList<Cuti> listCuti) {
        this.context = context;
        this.listCuti = listCuti;
    }

    //view holder berfungsi untuk setting list item yang digunakan
    @Override
    public ListCutiAdapter.ListCutiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cuti, null, false);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mItemView.setLayoutParams(layoutParams);

        return new ListCutiAdapter.ListKaryawanViewHolder(mItemView, this);
    }

    //bind view holder berfungsi untuk set data ke view yang ditampilkan pada list item
    @Override
    public void onBindViewHolder(ListCutiAdapter.ListCutiViewHolder holder, int position) {
        final Cuti mCurrent = listCuti.get(position);
        holder.namaUser.setText(mCurrent.getNamaCuti());
        holder.avaUser.setImageResource(mCurrent.getAvaCuti());
    }

    //untuk menghitung jumlah data yang ada pada list
    @Override
    public int getItemCount() {
        return listCuti.size();
    }

    public class ListCutiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView namaUser;
        private CircleImageView avaUser;

        final ListCutiAdapter mAdapter;

        //untuk casting view yang digunakan pada list item
        public ListCutiViewHolder(View itemView, ListCutiAdapter adapter) {
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
            Cuti element = listCuti.get(mPosition);

            //intent ke main activity dengan passing data
            Intent i = new Intent(context, DetailCutiActivity.class);
            i.putExtra("namaUser", element.getNamaCuti());
            i.putExtra("avaUser", element.getAvaCuti());
            context.startActivity(i);
            mAdapter.notifyDataSetChanged();
        }
    }

}
*/
