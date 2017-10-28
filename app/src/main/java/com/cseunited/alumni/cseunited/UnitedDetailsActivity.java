package com.cseunited.alumni.cseunited;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UnitedDetailsActivity extends BaseActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inflating the layout with the drawer layout
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_united_details, null, false);
        mDrawer.addView(contentView, 0);

        //Setting up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Setting up hamburger icon
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Adding child data
        listDataHeader.add(getString(R.string.cse_2015));
        listDataHeader.add(getString(R.string.cse_2014));
        listDataHeader.add(getString(R.string.cse_2013));
        listDataHeader.add(getString(R.string.cse_2012));
        listDataHeader.add(getString(R.string.cse_2011));

        // Adding child data
        List<String> u15 = new ArrayList<>();
        u15.add(getString(R.string.description_cse_2015));

        List<String> u14 = new ArrayList<>();
        u14.add(getString(R.string.description_cse_2014));

        List<String> u13 = new ArrayList<>();
        u13.add(getString(R.string.description_cse_2013));

        List<String> u12 = new ArrayList<>();
        u12.add(getString(R.string.description_cse_2012));

        List<String> u11 = new ArrayList<>();
        u11.add(getString(R.string.description_cse_2011));

        listDataChild.put(listDataHeader.get(0), u15); // Header, Child data
        listDataChild.put(listDataHeader.get(1), u14);
        listDataChild.put(listDataHeader.get(2), u13);
        listDataChild.put(listDataHeader.get(3), u12);
        listDataChild.put(listDataHeader.get(4), u11);
    }
}