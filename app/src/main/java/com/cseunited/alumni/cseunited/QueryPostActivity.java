package com.cseunited.alumni.cseunited;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Sachin on 10/28/2017.
 */

public class QueryPostActivity extends BaseActivity implements View.OnClickListener {
    private TextInputEditText textInputEditQustion;
    private TextInputEditText textInputEditQuery;
    private AppCompatButton appCompatButtonRegister;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_add_question, null, false);
        mDrawer.addView(contentView, 0);

        //Setting up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Setting up hamburger icon
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.getMenu().getItem(3).setChecked(true);

        textInputEditQustion = (TextInputEditText) findViewById(R.id.QustionTitle);
        textInputEditQuery = (TextInputEditText) findViewById(R.id.AddQuery);
        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.PostQuery);
        appCompatButtonRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Toast.makeText(this,"Thanks for posting",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DiscussActivity.class);
        startActivity(intent);

    }
}
