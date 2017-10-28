package com.cseunited.alumni.cseunited;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Home extends BaseActivity {
    TextView textView,textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Intent to receive the username from the login screen
        /* Paste below lines in the login activity
        Intent intent = new Intent(this, Home.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);*/


        /*Intent intent = getIntent();
          String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);*/

        textView = (TextView) findViewById(R.id.usr);
        textView.setText("Hello Username");

        // Code needs to be replaced to pick the upcoming events from the server

        textView1 = (TextView) findViewById(R.id.event);
        textView1.setText("Info on the upcoming event is going to be placed over here");

        //Code to be placed to pick user image from the server


    }





}
