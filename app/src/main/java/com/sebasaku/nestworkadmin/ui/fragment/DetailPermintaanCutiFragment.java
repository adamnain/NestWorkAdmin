package com.sebasaku.nestworkadmin.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sebasaku.nestworkadmin.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailPermintaanCutiFragment extends Fragment {
    View v;
    CircleImageView avaUser;
    TextView namaUser, jobUser, email, tanggalLahir, hp;
    CardView cardView;

    public DetailPermintaanCutiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_detail_permintaan_cuti, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String id = bundle.getString("id", "");
            namaUser.setText(id);
        }

        intializedObject(v);
        return v;
    }

    public void intializedObject(View v){
        //avaUser = (CircleImageView) findViewById(R.id.avaUser);
        namaUser = (TextView) v.findViewById(R.id.namaUser);
        cardView = (CardView) v.findViewById(R.id.cardView);
        //jobUser = (TextView) findViewById(R.id.jobUser);
        //email = (TextView) findViewById(R.id.email);
        //tanggalLahir = (TextView) findViewById(R.id.tanggalLahir);
        //hp = (TextView) findViewById(R.id.hp);

    }

    public void switchFragment(Fragment frag){
        FragmentManager mFragmentManager = getFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.frame_container, frag,
                DetailPermintaanCutiFragment.class.getSimpleName());
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();
    }

}
