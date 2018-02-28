package com.uludag.can.spacexdroid.home.upcomings;

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

public class UpcomingsFragment extends Fragment {

    @BindView(R.id.recycler_view_upcomings)
    RecyclerView mRecyclerViewUpcomings;
    @BindView(R.id.tv_upcomings_error)
    TextView mTvUpcomingsError;
    @BindView(R.id.loading_upcomings)
    View mLoadingUpcomings;

    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_upcomings, container, false);
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
