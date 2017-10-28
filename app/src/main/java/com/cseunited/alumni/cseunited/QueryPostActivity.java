package com.cseunited.alumni.cseunited;

import android.content.Context;
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
 * Created by Sachin on 10/28/2017
 * Edited by Suyash
 */

public class QueryPostActivity extends BaseActivity implements View.OnClickListener {

    private TextInputEditText textInputEditTitle;
    private TextInputEditText textInputEditDesc;
    private AppCompatButton appCompatButtonPost;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inflating the drawer layout
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

        textInputEditTitle = (TextInputEditText) findViewById(R.id.new_question_title);
        textInputEditDesc = (TextInputEditText) findViewById(R.id.new_question_desc);
        appCompatButtonPost = (AppCompatButton) findViewById(R.id.new_question_btn);
        appCompatButtonPost.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String title = textInputEditTitle.getText().toString();
        String desc = textInputEditDesc.getText().toString();
        //Do web thing
        Toast.makeText(this, "POSTED...", Toast.LENGTH_SHORT).show();
        finish();
    }
}
