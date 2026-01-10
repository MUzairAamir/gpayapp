package com.example.gpayapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // ❌ TURN OFF AUTO LOGIN
        FirebaseAuth.getInstance().signOut();

        new Handler().postDelayed(() -> {

            // ✅ ALWAYS GO TO LOGIN
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();

        }, 3000);
    }
}
