package com.cseunited.alumni.cseunited;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sachin on 10/30/2017.
 */

public class AboutActivity extends BaseActivity {

    ListView list;
    String[] teacher = {
            "cse",
            "cse_logo"
    } ;
    String[] teacher_desig = {
            "cse",
            "cse_logo"
    } ;
    Integer[] imageId = {
            R.drawable.cse,
            R.drawable.cse_logo1

    };
    String achiev;
    private TextView achievements;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inflating the layout with the drawer layout
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_about, null, false);
        mDrawer.addView(contentView, 0);

        //Setting up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Setting up hamburger icon
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        achievements=(TextView) findViewById(R.id.achievemnts);
        //setting custom view
        CustomList adapter = new
                CustomList(AboutActivity.this, teacher, teacher_desig, imageId);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(AboutActivity.this, "You Clicked at " +teacher[+ position], Toast.LENGTH_SHORT).show();
                //achievements.setText("item clicked");


            }
        });
    }

}