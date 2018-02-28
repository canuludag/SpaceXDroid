package com.uludag.can.spacexdroid.networking;

import com.uludag.can.spacexdroid.models.Launch;
import com.uludag.can.spacexdroid.models.Rocket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("rockets")
    Call<List<Rocket>> getRockets();

    @GET("launches")
    Call<List<Launch>> getLaunches();

    @GET("launches/upcoming")
    Call<List<Launch>> getUpcomingLaunches();
}
