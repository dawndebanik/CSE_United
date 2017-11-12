package com.cseunited.alumni.cseunited;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends BaseActivity {

    private ViewPager mPager;
    private int currentPage = 0;
    private List<Integer> images = new ArrayList<>(Arrays.asList(R.drawable.mb_mam,R.drawable.mkn_sir,R.drawable.nc_mam,R.drawable.pd_mam,R.drawable.tc_sir,R.drawable.ud_sir));

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

        //Setting up hamburger icon
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        mPager = (ViewPager) findViewById(R.id.home_viewpager);
        mPager.setAdapter(new HomePagerAdapter(this, images));

        startSlide();
    }

    private void startSlide() {
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
        }, 1000, 3000);
    }

}





