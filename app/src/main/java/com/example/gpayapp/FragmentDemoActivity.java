package com.example.gpayapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class FragmentDemoActivity extends AppCompatActivity {

    Button btnRed, btnGreen, btnBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_demo_activity);

        btnRed = findViewById(R.id.btnRed);
        btnGreen = findViewById(R.id.btnGreen);
        btnBlue = findViewById(R.id.btnBlue);

        btnRed.setOnClickListener(v -> loadFragment(new RedFragment()));
        btnGreen.setOnClickListener(v -> loadFragment(new GreenFragment()));
        btnBlue.setOnClickListener(v -> loadFragment(new BlueFragment()));
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}
