package com.gregoriopalama.carservice.di;

import android.app.Application;
import android.content.Context;

import com.gregoriopalama.carservice.data.CarDao;
import com.gregoriopalama.carservice.data.CarServiceDatabase;
import com.gregoriopalama.carservice.domain.CarRepository;
import com.gregoriopalama.carservice.domain.CarRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gregorio on 29/12/2017.
 */

@Module
public class RoomModule {

    @Singleton
    @Provides
    CarServiceDatabase providesCarServiceDatabase(Application context) {
        return CarServiceDatabase.getInstance(context);
    }

    @Singleton
    @Provides
    CarDao providesCarDao(CarServiceDatabase db) {
        return db.carDao();
    }

    @Singleton
    @Provides
    CarRepository providesCarRepository(CarDao carDao) {
        return new CarRepositoryImpl(carDao);
    }
}
