package com.gregoriopalama.carservice.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.gregoriopalama.carservice.viewmodel.CarAddViewModel;
import com.gregoriopalama.carservice.viewmodel.CarsListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by gregorio on 04/01/2018.
 */

@Module(includes = {RoomModule.class})
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CarsListViewModel.class)
    abstract ViewModel bindCarsListViewModel(CarsListViewModel carsListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CarAddViewModel.class)
    abstract ViewModel bindCarAddViewModel(CarAddViewModel carAddViewModel);


    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}
