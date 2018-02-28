package com.uludag.can.spacexdroid.home.rockets;

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

import static android.view.View.GONE;

public class RocketsFragment extends Fragment {

    @Inject
    ViewModelFactory mViewModelFactory;

    @BindView(R.id.recycler_view_rockets)
    RecyclerView mRecyclerViewRockets;
    @BindView(R.id.tv_rockets_error)
    TextView mTvRocketsError;
    @BindView(R.id.loading_rockets)
    View mLoadingRockets;

    private Unbinder mUnbinder;
    private RocketsViewModel mRocketsViewModel;

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

        View view = inflater.inflate(R.layout.fragment_rockets, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRocketsViewModel = ViewModelProviders.
                of(this, mViewModelFactory).get(RocketsViewModel.class);

        mRecyclerViewRockets.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        mRecyclerViewRockets.setAdapter(new RocketsListAdapter(mRocketsViewModel,
                this));
        mRecyclerViewRockets.setLayoutManager(new LinearLayoutManager(getContext()));

        observeViewModel();
    }

    private void observeViewModel() {
        mRocketsViewModel.getRockets().observe(this, rockets -> {
            if (rockets != null) {
                mRecyclerViewRockets.setVisibility(View.VISIBLE);
            }
        });

        mRocketsViewModel.getError().observe(this, isError -> {
            //noinspection ConstantConditions
            if (isError) {
                mTvRocketsError.setVisibility(View.VISIBLE);
                mRecyclerViewRockets.setVisibility(GONE);
                mTvRocketsError.setText(R.string.api_error_rockets);
            } else {
                mTvRocketsError.setVisibility(GONE);
                mTvRocketsError.setText(null);
            }
        });

        mRocketsViewModel.getLoading().observe(this, isLoading -> {
            //noinspection ConstantConditions
            mLoadingRockets.setVisibility(isLoading ? View.VISIBLE : GONE);
            if (isLoading) {
                mTvRocketsError.setVisibility(GONE);
                mRecyclerViewRockets.setVisibility(GONE);
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
