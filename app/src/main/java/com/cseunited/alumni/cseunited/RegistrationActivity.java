package com.cseunited.alumni.cseunited;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class RegistrationActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;
    private TextInputLayout textInputLayoutBatch;
    private TextInputLayout textInputLayoutQual;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;
    private TextInputEditText textInputEditTextDOB;
    private TextInputEditText textInputEditTextBatch;
    private TextInputEditText employer;
    private TextInputEditText position;

    private CheckBox terms;
    private RadioGroup radioGroupGender;
    private TextInputEditText phoneNumber;
    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    //private Spinner role;
    private Spinner qualification;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inflating the layout with the drawer layout
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_registration, null, false);
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

    /**
     * This method is to initialize views
     */
    private void initViews() {
        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);

        textInputLayoutQual = (TextInputLayout) findViewById(R.id.textInputLayoutQual);
        textInputLayoutQual.setVisibility(View.GONE);

        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);
        textInputEditTextDOB = (TextInputEditText) findViewById(R.id.textInputEditTextDOB);
        employer = (TextInputEditText) findViewById(R.id.institution);
        position = (TextInputEditText) findViewById(R.id.position);

        radioGroupGender = (RadioGroup)findViewById(R.id.gender);
        phoneNumber = (TextInputEditText) findViewById(R.id.phoneNumber);
        textInputEditTextBatch = (TextInputEditText) findViewById(R.id.textInputEditTextBatch);
        terms = (CheckBox) findViewById(R.id.terms);
        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);

        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

        /*role = (Spinner) findViewById(R.id.spinnerRole);
        ArrayAdapter<CharSequence> roles = ArrayAdapter.createFromResource(this,
                R.array.role, android.R.layout.simple_spinner_item);
        roles.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role.setAdapter(roles);*/

        qualification = (Spinner) findViewById(R.id.spinnerQual);
        ArrayAdapter<CharSequence> qualifications = ArrayAdapter.createFromResource(this,
                R.array.qualification, android.R.layout.simple_spinner_item);
        qualifications.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qualification.setAdapter(qualifications);
    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);
        //role.setOnItemSelectedListener(this);
        qualification.setOnItemSelectedListener(this);
        textInputEditTextDOB.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {

    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param view view pressed in the layout
     *
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.appCompatButtonRegister:
                // Verification and Web Request
                try {
                    uploadToServer();
                }catch (JSONException e){
                    Toast.makeText(getApplicationContext(), LoginActivity.errorUnknown, Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.appCompatTextViewLoginLink:
                finish();
                break;

            case R.id.textInputEditTextDOB:
                Calendar currentDate = Calendar.getInstance();
                int year = currentDate.get(Calendar.YEAR);
                int month = currentDate.get(Calendar.MONTH);
                int date = currentDate.get(Calendar.DATE);
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegistrationActivity.this, new DatePickerDialog.OnDateSetListener(){
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDate){
                        String dob = getString(R.string.dob_display, selectedDate, selectedMonth, selectedYear);
                        textInputEditTextDOB.setText(dob);
                    }
                }, year, month, date);
                datePickerDialog.show();
                break;
        }
    }

    /**
     *
     * @param parent the Spinner on which the method is called
     * @param view the view
     * @param position position of the item selected
     * @param id id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        /*if(spinner.getId() == R.id.spinnerRole) {
            if (position == 1)
                textInputLayoutBatch.setVisibility(View.GONE);
            else
                textInputLayoutBatch.setVisibility(View.VISIBLE);
        }*/
        if(spinner.getId() == R.id.spinnerQual){
            if (position == 3)
                textInputLayoutQual.setVisibility(View.VISIBLE);
            else
                textInputLayoutQual.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private void uploadToServer()throws JSONException{
        final String registerUrl = "http:// alumni.cseunited.com/users/register";
        final ProgressDialog progressDialog = new ProgressDialog(this);
        final String successMessage = "You have been successfully registered!";
        final String errorFailure = "Couldn't register you. Please check your details carefully.";

        progressDialog.setCancelable(false);
        progressDialog.setMessage("Registering you...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        int terms = (this.terms.isChecked())?1:0;
        String gender;
        if(radioGroupGender.getCheckedRadioButtonId()!=-1)
            gender = ((RadioButton)radioGroupGender.findViewById(radioGroupGender.getCheckedRadioButtonId())).getText().toString();
        else
            gender = "";
        JSONObject toPost = new JSONObject()
                .put("email", textInputEditTextEmail.getText())
                .put("password", textInputEditTextConfirmPassword.getText())
                .put("name", textInputEditTextName.getText())
                .put("batch", textInputEditTextBatch.getText())
                .put("gender", gender)
                .put("mob", phoneNumber.getText())
                .put("qual", qualification.getSelectedItem().toString())
                .put("employer", employer.getText())
                .put("position", position.getText())
                .put("dob", textInputEditTextDOB.getText())
                .put("terms", terms);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, registerUrl, toPost,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getInt(LoginActivity.statusTag) == 1) {
                                Toast.makeText(getApplicationContext(), successMessage, Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), errorFailure, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressDialog.cancel();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getApplicationContext(), LoginActivity.errorUnknown, Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                    }
                });
        request.setTag(this);
        VolleyChannel.getInstance(this).getRequestQueue().add(request);
        Log.d("JSON", toPost.toString());
    }

}
