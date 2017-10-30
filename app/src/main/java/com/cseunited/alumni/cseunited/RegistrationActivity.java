package com.cseunited.alumni.cseunited;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

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

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    private Spinner role;
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
        textInputLayoutBatch = (TextInputLayout) findViewById(R.id.textInputLayoutBatch);
        textInputLayoutQual = (TextInputLayout) findViewById(R.id.textInputLayoutQual);
        textInputLayoutQual.setVisibility(View.GONE);

        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);
        textInputEditTextDOB = (TextInputEditText) findViewById(R.id.textInputEditTextDOB);

        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);

        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

        role = (Spinner) findViewById(R.id.spinnerRole);
        ArrayAdapter<CharSequence> roles = ArrayAdapter.createFromResource(this,
                R.array.role, android.R.layout.simple_spinner_item);
        roles.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role.setAdapter(roles);

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
        role.setOnItemSelectedListener(this);
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
                Toast.makeText(this, "Registered Successfully", Toast.LENGTH_LONG).show();
                finish();
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
        if(spinner.getId() == R.id.spinnerRole) {
            if (position == 1)
                textInputLayoutBatch.setVisibility(View.GONE);
            else
                textInputLayoutBatch.setVisibility(View.VISIBLE);
        }
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
}
