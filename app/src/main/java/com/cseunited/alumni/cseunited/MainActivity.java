package com.cseunited.alumni.cseunited;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private AppCompatButton loginBtn, forumBtn, detailsBtn;

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
        initObjects();
    }

    private void initViews() {
        loginBtn = (AppCompatButton) findViewById(R.id.login_view);
        forumBtn = (AppCompatButton) findViewById(R.id.forum_view);
        detailsBtn = (AppCompatButton) findViewById(R.id.details_view);
    }

    private void initListeners() {
        loginBtn.setOnClickListener(this);
        forumBtn.setOnClickListener(this);
        detailsBtn.setOnClickListener(this);
    }

    private void initObjects() {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.events) {

        } else if (id == R.id.gallery) {

        } else if (id == R.id.about) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
            Intent intent = new Intent(this, UnitedDetails.class);
            startActivity(intent);
        }
    }
}

