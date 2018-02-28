package com.uludag.can.spacexdroid.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.uludag.can.spacexdroid.home.launches.LaunchesViewModel;
import com.uludag.can.spacexdroid.home.rockets.RocketsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RocketsViewModel.class)
    abstract ViewModel bindRocketsViewModel(RocketsViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LaunchesViewModel.class)
    abstract ViewModel bindLaunchesViewModel(LaunchesViewModel viewModel);
}
