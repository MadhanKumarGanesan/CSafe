package com.madcodes.CSafe.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class CSafePreferences {

    private static SharedPreferences sharedPreferences;

    private static final String MSISDN_NO = "msisdn_no";
    private static final String SUBSCRIBER_ID = "subcriber_id";
    private static final String IS_LOGIN = "is_login";
    private static final String SUBSCRIBER_NAME = "subcriber_name";
    private static final String EMAIL = "email";



    /**
     * SetUpDefaults
     *
     * @param context Context
     */
    public static void setUpDefaults(Context context) {
        sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
    }


    public static void setMsisdn(String value){
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(MSISDN_NO, value);
            editor.apply();
        }

    }

    public static String getMsisdn() {
        String msisdn_no = null;
        if (sharedPreferences != null) {
            msisdn_no = sharedPreferences.getString(MSISDN_NO, "");
        }
        return msisdn_no;
    }


    public static void setEmail(String value){
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(EMAIL, value);
            editor.apply();
        }

    }

    public static String getEmail() {
        String email = null;
        if (sharedPreferences != null) {
            email = sharedPreferences.getString(EMAIL, "");
        }
        return email;
    }





    public static void setUserId(String value){
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(SUBSCRIBER_ID, value);
            editor.apply();
        }

    }

    public static String getUserId() {
        String subscriber_id = null;
        if (sharedPreferences != null) {
            subscriber_id = sharedPreferences.getString(SUBSCRIBER_ID, "");
        }
        return subscriber_id;
    }


    public static void setUserName(String value){
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(SUBSCRIBER_NAME, value);
            editor.apply();
        }

    }

    public static String getUserName() {
        String subscriber_name = null;
        if (sharedPreferences != null) {
            subscriber_name = sharedPreferences.getString(SUBSCRIBER_NAME, "");
        }
        return subscriber_name;
    }



    public static void setIsLogin(Boolean value){
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(IS_LOGIN, value);
            editor.apply();
        }

    }

    public static boolean getIsLogin() {
        boolean is_broadcastValue = false;
        if (sharedPreferences != null) {
            // in case of no value setted in IS_BROADCAST  then the default value will be false
            is_broadcastValue = sharedPreferences.getBoolean(IS_LOGIN,false);
        }
        return is_broadcastValue;
    }



}
