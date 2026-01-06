package com.example.gpayapp;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedData {

    private static final String PREF_NAME = "MyBankApp";

    public static void saveBalance(Context context, int balance) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().putInt("balance", balance).apply();
    }

    public static int getBalance(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getInt("balance", 0);   // default balance = 0
    }

    public static void saveUser(Context context, String username, int age) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit()
                .putString("username", username)
                .putInt("age", age)
                .apply();
    }

    public static String getUsername(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getString("username", "User");
    }

    public static int getAge(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getInt("age", 18);
    }
}

