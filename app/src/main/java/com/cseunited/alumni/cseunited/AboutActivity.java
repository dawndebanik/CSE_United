package com.cseunited.alumni.cseunited;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sachin on 10/30/2017
 */

public class AboutActivity extends BaseActivity {

    Pair<String, String> name1 = new Pair<>("Name1", "Designation1");
    Pair<String, String> name2 = new Pair<>("Name2", "Designation2");
    List<Pair<String, String>> name = Arrays.asList(name1, name2);

    List<String> achievements  = Arrays.asList("Achievement1", "Achievement2", "Achievement3");
    List<String> publications  = Arrays.asList("Publication1", "Publication2");
    Pair<List<String>, List<String>> detail1 = new Pair<>(achievements, publications);
    Pair<List<String>, List<String>> detail2 = new Pair<>(achievements, publications);
    Map<String, Pair<List<String>, List<String>>> detail = new HashMap<>();
    Map<String, Integer> image = new HashMap<>();

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
      
        detail.put(name1.first, detail1);
        detail.put(name2.first, detail2);

        image.put(name1.first, R.drawable.mb_mam);
        image.put(name2.first, R.drawable.nc_mam);

        populateView();
    }

    void populateView(){
        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.faculty_expandable_view);
        FacultyExpandableListAdapter listAdapter = new FacultyExpandableListAdapter(this, image, name, detail);
        expandableListView.setAdapter(listAdapter);
    }
}