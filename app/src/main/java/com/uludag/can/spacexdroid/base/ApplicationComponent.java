package com.uludag.can.spacexdroid.base;

import com.uludag.can.spacexdroid.home.launches.LaunchesFragment;
import com.uludag.can.spacexdroid.home.rockets.RocketsFragment;
import com.uludag.can.spacexdroid.home.upcomings.UpcomingsFragment;
import com.uludag.can.spacexdroid.networking.ApiModule;
import com.uludag.can.spacexdroid.viewmodel.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApiModule.class,
        ViewModelModule.class
})
public interface ApplicationComponent {

    void inject(RocketsFragment rocketsFragment);

    void inject(LaunchesFragment launchesFragment);

    void inject(UpcomingsFragment upcomingsFragment);

}
