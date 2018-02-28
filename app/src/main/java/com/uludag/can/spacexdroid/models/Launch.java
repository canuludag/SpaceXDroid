package com.uludag.can.spacexdroid.models;

import com.squareup.moshi.Json;

public class Launch {

    @Json(name = "launch_year")
    public final int launchYear;
    @Json(name = "launch_success")
    public final boolean success;
    public final String details;
    public final Rocket rocket;

    public Launch(int launchYear, boolean success, String details, Rocket rocket) {
        this.launchYear = launchYear;
        this.success = success;
        this.details = details;
        this.rocket = rocket;
    }
}
