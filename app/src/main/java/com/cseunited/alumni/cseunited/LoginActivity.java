package com.cseunited.alumni.cseunited;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Created by Suyash on 10/27/2017
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private TextInputEditText emailView;
    private TextInputEditText passwordView;
    private AppCompatButton loginBtn;
    private AppCompatTextView registerText;
    private static final String urlLogin = "http://suyashmittal.000webhostapp.com/csealumni/users/login";
    static final String statusTag = "statusCode";
    static final String errorUnknown = "Oops! An error was encountered.";
    static final String errorInvalidDetails = "Failed to connect to server, please try after some time.";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()) {
            finish();
            startActivity(new Intent(LoginActivity.this, DiscussActivity.class));
        }

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
            //validate();
            String email = emailView.getText().toString();
            if(!isValidEmail(email)){
                emailView.setError("Invalid Email");
            }
            String password = passwordView.getText().toString();
//            if (!isValidPassword(password)) {
//                passwordView.setError("Invalid Password");
//            }
            //Do the web thing
            try{
                authenticate(email, password);
            }catch (JSONException e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), errorInvalidDetails, Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Intent intent = new Intent(this, RegistrationActivity.class);
            startActivity(intent);
        }
    }

    // Method to authenticate the credentials
    private void authenticate(String email, String password)throws JSONException{
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Logging you in...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        JSONObject toPost = new JSONObject().put("email", email).put("password", password);
        Log.e("Data", toPost.toString());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, urlLogin, toPost,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("JSON reply", response.toString());
                        try {
                            if(response.getInt(statusTag)==1){
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(emailView.getText().toString(), passwordView.getText().toString());
                                startActivity(new Intent(LoginActivity.this, DiscussActivity.class));
                            }
                            else{
                                Toast.makeText(getApplicationContext(), errorInvalidDetails , Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), errorUnknown, Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.cancel();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), errorUnknown, Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                    }
                });
        request.setTag(this);
        VolleyChannel channel = VolleyChannel.getInstance(this);
        channel.getRequestQueue().add(request);
    }

    // Validation functions
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String pass) {
        return pass != null && pass.length() > 6;
    }
}
