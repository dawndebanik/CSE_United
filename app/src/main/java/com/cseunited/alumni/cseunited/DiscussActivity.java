package com.cseunited.alumni.cseunited;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DiscussActivity extends BaseActivity implements DiscussAdapter.ItemClickListener {
    RecyclerView discussionView;
    FloatingActionButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inflating the layout with the drawer layout
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_coming_soon, null, false);
        mDrawer.addView(contentView, 0);

        //Setting up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Forum");

        //Setting up hamburger icon
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        //Selecting the option in navigation view
        navigationView.getMenu().getItem(3).setChecked(true);

//        discussionView = (RecyclerView) findViewById(R.id.recycler);
//        DiscussAdapter adapter = new DiscussAdapter(getApplicationContext(), fetchListOfPosts());
//        adapter.setClickListener(this);
//        discussionView.setAdapter(adapter);
//        discussionView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        addButton = (FloatingActionButton) findViewById(R.id.addButton);
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onAddButtonClick(view);
//            }
//        });
    }

    /**
     * This is a dummy method to fill the list of posts in the forum with testing content.
     * The code inside is to be replaced by code to fetch list of posts from the internet.
     *
     * @return List of "DiscussionItems" objects representing each post in the forum.
     */
    List<DiscussItem> fetchListOfPosts() {
        int count = 1;
        List<DiscussItem> list = new ArrayList<>();
        while (count < 11)
            list.add(new DiscussItem("Question: This is supposed to be a really long line #" + count++, "Post by: Name LastName"));
        return list;
    }

    /**
     * Code to be executed when the user clicks on one item (i.e. the a post)/.
     *
     * @param view     The view clicked.
     * @param position The position of the clicked view.
     */
    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, QuestionDetailActivity.class);
        startActivity(intent);
        Toast.makeText(this, (position+1)+" Clicked", Toast.LENGTH_LONG).show();
    }

    /**
     * Code to be run when the user clicks on the "Add" button.
     *
     * @param view The view clicked (the button).
     */
    void onAddButtonClick(View view) {
        /* Code to be executed when the user clicks on the 'add' button.
           To be taken to a screen to add a post.
         */
        Intent intent = new Intent(this, QueryPostActivity.class);
        startActivity(intent);
        Log.d("AddButton", "Click");
    }
}
