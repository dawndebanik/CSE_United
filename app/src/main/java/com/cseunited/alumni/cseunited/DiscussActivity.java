package com.cseunited.alumni.cseunited;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
        Intent intent = new Intent(this, QuestionDetail.class);
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
        Log.d("AddButton", "Click");
    }
}
