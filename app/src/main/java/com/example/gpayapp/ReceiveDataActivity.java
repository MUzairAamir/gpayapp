package com.example.gpayapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ReceiveDataActivity extends AppCompatActivity {

    TextView tvUserName, tvUserCity;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_data);

        tvUserName = findViewById(R.id.tvUserName);
        tvUserCity = findViewById(R.id.tvUserCity);
        btnBack = findViewById(R.id.btnBack);

        // Read data from Intent (keys used in SendDataActivity: "username", "usercity")
        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        String city = intent.getStringExtra("usercity");

        if (name == null || name.isEmpty()) name = "-";
        if (city == null || city.isEmpty()) city = "-";

        tvUserName.setText("Name: " + name);
        tvUserCity.setText("City: " + city);

        btnBack.setOnClickListener(v -> finish());
    }
}
