package com.gregoriopalama.carservice.ui.tax;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gregoriopalama.carservice.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaxListFragment extends Fragment {


    public TaxListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tax_list, container, false);
    }

}
