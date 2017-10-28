package com.cseunited.alumni.cseunited;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Suyash on 10/27/2017
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private TextInputEditText emailView;
    private TextInputEditText passwordView;
    private AppCompatButton loginBtn;
    private AppCompatTextView registerText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inflating the layout with the drawer layout
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_login, null, false);
        mDrawer.addView(contentView, 0);

        //Setting up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Setting up hamburger icon
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews(){
        emailView = (TextInputEditText) findViewById(R.id.login_email);
        passwordView = (TextInputEditText) findViewById(R.id.login_pass);
        loginBtn = (AppCompatButton) findViewById(R.id.login_btn);
        registerText = (AppCompatTextView) findViewById(R.id.to_register);
    }

    private void initListeners(){
        loginBtn.setOnClickListener(this);
        registerText.setOnClickListener(this);
    }

    private void initObjects(){

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.login_btn){
            validate();
            String email = emailView.getText().toString();
            String password = passwordView.getText().toString();
            password = new HashMaker("SHA-256").getHash(password);
            //Do the web thing
        }
        else{
            Intent intent = new Intent(this, RegistrationActivity.class);
            startActivity(intent);
        }
    }

    /**
     * Method used to validate the inputs.
     */
    private void validate(){

    }
}
