package com.uludag.can.spacexdroid.base;

import android.app.Application;
import android.content.Context;

public class SpaceXDroidApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.create();
    }

    public static ApplicationComponent getApplicationComponent(Context context) {
        return ((SpaceXDroidApplication) context.getApplicationContext()).mApplicationComponent;
    }
}
