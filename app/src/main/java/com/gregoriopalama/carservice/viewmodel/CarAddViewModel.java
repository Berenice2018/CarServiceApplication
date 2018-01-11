package com.gregoriopalama.carservice.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.support.design.widget.Snackbar;
import android.util.Log;

import com.gregoriopalama.carservice.data.Car;
import com.gregoriopalama.carservice.domain.CarRepository;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by gregorio on 10/01/2018.
 */

public class CarAddViewModel extends ViewModel {
    public CarRepository carRepository;

    @Inject
    public CarAddViewModel(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    private String carName;
    private String carPlate;

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public void addCar() {
        Car car = new Car();
        car.setName(getCarName());
        car.setPlate(getCarPlate());
        carRepository.insert(car).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i("TAG", "insert done");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("TAG", "error in insert");
                    }
                });
    }
}
