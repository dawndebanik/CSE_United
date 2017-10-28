package com.cseunited.alumni.cseunited;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AboutTeachers extends AppCompatActivity {

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
        listDataHeader.add(getString(R.string.PD_mam));
        listDataHeader.add(getString(R.string.MB_mam));
        listDataHeader.add(getString(R.string.NC_mam));
        listDataHeader.add(getString(R.string.Tapan_Sir));
        listDataHeader.add(getString(R.string.MKN_Sir));

        // Adding child data
        List<String> PD_mam_says = new ArrayList<>();
        PD_mam_says.add(getString(R.string.PD_mam_words));

        List<String> MB_mam_says = new ArrayList<>();
        MB_mam_says.add(getString(R.string.MB_mam_words));

        List<String> NC_mam_says = new ArrayList<>();
        NC_mam_says.add(getString(R.string.NC_mam_words));

        List<String> Tapan_sir_says = new ArrayList<>();
        Tapan_sir_says.add(getString(R.string.Tapan_sir_words));

        List<String> MKN_sir_says = new ArrayList<>();
        MKN_sir_says.add(getString(R.string.MKN_sir_words));

        listDataChild.put(listDataHeader.get(0), PD_mam_says); // Header, Child data
        listDataChild.put(listDataHeader.get(1), MB_mam_says);
        listDataChild.put(listDataHeader.get(2), NC_mam_says);
        listDataChild.put(listDataHeader.get(3), Tapan_sir_says);
        listDataChild.put(listDataHeader.get(4), MKN_sir_says);
    }
}
