package com.sebasaku.nestworkadmin.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sebasaku.nestworkadmin.R;
import com.sebasaku.nestworkadmin.ui.SessionManager;
import com.sebasaku.nestworkadmin.ui.adapter.RequestCutiAdapter;
import com.sebasaku.nestworkadmin.api.model.ResponsCuti;
import com.sebasaku.nestworkadmin.api.service.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PermintaanCutiFragment extends Fragment {
    View v;
    private List<ResponsCuti> listRequestCuti;
    private RecyclerView mRecyclerView;
    private RequestCutiAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_permintaan_cuti, container, false);
        listRequestCuti = new ArrayList<>();


        SessionManager userPref = new SessionManager(getContext());
        String accesToken = userPref.getAccesToken();
        Call<List<ResponsCuti>> call = UtilsApi.getAPIService().findByRespons("Bearer "+accesToken, 0);
        call.enqueue(new Callback<List<ResponsCuti>>() {
            @Override
            public void onResponse(Call<List<ResponsCuti>> call, Response<List<ResponsCuti>> response) {
                Toast.makeText(getActivity(), "harusnya bener", Toast.LENGTH_SHORT).show();
                if (response.code()==200){
                    List<ResponsCuti> responsCuti = response.body();
                    for(int i = 0; i<responsCuti.size(); i++){
                        int respons = responsCuti.get(i).getRespons();
                        int status = responsCuti.get(i).getStatus();
                        String id = responsCuti.get(i).getId();
                        String email = responsCuti.get(i).getEmail();
                        String awalCuti = responsCuti.get(i).getAwalCuti();
//                        StringTokenizer awal = new StringTokenizer(awalCuti, "T");
//                        String separatedAwal = awal.nextToken();
                        String akhirCuti = responsCuti.get(i).getAkhirCuti();
//                        StringTokenizer akhir = new StringTokenizer(akhirCuti, "T");
//                        String separatedAkhir = akhir.nextToken();
                        String keterangan = responsCuti.get(i).getKeterangan();
                        String nama = responsCuti.get(i).getNama();
                        String createdAt = responsCuti.get(i).getCreatedAt();
                        String updatedAt = responsCuti.get(i).getUpdatedAt();
                        int v = responsCuti.get(i).getV();
                        listRequestCuti.add(new ResponsCuti(respons, status, id, email, awalCuti, akhirCuti, keterangan, nama, createdAt, updatedAt, v));
                        mAdapter.notifyDataSetChanged();
                    }
                }
                else {
                    Toast.makeText(getActivity(), "not correct", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ResponsCuti>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });


        mRecyclerView = v.findViewById(R.id.rv_list_cuti);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new RequestCutiAdapter(getActivity(),listRequestCuti);
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }

}
