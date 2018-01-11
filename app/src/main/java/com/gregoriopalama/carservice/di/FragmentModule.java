package com.gregoriopalama.carservice.di;


import com.gregoriopalama.carservice.ui.cars.CarAddFragment;
import com.gregoriopalama.carservice.ui.cars.CarsListFragment;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by gregorio on 09/01/2018.
 */

@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract CarsListFragment contributeCarsListFragment();

    @ContributesAndroidInjector
    abstract CarAddFragment contributeCarAddFragment();
}
