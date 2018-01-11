package com.gregoriopalama.carservice.ui.cars;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gregoriopalama.carservice.databinding.FragmentCarAddBinding;
import com.gregoriopalama.carservice.di.Injectable;
import com.gregoriopalama.carservice.di.ViewModelFactory;
import com.gregoriopalama.carservice.viewmodel.CarAddViewModel;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarAddFragment extends Fragment implements Injectable {
    FragmentCarAddBinding binding;

    @Inject
    ViewModelFactory carServiceViewModelFactory;

    private CarAddViewModel carAddViewModel;

    public CarAddFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCarAddBinding.inflate(inflater, container, false);
        setupViewModel();
        setupTextChangedListeners();
        return binding.getRoot();
    }

    private void setupViewModel() {
        carAddViewModel = ViewModelProviders.of(getActivity(), carServiceViewModelFactory)
                .get(CarAddViewModel.class);
        binding.carName.setText(carAddViewModel.getCarName());
        binding.carPlate.setText(carAddViewModel.getCarPlate());
    }

    private void setupTextChangedListeners() {
        binding.carName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                carAddViewModel.setCarName(s.toString());
            }
        });
        binding.carPlate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                carAddViewModel.setCarPlate(s.toString());
            }
        });
    }

}
