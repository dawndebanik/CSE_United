package com.cseunited.alumni.cseunited;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Akriti on 11/11/2017
 * Edited by Suyash
 */

public class PlacementActivity extends  BaseActivity {

    private final String url = "http://cseunited.com/app/placement.json";
    private ExpandableListView expandableListView;
    private ProgressDialog progressDialog;
    private int lastExpandedPosition = -1;

    List<String> batches = new ArrayList<>();
    Map<String, Map<String, List<String>>> details = new HashMap<>();
    Map<String, List<String>> cnames = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Inflating the layout with the drawer layout
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_placement, null, false);
        mDrawer.addView(contentView, 0);

        //Setting up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Placements");

        //Setting up hamburger icon
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        //Selecting the option in navigation view
        navigationView.getMenu().getItem(5).setChecked(true);

        expandableListView = (ExpandableListView) findViewById(R.id.placement_expandable_list_view);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition)
                    expandableListView.collapseGroup(lastExpandedPosition);
                lastExpandedPosition = groupPosition;
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        volleyRequest();
    }

    private void volleyRequest(){
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                processJson(response);
                progressDialog.cancel();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressDialog.cancel();
                Toast.makeText(PlacementActivity.this, "Error fetching data from server...", Toast.LENGTH_SHORT).show();
            }
        });

        VolleyChannel.getInstance(this).addToRequestQueue(jsObjRequest, this);
    }

    private void processJson(JSONObject response){
        try {
            JSONArray placements = response.getJSONArray("placements");
            for (int i=0;i<placements.length();i++){
                JSONObject batch = placements.getJSONObject(i);
                batches.add(batch.getString("batch"));
                JSONArray c = batch.getJSONArray("details");
                Map<String, List<String>> detail = new HashMap<>();
                List<String> companies = new ArrayList<>();
                for (int j=0;j<c.length();j++){
                    JSONObject company = c.getJSONObject(j);
                    String cname = company.getString("company");
                    companies.add(cname);
                    JSONArray n = company.getJSONArray("names");
                    List<String> names = new ArrayList<>();
                    for (int k=0;k<n.length();k++)
                        names.add(n.getString(k));
                    detail.put(cname, names);
                }
                cnames.put(batch.getString("batch"), companies);
                details.put(batch.getString("batch"), detail);
            }

            expandableListView.setAdapter(new PlacementExpandableListAdapter(this, batches, cnames, details));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
