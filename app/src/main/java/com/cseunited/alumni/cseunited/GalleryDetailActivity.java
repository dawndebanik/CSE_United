package com.cseunited.alumni.cseunited;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GalleryDetailActivity extends AppCompatActivity {
    RecyclerView galleryView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        initGalleryView();
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
//        GalleryAdapter adapter = new GalleryAdapter(this, urls, labels);
//        galleryView.setAdapter(adapter);
//        galleryView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }
}
