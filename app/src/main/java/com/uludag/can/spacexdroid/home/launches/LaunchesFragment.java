package com.uludag.can.spacexdroid.home.launches;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uludag.can.spacexdroid.R;
import com.uludag.can.spacexdroid.base.SpaceXDroidApplication;
import com.uludag.can.spacexdroid.viewmodel.ViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LaunchesFragment extends Fragment {

    @Inject
    ViewModelFactory mViewModelFactory;

    @BindView(R.id.recycler_view_launches)
    RecyclerView mRecyclerViewLaunches;
    @BindView(R.id.tv_launches_error)
    TextView mTvLaunchesError;
    @BindView(R.id.loading_launches)
    View mLoadingLaunches;

    private Unbinder mUnbinder;
    private LaunchesViewModel mLaunchesViewModel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        SpaceXDroidApplication.getApplicationComponent(context).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_launches, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mLaunchesViewModel = ViewModelProviders
                .of(this, mViewModelFactory).get(LaunchesViewModel.class);

        mRecyclerViewLaunches
                .addItemDecoration(new DividerItemDecoration(getContext(),
                        DividerItemDecoration.VERTICAL));
        mRecyclerViewLaunches.setLayoutManager(new LinearLayoutManager(getContext()));

        observeViewModel();
    }

    private void observeViewModel() {
        mLaunchesViewModel.getLaunches().observe(this, launches -> {
            if (launches != null) {
                mRecyclerViewLaunches.setVisibility(View.VISIBLE);
            }
        });

        mLaunchesViewModel.getError().observe(this, isError -> {
            //noinspection ConstantConditions
            if (isError) {
                mTvLaunchesError.setVisibility(View.VISIBLE);
                mRecyclerViewLaunches.setVisibility(View.GONE);
                mTvLaunchesError.setText(R.string.api_error_launches);
            } else {
                mTvLaunchesError.setVisibility(View.GONE);
                mTvLaunchesError.setText(null);
            }
        });

        mLaunchesViewModel.getLoading().observe(this, isLoading -> {
            //noinspection ConstantConditions
            if (isLoading) {
                mTvLaunchesError.setVisibility(View.GONE);
                mRecyclerViewLaunches.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }
}
