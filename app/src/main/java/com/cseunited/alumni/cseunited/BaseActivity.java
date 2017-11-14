package com.cseunited.alumni.cseunited;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Suyash on 10/28/2017
 */

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    protected DrawerLayout mDrawer;
    protected NavigationView navigationView;
    private Map<Integer, Class> activitySelector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.drawer_layout);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initializeActivitySelector();
    }

    /**
     * Fill the HashMap with the ids of the items in the nav_drawer
     * and its corresponding target class.
     */
    private void initializeActivitySelector(){
        activitySelector = new HashMap<>();
        activitySelector.put(R.id.nav_home, MainActivity.class);
        activitySelector.put(R.id.nav_forum, DiscussActivity.class);
        activitySelector.put(R.id.nav_achievement, AboutActivity.class);
        activitySelector.put(R.id.nav_placement, PlacementActivity.class);
        activitySelector.put(R.id.nav_events, EventActivity.class);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if(mDrawer.isDrawerOpen(GravityCompat.START))
            mDrawer.closeDrawer(GravityCompat.START);

        Class target = activitySelector.get(item.getItemId());
        if(this.getClass()!=target && target!=null) { // to prevent redirection to the same page.
            startActivity(new Intent(this, target));
            this.finish();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        if (this instanceof MainActivity)
            this.finishAffinity();
        else if(this.isTaskRoot()){
            startActivity(new Intent(this, MainActivity.class));
            this.finish();
        }
    }

    @Override
    protected void onStop() {
        VolleyChannel.getInstance(this).getRequestQueue().cancelAll(this);
        super.onStop();
    }
}
