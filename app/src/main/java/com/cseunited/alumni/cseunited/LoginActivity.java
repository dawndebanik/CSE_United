package com.cseunited.alumni.cseunited;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

/**
 * Created by suyash on 10/27/2017
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText emailView;
    private TextInputEditText passwordView;
    private AppCompatButton loginBtn;
    private AppCompatTextView registerText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
            //Do the web thing.
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
