package com.cseunited.alumni.cseunited;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Akriti on 11/11/2017.
 */

public class PlacementActivity extends  BaseActivity {
    ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placement_activity);
        expandableListView=(ExpandableListView) findViewById(R.id.exp_listview);
        List<String> Headings=new ArrayList<String>();
        List<String> L1=new ArrayList<String>();
        List<String> L2=new ArrayList<String>();
        List<String> L3=new ArrayList<String>();
        List<String> L4=new ArrayList<String>();
        List<String> L5=new ArrayList<String>();
        HashMap<String,List<String>> ChildList=new HashMap<String, List<String>>();
        String heading_items[]=getResources().getStringArray(R.array.header_titles);
        String l1[]=getResources().getStringArray(R.array.h1_items);
        String l2[]=getResources().getStringArray(R.array.h2_items);
        String l3[]=getResources().getStringArray(R.array.h3_items);
        String l4[]=getResources().getStringArray(R.array.h4_items);
        String l5[]=getResources().getStringArray(R.array.h5_items);
        for(String title:heading_items)
        {
            Headings.add(title);
        }
        for(String title:l1)
        {
            L1.add(title);
        }
        for(String title:l2)
        {
            L2.add(title);
        }
        for(String title:l3)
        {
            L3.add(title);
        }
        for(String title:l4)
        {
            L4.add(title);
        }
        for(String title:l5)
        {
            L5.add(title);
        }
        ChildList.put(Headings.get(0),L1);
        ChildList.put(Headings.get(1),L2);
        ChildList.put(Headings.get(2),L3);
        ChildList.put(Headings.get(3),L4);
        ChildList.put(Headings.get(4),L5);
        PlacementAdapter myAdapter=new PlacementAdapter(this,Headings,ChildList);
        expandableListView.setAdapter(myAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //Handle action bar item clicks here.The action bar will
        //automatically handle clicks on the Home/Up button,so long
        //as you specifify a parent activity in AndroidManifest.xml
        int id=item.getItemId();

        if(id==R.id.action_theme)
            return true;
        return super.onOptionsItemSelected(item);
    }
}
