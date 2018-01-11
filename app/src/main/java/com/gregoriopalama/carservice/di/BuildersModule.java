package com.gregoriopalama.carservice.di;

import com.gregoriopalama.carservice.ui.cars.CarAddActivity;
import com.gregoriopalama.carservice.ui.cars.CarsListFragment;
import com.gregoriopalama.carservice.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by gregorio on 09/01/2018.
 */

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector(modules = {FragmentModule.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {FragmentModule.class})
    abstract CarAddActivity bindCarAddActivity();
}
