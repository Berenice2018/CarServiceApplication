package com.gregoriopalama.carservice.domain;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.gregoriopalama.carservice.data.Car;
import com.gregoriopalama.carservice.data.CarDao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;

/**
 * Created by gregorio on 29/12/2017.
 */

@Singleton
public class CarRepositoryImpl implements CarRepository {
    private final CarDao carDao;

    @Inject
    public CarRepositoryImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public LiveData<List<Car>> getAll() {
        return carDao.selectAll();
    }

    @Override
    public Completable insert(Car... cars) {
        return Completable.fromAction(() -> carDao.insert(cars));
    }

    @Override
    public Completable update(Car car) {
        return Completable.fromAction(() -> carDao.update(car));
    }

    @Override
    public Completable delete(Car car) {
        return Completable.fromAction(() -> carDao.delete(car));
    }
}
