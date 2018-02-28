package com.uludag.can.spacexdroid.home.rockets;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uludag.can.spacexdroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RocketsFragment extends Fragment {

    @BindView(R.id.recycler_view_rockets)
    RecyclerView mRecyclerViewRockets;
    @BindView(R.id.tv_rockets_error)
    TextView mTvRocketsError;
    @BindView(R.id.loading_rockets)
    View mLoadingRockets;

    private Unbinder mUnbinder;

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
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }
}
