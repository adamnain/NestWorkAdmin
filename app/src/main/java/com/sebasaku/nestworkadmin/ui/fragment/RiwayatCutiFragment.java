package com.sebasaku.nestworkadmin.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sebasaku.nestworkadmin.R;
import com.sebasaku.nestworkadmin.api.model.ResponsCuti;
import com.sebasaku.nestworkadmin.api.service.UtilsApi;
import com.sebasaku.nestworkadmin.ui.SessionManager;
import com.sebasaku.nestworkadmin.ui.activity.LoginActivity;
import com.sebasaku.nestworkadmin.ui.adapter.RiwayatCutiAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RiwayatCutiFragment extends Fragment {
    View v;
    private List<ResponsCuti> listRiwayatCuti;
    private RecyclerView mRecyclerView;
    private RiwayatCutiAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_riwayat_cuti, container, false);
        listRiwayatCuti = new ArrayList<>();

        SessionManager userPref = new SessionManager(getContext());
        String accesToken = userPref.getAccesToken();
        Call<List<ResponsCuti>> call = UtilsApi.getAPIService().findByRespons("Bearer "+accesToken, 1);
        call.enqueue(new Callback<List<ResponsCuti>>() {
            @Override
            public void onResponse(Call<List<ResponsCuti>> call, Response<List<ResponsCuti>> response) {
                if (response.code()==200){
                    List<ResponsCuti> responsCuti = response.body();
                    for(int i = 0; i<responsCuti.size(); i++){
                        int respons = responsCuti.get(i).getRespons();
                        int status = responsCuti.get(i).getStatus();
                        String id = responsCuti.get(i).getId();
                        String email = responsCuti.get(i).getEmail();
                        String awalCuti = responsCuti.get(i).getAwalCuti();
                        String akhirCuti = responsCuti.get(i).getAkhirCuti();
                        String keterangan = responsCuti.get(i).getKeterangan();
                        String nama = responsCuti.get(i).getNama();
                        String createdAt = responsCuti.get(i).getCreatedAt();
                        String updatedAt = responsCuti.get(i).getUpdatedAt();
                        int v = responsCuti.get(i).getV();
                        listRiwayatCuti.add(new ResponsCuti(respons, status, id, email, awalCuti, akhirCuti, keterangan, nama, createdAt, updatedAt, v));
                        mAdapter.notifyDataSetChanged();
                    }
                }
                else {
                    Toast.makeText(getActivity(), "not correct", Toast.LENGTH_SHORT).show();
                    logoutUser();
                    Intent i = new Intent(getActivity(), LoginActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<List<ResponsCuti>> call, Throwable t) {
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });


        mRecyclerView = v.findViewById(R.id.rv_riwayat_cuti);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new RiwayatCutiAdapter(getActivity(),listRiwayatCuti);
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }

    public void logoutUser(){
        SessionManager session = new SessionManager(getActivity());
        session.logoutUser();
    }

}
