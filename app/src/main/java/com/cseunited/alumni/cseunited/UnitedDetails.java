package com.cseunited.alumni.cseunited;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UnitedDetails extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_united_details);

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