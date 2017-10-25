package com.cseunited.alumni.cseunited;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by Client on 10/22/2017.
 */

public class guideline_main extends AppCompatActivity implements Communicator{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void respond(int position) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentB f2 = (FragmentB) manager.findFragmentById(R.id.fragment2);
        f2.changeData(position);
    }

    /**
     *
     * @param menu
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    /**
     *
     * @param item
     * @return true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch(id)
        {
            case R.id.home:
                Toast.makeText(this,"Home clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.events:
                Toast.makeText(this,"Events clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.gallery:
                Toast.makeText(this,"Gallery clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.cseunited:
                Toast.makeText(this,"cse united clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:
                Toast.makeText(this,"About us clicked",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}

