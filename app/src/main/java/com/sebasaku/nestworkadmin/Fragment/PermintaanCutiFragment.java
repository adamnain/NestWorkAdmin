package com.sebasaku.nestworkadmin.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sebasaku.nestworkadmin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PermintaanCutiFragment extends Fragment {


    public PermintaanCutiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_permintaan_cuti, container, false);
    }

}
