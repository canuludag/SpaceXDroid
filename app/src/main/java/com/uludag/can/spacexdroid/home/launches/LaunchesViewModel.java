package com.uludag.can.spacexdroid.home.launches;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.uludag.can.spacexdroid.models.Launch;
import com.uludag.can.spacexdroid.networking.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaunchesViewModel extends ViewModel {

    private final MutableLiveData<List<Launch>> mLaunches = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mLaunchesLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mLoading = new MutableLiveData<>();

    private final ApiService mApiService;
    private Call<List<Launch>> mLaunchesCall;

    public LaunchesViewModel(ApiService apiService) {
        mApiService = apiService;
        fetchLaunches();
    }

    LiveData<List<Launch>> getLaunches() {
        return mLaunches;
    }

    LiveData<Boolean> getError() {
        return mLaunchesLoadError;
    }

    LiveData<Boolean> getLoading() {
        return mLoading;
    }

    private void fetchLaunches() {
        mLoading.setValue(true);
        mLaunchesCall = mApiService.getLaunches();
        mLaunchesCall.enqueue(new Callback<List<Launch>>() {
            @Override
            public void onResponse(Call<List<Launch>> call, Response<List<Launch>> response) {
                mLaunchesLoadError.setValue(false);
                mLaunches.setValue(response.body());
                mLoading.setValue(false);
                mLaunchesCall = null;
            }

            @Override
            public void onFailure(Call<List<Launch>> call, Throwable t) {
                Log.e(getClass().getSimpleName(), "Error loading rockets", t);
                mLaunchesLoadError.setValue(true);
                mLoading.setValue(false);
                mLaunchesCall = null;
            }
        });
    }

    @Override
    protected void onCleared() {
        if (mLaunchesCall != null) {
            mLaunchesCall.cancel();
            mLaunchesCall = null;
        }
    }
}
