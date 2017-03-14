package com.codepath.example.masterdetailmanual;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by fuerm on 13/03/2017.
 */

public class CompletoFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_completo,
                container, false);

        return view;
    }

    public static CompletoFragment newInstance() {
        //ItemDetailActivity.preferences = preferences;
        CompletoFragment completoFragment= new CompletoFragment();

        return completoFragment;
    }
}
