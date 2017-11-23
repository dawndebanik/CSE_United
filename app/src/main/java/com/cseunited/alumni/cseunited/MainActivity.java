package com.cseunited.alumni.cseunited;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private AppCompatButton loginBtn, forumBtn, detailsBtn, eventsBtn, homeBtn, placementBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inflating the layout with the drawer layout
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_main, null, false);
        mDrawer.addView(contentView, 0);

        //Setting up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Setting up hamburger icon
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        //Selecting the option in navigation view
        navigationView.getMenu().getItem(0).setChecked(true);

        initViews();
        initListeners();
    }

    private void initViews() {
        loginBtn = (AppCompatButton) findViewById(R.id.login_view);
        forumBtn = (AppCompatButton) findViewById(R.id.forum_view);
        detailsBtn = (AppCompatButton) findViewById(R.id.details_view);
        eventsBtn = (AppCompatButton) findViewById(R.id.events_view);
        homeBtn = (AppCompatButton) findViewById(R.id.home_view);
        placementBtn = (AppCompatButton) findViewById(R.id.placement_view);
    }

    private void initListeners() {
        loginBtn.setOnClickListener(this);
        forumBtn.setOnClickListener(this);
        detailsBtn.setOnClickListener(this);
        eventsBtn.setOnClickListener(this);
        homeBtn.setOnClickListener(this);
        placementBtn.setOnClickListener(this);
        Button b1 = (Button) findViewById(R.id.test);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GalleryActivity.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.login_view){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.forum_view){
            Intent intent = new Intent(this, DiscussActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.details_view){
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.events_view){
            Intent intent = new Intent(this, EventActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.home_view){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.placement_view){
            Intent intent = new Intent(this, PlacementActivity.class);
            startActivity(intent);
        }
    }
}

