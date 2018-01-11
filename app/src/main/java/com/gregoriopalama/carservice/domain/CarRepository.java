package com.gregoriopalama.carservice.domain;

import android.arch.lifecycle.LiveData;

import com.gregoriopalama.carservice.data.Car;

import java.util.List;

import io.reactivex.Completable;

/**
 * Created by gregorio on 29/12/2017.
 */

public interface CarRepository {
    public LiveData<List<Car>> getAll();
    public Completable insert(Car... cars);
    public Completable update(Car car);
    public Completable delete(Car car);
}
