package com.cseunited.alumni.cseunited;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends BaseActivity {
    TextView textView,textView1;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] images= {R.drawable.mkn_sir,R.drawable.nc_mam,R.drawable.pd_mam,R.drawable.tc_sir,R.drawable.ud_sir};
    private ArrayList<Integer> IMAGEArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        // Intent to receive the username from the login screen
        /* Paste below lines in the login activity
        Intent intent = new Intent(this, HomeActivity.class);
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

    private void init() {
        for(int i=0;i<images.length;i++)
            IMAGEArray.add(images[i]);

        mPager = (ViewPager) findViewById(R.id.viewPager);
        mPager.setAdapter(new MyPagerAdapter(HomeActivity.this,IMAGEArray));
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == images.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }

}





