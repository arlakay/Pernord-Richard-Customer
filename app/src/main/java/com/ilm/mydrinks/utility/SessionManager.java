package com.ilm.mydrinks.utility;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.ilm.mydrinks.ui.welcome.WelcomeActivity;

import java.util.HashMap;

/**
 * Created by E.R.D on 4/2/2016.
 */
public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "MyDrinks Customer";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    public static final String KEY_CUSTOMER_ID = "customer_id";
    public static final String KEY_CUSTOMER_CODE = "username";
    public static final String KEY_FIRST_NAME = "first_name";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_BIRTH_DATE = "birth_date";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_POINT_BALANCE = "point_balance";
    public static final String KEY_LAST_UPDATE_POINT = "last_update_point";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String customerId, String customerCode_username, String firstName,
                                   String lastName, String birthdate, String email, String phone,
                                   String point_balance, String last_update_point){
        editor.putBoolean(KEY_IS_LOGGEDIN, true);

        editor.putString(KEY_CUSTOMER_ID, customerId);
        editor.putString(KEY_CUSTOMER_CODE, customerCode_username);
        editor.putString(KEY_FIRST_NAME, firstName);
        editor.putString(KEY_LAST_NAME, lastName);
        editor.putString(KEY_BIRTH_DATE, birthdate);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PHONE, phone);
        editor.putString(KEY_POINT_BALANCE, point_balance);
        editor.putString(KEY_LAST_UPDATE_POINT, last_update_point);

        editor.commit();
    }

//    public void createBarcodeSession(String barcode){
//        editor.putBoolean(KEY_IS_LOGGEDIN, true);
//
//        editor.putString(KEY_BARCODE, barcode);
//
//        editor.commit();
//    }

    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent i = new Intent(_context, WelcomeActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.commit();
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_CUSTOMER_ID, pref.getString(KEY_CUSTOMER_ID, null));
        user.put(KEY_CUSTOMER_CODE, pref.getString(KEY_CUSTOMER_CODE, null));
        user.put(KEY_FIRST_NAME, pref.getString(KEY_FIRST_NAME, null));
        user.put(KEY_LAST_NAME, pref.getString(KEY_LAST_NAME, null));
        user.put(KEY_BIRTH_DATE, pref.getString(KEY_BIRTH_DATE, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_PHONE, pref.getString(KEY_PHONE, null));
        user.put(KEY_POINT_BALANCE, pref.getString(KEY_POINT_BALANCE, null));
        user.put(KEY_LAST_UPDATE_POINT, pref.getString(KEY_LAST_UPDATE_POINT, null));

        return user;
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, WelcomeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        _context.startActivity(i);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

}
