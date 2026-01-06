package com.example.gpayapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ShowDetailsActivity extends AppCompatActivity {

    TextView tvFinalDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        tvFinalDetails = findViewById(R.id.tvFinalDetails);

        String name = getIntent().getStringExtra("name");
        int age = getIntent().getIntExtra("age", 0);

        tvFinalDetails.setText("Name: " + name + "\nAge: " + age);
    }
}
