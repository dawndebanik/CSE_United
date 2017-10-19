package com.cseunited.alumni.cseunited;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DiscussActivity extends AppCompatActivity implements DiscussAdapter.ItemClickListener {
    RecyclerView discussionView;
    FloatingActionButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discuss_main);
        discussionView = (RecyclerView) findViewById(R.id.recycler);
        DiscussAdapter adapter = new DiscussAdapter(getApplicationContext(), fetchListOfPosts());
        adapter.setClickListener(this);
        discussionView.setAdapter(adapter);
        discussionView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        addButton = (FloatingActionButton) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddButtonClick(view);
            }
        });
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
        /*  Code to be added to redirect to a new page showing the selected post on the forum.
            Raj will create the required page.
         */
        Log.d("Item", "Click");
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
        Log.d("AddButton", "Click");
    }
}
