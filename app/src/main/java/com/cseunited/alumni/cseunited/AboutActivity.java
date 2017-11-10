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
 * Created by Sachin on 10/30/2017
 * Edited by Suyash
 */

public class AboutActivity extends BaseActivity {

    private final String url = "http://192.168.0.100/cseunited/faculty.json"; //Temporary url for testing

    List<Pair<String, String>> names = new ArrayList<>();
    Map<String, Pair<List<String>, List<String>>> details = new HashMap<>();
    Map<String, String> images = new HashMap<>();

    private ExpandableListView expandableListView;
    private int lastExpandedPosition = -1;

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

        volleyRequest();

        expandableListView = (ExpandableListView) findViewById(R.id.faculty_expandable_view);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition)
                    expandableListView.collapseGroup(lastExpandedPosition);
                lastExpandedPosition = groupPosition;
            }
        });
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
            }
        });

        VolleyChannel.getInstance(this).addToRequestQueue(jsObjRequest, this);
    }

    private void processJson(JSONObject response){
        try {
            JSONArray faculty = response.getJSONArray("faculty");
            for (int i=0;i<faculty.length();i++){
                JSONObject f = faculty.getJSONObject(i);
                Pair<String, String> name = new Pair<>(f.getString("name"), f.getString("qual"));
                names.add(name);
                images.put(f.getString("name"), f.getString("image"));
                JSONArray a = f.getJSONArray("achievements");
                JSONArray p = f.getJSONArray("publications");
                List<String> achievements = new ArrayList<>();
                List<String> publications = new ArrayList<>();
                for (int j=0;j<a.length();j++){
                    achievements.add((j+1)+". "+a.getString(j)+"\n");
                }
                for (int j=0;j<p.length();j++){
                    publications.add((j+1)+". "+p.getString(j)+"\n");
                }
                Pair<List<String>, List<String>> detail = new Pair<>(achievements, publications);
                details.put(f.getString("name"), detail);

                FacultyExpandableListAdapter listAdapter = new FacultyExpandableListAdapter(this, images, names, details);
                expandableListView.setAdapter(listAdapter);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
