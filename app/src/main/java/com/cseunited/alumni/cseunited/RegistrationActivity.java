package com.cseunited.alumni.cseunited;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import helpers.InputValidation;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private final AppCompatActivity activity = RegistrationActivity.this;

    private NestedScrollView nestedScrollView;

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

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private UserDetails user;

    private Spinner role;
    private Spinner qualification;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regform);

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

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
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new UserDetails();

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

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {

            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());

            databaseHelper.addUser(user);

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();
            startActivity(MainActivity.class);


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }

    private void startActivity(Class<?> targetActivity) {
        Intent intent=new Intent(this, targetActivity);
        startActivity(intent);
    }
}
