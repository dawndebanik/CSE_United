package com.cseunited.alumni.cseunited;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private final String url = "http://suyashmittal.000webhostapp.com/cseunited/home_slider.json"; //Temporary url for testing

    private static ViewPager mPager;
    private int currentPage = 0;
    private final List<String> images = new ArrayList<>();
    private Timer swipeTimer;
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
        getSupportActionBar().setTitle("Home");

        //Setting up hamburger icon
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        //Selecting the option in navigation view
        navigationView.getMenu().getItem(0).setChecked(true);

        CardView cardView = (CardView) findViewById(R.id.card_view);
        cardView.setOnClickListener(this);

        volleyRequest();
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
        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3500, 3500);
    }

    private void volleyRequest(){
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                processJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(HomeActivity.this, "Error fetching data from server...", Toast.LENGTH_SHORT).show();
            }
        });

        VolleyChannel.getInstance(this).addToRequestQueue(jsObjRequest, this);
    }

    private void processJson(JSONObject response){
        try{
            JSONArray images = response.getJSONArray("images");
            for(int i=0;i<images.length();i++)
                this.images.add(images.getString(i));
            init();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.card_view){
            Intent intent = new Intent(this, EventActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        swipeTimer.cancel();
    }
}





