package com.gregoriopalama.carservice.di;

import android.app.Application;
import android.content.Context;

import com.gregoriopalama.carservice.CarServiceApplication;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;

/**
 * Created by gregorio on 09/01/2018.
 */

@Module(includes = {AndroidInjectionModule.class, ViewModelModule.class})
public class AppModule {

    @Provides
    Application provideContext(CarServiceApplication application) {
        return application;
    }
}
