package com.cseunited.alumni.cseunited;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by suyash on 12/23/2017.
 */

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "usersharedpref";
    private static final String KEY_LOGGEDIN = "keyloggedin";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_PASSWORD = "keypassword";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void userLogin(String email, String pass) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_LOGGEDIN, true);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, pass);
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(KEY_LOGGEDIN, false);
    }
}
