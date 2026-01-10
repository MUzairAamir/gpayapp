package com.example.gpayapp;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import android.content.Intent;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Wait 3 seconds → Go to Signup screen
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, SignupActivity.class));
            finish();
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseAuth auth = FirebaseAuth.getInstance();

            if (auth.getCurrentUser() != null) {
                startActivity(new Intent(this, MainDrawerActivity.class));
            } else {
                startActivity(new Intent(this, LoginActivity.class));
            }
            finish();

            if (mAuth.getCurrentUser() != null) {
                startActivity(new Intent(this, MainDrawerActivity.class));
            } else {
                startActivity(new Intent(this, LoginActivity.class));
            }
            finish();
        }, 3000);


    }
}
