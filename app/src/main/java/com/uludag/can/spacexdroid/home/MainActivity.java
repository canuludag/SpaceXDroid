package com.uludag.can.spacexdroid.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.uludag.can.spacexdroid.R;
import com.uludag.can.spacexdroid.home.rockets.RocketsFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_rockets:
                        getSupportFragmentManager().beginTransaction()
                                .add(R.id.screen_container, new RocketsFragment())
                                .commit();
                        return true;
                    case R.id.navigation_launches:
                        return true;
                    case R.id.navigation_upcomings:
                        return true;
                }
                return false;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.screen_container, new RocketsFragment())
                .commit();
    }

}
