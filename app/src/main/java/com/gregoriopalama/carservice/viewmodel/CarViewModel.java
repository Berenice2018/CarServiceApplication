package com.gregoriopalama.carservice.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.gregoriopalama.carservice.data.Car;
import com.gregoriopalama.carservice.domain.CarRepository;

import javax.inject.Inject;

/**
 * Created by gregorio on 14/01/2018.
 */

public class CarViewModel extends ViewModel {
    private static final MutableLiveData ABSENT = new MutableLiveData();
    {
        ABSENT.setValue(null);
    }

    private final LiveData<Car> carObservable;
    private final MutableLiveData<String> plate;

    public ObservableField<Car> car = new ObservableField<>();

    public CarRepository carRepository;

    @Inject
    public CarViewModel(CarRepository carRepository) {
        this.carRepository = carRepository;

        this.plate = new MutableLiveData<>();

        carObservable = Transformations.switchMap(plate, input -> {
            if (input.isEmpty()) {
                return ABSENT;
            }

            return this.carRepository.getFromPlate(plate.getValue());
        });
    }

    public LiveData<Car> getObservableCar() {
        return carObservable;
    }

    public void setCar(Car car) {
        this.car.set(car);
    }

    public void setPlate(String plate) {
        this.plate.setValue(plate);
    }
}
