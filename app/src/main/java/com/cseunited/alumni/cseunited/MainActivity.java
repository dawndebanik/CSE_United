package com.cseunited.alumni.cseunited;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import helpers.InputValidation;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private final AppCompatActivity activity = MainActivity.this;
    private NestedScrollView nestedScrollView;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private AppCompatButton appCompatButtonLogin;
    private AppCompatTextView textViewLinkRegister;
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().setTitle("");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
        initListeners();
        initObjects();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);

        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);

    }
    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_theme) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            // Handle the camera action
        } else if (id == R.id.events) {

        } else if (id == R.id.gallery) {

        } else if (id == R.id.cse) {

        } else if (id == R.id.about) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonLogin:
                verifyFromSQLite();
                break;
            case R.id.textViewLinkRegister:
                // Navigate to registrationform
                Intent intentRegister = new Intent(getApplicationContext(), registrationform.class);
                startActivity(intentRegister);
                break;
        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
            return;
        }

        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {


            Intent accountsIntent = new Intent(activity, UsersListActivity.class);
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);

        } else {
            // Snack Bar to show success message that record is wrong
            //Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
            Toast.makeText(MainActivity.this,
                    "INVALID EMAIL OR PASSWORD", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
    private void startActivity(Class<?> targetActivity) {
        Intent intent=new Intent(this, targetActivity);
        startActivity(intent);
    }
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("CSE United 2015");
        listDataHeader.add("CSE United and Alumni Meet 2014");
        listDataHeader.add("CSE Alumni Meet");
        listDataHeader.add("CSE United 2012");
        listDataHeader.add("CSE United 2011");

        // Adding child data
        List<String> u15 = new ArrayList<String>();
        u15.add("In the 5th year, CSE UNITED soared to an entirely new height. The event took place at the EZCC (Eastern Zonal Cultural Centre) Hall. For the first time, in five years, the event took place outside the college premises. Following our traditions, the hall was decorated, keeping in mind the significance of the celebration of the 5th year. Starting from the inauguration to the end, all the events had a cultural as well as a western touch to it. In addition, the Mr. and Miss CSE event was introduced this year. ");

        List<String> u14 = new ArrayList<String>();
        u14.add("CSE United is our way of welcoming the first years to our family. What better than combine it with an alumni meet as, after all, in the words of our beloved CKB Sir, an institution is not known by its faculty but its alumni.");

        List<String> u13 = new ArrayList<String>();
        u13.add("This was the first alumni meet organised by the CSE Department of Techno India. The meet proved to be a success, and we had quite a few alumni members joining us to guide and inspire the first years. A Q&A session on various aspects of corporate life and academics was followed by fun events organised by the alumni members themselves. Students of the department also presented their take on the alumni website and the group led by Soumajit Biswas was declared the winner. The event came to an end with a light-hearted game of tug-of-war, with students of the first, second, third and fourth years as well as one consisting of members of the Alumni competing for the title. ");

        List<String> u12 = new ArrayList<String>();
        u12.add("By 2012, CSE United had become a day everyone looks forward to. The second ever CSE Unted was held in late 2012 and consisted of the usual fare – songs, dances, games, etc. The events of the day included:"+"\n"+
                "Dance Performances by Russa and Smita, Deepika, Prema, Saurav Kothari"+"\n"+
                "A group song by Jyotirmoy and his group"+"\n"+
                "A recitation by Sayan, a fresher at the time"+"\n"+
                "A recitation by Ritesh"+"\n"+
                "A Song by Joydeep sir"+"\n"+
                "A group song by Meghdut and his Group"+"\n"+
                "Songs by CKB sir and Poulami ma'am");

        List<String> u11 = new ArrayList<String>();
        u11.add(" The first ever CSE United was deviced as a cultural program to welcome freshers to the Computer Science and Engineering Department. The events of the day included:"+"\n"+
                "A recitation by Ritesh"+"\n"+
                "A song by Souvik and Subham"+"\n"+
                "A group song by Meghdut"+"\n"+
                "Dance performances by Saurav Kothari, Kavita and Deepika"+"\n"+
                "A game of musical chair by the teachers"+"\n"+
                "Interaction with first years"+"\n"+
                "Songs by CKB sir and Poulami ma'am");

        listDataChild.put(listDataHeader.get(0), u15); // Header, Child data
        listDataChild.put(listDataHeader.get(1), u14);
        listDataChild.put(listDataHeader.get(2), u13);
        listDataChild.put(listDataHeader.get(3), u12);
        listDataChild.put(listDataHeader.get(4), u11);
    }
}

