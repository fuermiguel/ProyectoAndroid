package com.codepath.example.masterdetailmanual;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by fuerm on 14/03/2017.
 */

public class InicialFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicial,
                container, false);

        return view;
    }

    public static InicialFragment newInstance() {
        //ItemDetailActivity.preferences = preferences;
        InicialFragment inicialFragment= new InicialFragment();

        return inicialFragment;
    }
}
