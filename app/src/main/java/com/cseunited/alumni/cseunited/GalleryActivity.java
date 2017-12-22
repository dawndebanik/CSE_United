package com.cseunited.alumni.cseunited;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
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
import java.util.Map;
import java.util.HashMap;

public class GalleryActivity extends BaseActivity implements GalleryAdapter.ImageClickListener {
    RecyclerView galleryView;
    Map<View, String> gallerySelector;
    String url = "http://suyashmittal.000webhostapp.com/cseunited/gallery.json";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inflating the layout with the drawer layout
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_gallery, null, false);
        mDrawer.addView(contentView, 0);

        //Setting up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Gallery");

        //Setting up hamburger icon
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        //Selecting the option in navigation view
        navigationView.getMenu().getItem(2).setChecked(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        volleyRequest();
        gallerySelector = new HashMap<>();
    }

    private void volleyRequest(){
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                initGalleryView(response);
                progressDialog.cancel();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressDialog.cancel();
                Toast.makeText(GalleryActivity.this, "Error fetching data from server...", Toast.LENGTH_SHORT).show();
            }
        });

        VolleyChannel.getInstance(this).addToRequestQueue(jsObjRequest, this);
    }

    private void initGalleryView(JSONObject response){
        galleryView = (RecyclerView) findViewById(R.id.gallery_recycler);
        List<String> urls = new ArrayList<>();
        try{
            JSONArray images = response.getJSONArray("images");
            for(int i=0;i<images.length();i++){
                urls.add(images.getString(i));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        GalleryAdapter adapter = new GalleryAdapter(this, urls);
        adapter.setImageClickListener(this);
        galleryView.setAdapter(adapter);
        galleryView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    public void onImageClick(View view) {
        //Toast.makeText(getApplicationContext(), "Click!", Toast.LENGTH_LONG).show();
        //startActivity(new Intent(this, GalleryDetailActivity.class));
    }
}
