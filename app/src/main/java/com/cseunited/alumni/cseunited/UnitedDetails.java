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
        List<String> united_2015 = new ArrayList<>();
        united_2015.add(getString(R.string.description_cse_2015));

        List<String> united_2014 = new ArrayList<>();
        united_2014.add(getString(R.string.description_cse_2014));

        List<String> united_2013 = new ArrayList<>();
        united_2013.add(getString(R.string.description_cse_2013));

        List<String> united_2012 = new ArrayList<>();
        united_2012.add(getString(R.string.description_cse_2012));

        List<String> united_2011 = new ArrayList<>();
        united_2011.add(getString(R.string.description_cse_2011));

        listDataChild.put(listDataHeader.get(0), united_2015); // Header, Child data
        listDataChild.put(listDataHeader.get(1), united_2014);
        listDataChild.put(listDataHeader.get(2), united_2013);
        listDataChild.put(listDataHeader.get(3), united_2012);
        listDataChild.put(listDataHeader.get(4), united_2011);
    }
}