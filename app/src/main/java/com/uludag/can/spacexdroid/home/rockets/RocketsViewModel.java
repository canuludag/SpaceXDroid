package com.uludag.can.spacexdroid.home.rockets;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.uludag.can.spacexdroid.models.Rocket;
import com.uludag.can.spacexdroid.networking.ApiService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RocketsViewModel extends ViewModel {

    private final MutableLiveData<List<Rocket>> mRockets = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mRocketsLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mLoading = new MutableLiveData<>();

    private final ApiService mApiService;
    private Call<List<Rocket>> mRocketsCall;

    @Inject
    public RocketsViewModel(ApiService apiService) {
        mApiService = apiService;
        fetchRockets();
    }

    LiveData<List<Rocket>> getRockets() {
        return mRockets;
    }

    LiveData<Boolean> getError() {
        return mRocketsLoadError;
    }

    LiveData<Boolean> getLoading() {
        return mLoading;
    }

    private void fetchRockets() {
        mLoading.setValue(true);
        mRocketsCall = mApiService.getRockets();
        mRocketsCall.enqueue(new Callback<List<Rocket>>() {
            @Override
            public void onResponse(Call<List<Rocket>> call, Response<List<Rocket>> response) {
                mRocketsLoadError.setValue(false);
                mRockets.setValue(response.body());
                mLoading.setValue(false);
                mRocketsCall = null;
            }

            @Override
            public void onFailure(Call<List<Rocket>> call, Throwable t) {
                Log.e(getClass().getSimpleName(), "Error loading rockets", t);
                mRocketsLoadError.setValue(true);
                mLoading.setValue(false);
                mRocketsCall = null;
            }
        });
    }

    @Override
    protected void onCleared() {
        if (mRocketsCall != null) {
            mRocketsCall.cancel();
            mRocketsCall = null;
        }
    }
}
