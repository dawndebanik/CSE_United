package com.cseunited.alumni.cseunited;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EventActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inflating the layout with the drawer layout
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_event, null, false);
        fillView(inflater, contentView);
        mDrawer.addView(contentView, 0);

        //Setting up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Events");

        //Setting up hamburger icon
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        //Selecting the option in navigation view
        navigationView.getMenu().getItem(1).setChecked(true);
    }
    private void fillView(LayoutInflater inflater, View toFill){
        LinearLayout linearLayout = (LinearLayout) toFill.findViewById(R.id.event_linear);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        int[] banners = {R.drawable.cse_united_2017,
                R.drawable.cse_united_2016,
                R.drawable.cse_united_2015,
                R.drawable.cse_united_2014,
                R.drawable.cse_united_2013,
                R.drawable.cse_united_2012,
                R.drawable.cse_united_2011};
        int[] description = {R.string.description_cse_2017,
                R.string.description_cse_2016,
                R.string.description_cse_2015,
                R.string.description_cse_2014,
                R.string.description_cse_2013,
                R.string.description_cse_2012,
                R.string.description_cse_2011};
        int[] title = {R.string.united_title_2017,
                R.string.united_title_2016,
                R.string.united_title_2015,
                R.string.united_title_2014,
                R.string.united_title_2013,
                R.string.united_title_2012,
                R.string.united_title_2011,
        };
        for (int i=0;i<7;i++){
            View event = inflater.inflate(R.layout.event_item, null, false);
            ((ImageView)event.findViewById(R.id.event_banner)).setImageResource(banners[i]);
            ((TextView)event.findViewById(R.id.event_details)).setText(getString(description[i]));
            ((TextView)event.findViewById(R.id.event_title)).setText(getString(title[i]));
            linearLayout.addView(event);
        }
    }
}
