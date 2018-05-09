package com.sebasaku.nestworkadmin.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sebasaku.nestworkadmin.R;
import com.sebasaku.nestworkadmin.api.model.AllUser;
import com.sebasaku.nestworkadmin.api.model.SlipGaji;
import com.sebasaku.nestworkadmin.ui.activity.DetailKaryawanActivity;
import com.sebasaku.nestworkadmin.ui.activity.DetailSlipActivity;

import java.util.List;

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
        holder.tvNamaUser.setText(mCurrent.getEmail());
        holder.tvSesi.setText(mCurrent.getCreatedAt());

    }

    //untuk menghitung jumlah data yang ada pada list
    @Override
    public int getItemCount() {
        return listSlipGaji.size();
    }

    public class ListSlipGajiViewHolder extends RecyclerView.ViewHolder  {
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

        }

    }
}
