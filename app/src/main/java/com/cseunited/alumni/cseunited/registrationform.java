package com.cseunited.alumni.cseunited;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Sachin on 10/7/2017.
 */

public class registrationform extends AppCompatActivity implements View.OnClickListener {
    private EditText fullNameEditText;
    private EditText mobileEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText retypepapasswordEditText;
    private Button signupButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regform);
        fullNameEditText=(EditText)findViewById(R.id.name);
        mobileEditText=(EditText)findViewById(R.id.number);
        emailEditText=(EditText)findViewById(R.id.emailid);
        passwordEditText=(EditText)findViewById(R.id.regpassword);
        retypepapasswordEditText=(EditText)findViewById(R.id.retypepassword);
        signupButton=(Button) findViewById(R.id.signup);
        signupButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String p=passwordEditText.getText().toString();
        String rp=retypepapasswordEditText.getText().toString();
        if(p.equals(rp))
        {
            Toast.makeText(this, "successfully registered",Toast.LENGTH_LONG).show();
            startActivity(MainActivity.class);
        }
        else
        {
            retypepapasswordEditText.setError("Please check the password..");
            //return;
        }

    }
    private void startActivity(Class<?> targetActivity) {
        Intent intent=new Intent(this, targetActivity);
        startActivity(intent);
    }
}
