package com.gregoriopalama.carservice.ui.cars;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.gregoriopalama.carservice.R;
import com.gregoriopalama.carservice.databinding.FragmentCarBinding;
import com.gregoriopalama.carservice.ui.services.ServicesListFragment;
import com.gregoriopalama.carservice.ui.tax.TaxListFragment;
import com.gregoriopalama.carservice.ui.tyres.TyresListFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarFragment extends Fragment {
    private FragmentCarBinding binding;

    public CarFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCarBinding.inflate(inflater, container, false);

        binding.bottomNavigation.setOnNavigationItemSelectedListener((item) -> {
            switch ( item.getItemId()) {
                case R.id.car_nav_services:
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new ServicesListFragment())
                            .commit();
                    break;
                case R.id.car_nav_tyres:
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new TyresListFragment())
                            .commit();
                    break;
                case R.id.car_nav_tax:
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new TaxListFragment())
                            .commit();
                    break;
            }
            item.setChecked(true);
            return true;
        });

        return binding.getRoot();
    }

}
