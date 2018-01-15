package com.gregoriopalama.carservice.ui.cars;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gregoriopalama.carservice.R;
import com.gregoriopalama.carservice.data.Car;
import com.gregoriopalama.carservice.databinding.FragmentCarsListBinding;
import com.gregoriopalama.carservice.di.Injectable;
import com.gregoriopalama.carservice.di.ViewModelFactory;
import com.gregoriopalama.carservice.viewmodel.CarsListViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarsListFragment extends Fragment implements Injectable {
    FragmentCarsListBinding binding;
    CarListAdapter adapter;

    @Inject
    ViewModelFactory carServiceViewModelFactory;

    private CarsListViewModel carsListViewModel;

    public CarsListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCarsListBinding.inflate(inflater, container, false);
        binding.list.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,
                false));
        adapter = new CarListAdapter(new ArrayList<>());
        binding.list.setAdapter(adapter);
        binding.addCar.setOnClickListener(v ->
                        startActivity(new Intent(getContext(), CarAddActivity.class)));

        carsListViewModel = ViewModelProviders.of(getActivity(), carServiceViewModelFactory)
                .get(CarsListViewModel.class);

        carsListViewModel.getCars().observe(this, cars -> {
            adapter.setItems(cars);
        });

        return binding.getRoot();
    }

}
