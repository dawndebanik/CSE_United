package com.cseunited.alumni.cseunited;
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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GalleryActivity extends BaseActivity implements GalleryAdapter.ImageClickListener {
    RecyclerView galleryView;
    Map<View, String> gallerySelector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inflating the layout with the drawer layout
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.coming_soon, null, false);
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

//        initGalleryView();
//        gallerySelector = new HashMap<>();
    }

    private void initGalleryView(){
        setContentView(R.layout.activity_gallery);
        galleryView = (RecyclerView) findViewById(R.id.gallery_recycler);
        List<URL> urls = new ArrayList<>();
        try {
            for(int i=0;i<20;i++) {
                urls.add(new URL("http://suyashmittal.com/cseunited/tc_sir.jpg"));
                urls.add(new URL("http://suyashmittal.com/cseunited/mb_mam.jpg"));
                urls.add(new URL("http://suyashmittal.com/cseunited/ud_sir.jpg"));
                urls.add(new URL("http://suyashmittal.com/cseunited/pd_mam.jpg"));
                urls.add(new URL("http://suyashmittal.com/cseunited/mkn_sir.jpg"));
                urls.add(new URL("http://suyashmittal.com/cseunited/nc_mam.jpg"));
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        List<String> labels= new ArrayList<>();
        for(int i=0;i<120;i++)
            labels.add("DUMMY TEXT");
        GalleryAdapter adapter = new GalleryAdapter(this, urls, labels);
        adapter.setImageClickListener(this);
        galleryView.setAdapter(adapter);
        galleryView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    public void onImageClick(View view) {
        //Toast.makeText(getApplicationContext(), "Click!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, GalleryDetailActivity.class));
    }
}
