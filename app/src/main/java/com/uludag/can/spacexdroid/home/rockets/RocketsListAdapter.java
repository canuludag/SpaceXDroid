package com.uludag.can.spacexdroid.home.rockets;

import android.arch.lifecycle.LifecycleOwner;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uludag.can.spacexdroid.R;
import com.uludag.can.spacexdroid.models.Rocket;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RocketsListAdapter extends RecyclerView.Adapter<RocketsListAdapter.RocketViewHolder>{

    private final List<Rocket> mData = new ArrayList<>();

    public RocketsListAdapter(RocketsViewModel viewModel, LifecycleOwner lifecycleOwner) {
        viewModel.getRockets().observe(lifecycleOwner, rockets -> {
            mData.clear();
            if (rockets != null) {
                mData.addAll(rockets);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public RocketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rockets_list_item, parent, false);
        return new RocketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RocketViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static final class RocketViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_rocket_name)
        TextView tvRocketName;
        @BindView(R.id.tv_rocket_description)
        TextView tvRocketDescription;

        private Rocket rocket;

        public RocketViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Rocket rocket) {
            this.rocket = rocket;
            tvRocketName.setText(rocket.name);
            tvRocketDescription.setText(rocket.description);
        }
    }
}
