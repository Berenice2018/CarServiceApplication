package com.gregoriopalama.carservice.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.gregoriopalama.carservice.data.Car;
import com.gregoriopalama.carservice.domain.CarRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by gregorio on 29/12/2017.
 */

public class CarsListViewModel extends ViewModel {

    public CarRepository carRepository;

    @Inject
    public CarsListViewModel(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public LiveData<List<Car>> getCars() {
        return carRepository.getAll();
    }
}
