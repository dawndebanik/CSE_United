package com.cseunited.alumni.cseunited;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private final List<Integer> images = Arrays.asList(R.drawable.slider_1, R.drawable.slider_2, R.drawable.slider_3, R.drawable.slider_4, R.drawable.slider_5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inflating the layout with the drawer layout
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_home, null, false);
        mDrawer.addView(contentView, 0);

        //Setting up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("HOME");

        //Setting up hamburger icon
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        //Selecting the option in navigation view
        navigationView.getMenu().getItem(0).setChecked(true);

        CardView cardView = (CardView) findViewById(R.id.card_view);
        cardView.setOnClickListener(this);

        init();
    }

    private void init() {
        mPager = (ViewPager) findViewById(R.id.home_slider);
        mPager.setAdapter(new HomePagerAdapter(this, images));
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == images.size()) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2000, 2000);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.card_view){
            Intent intent = new Intent(this, EventActivity.class);
            startActivity(intent);
        }
    }
}





