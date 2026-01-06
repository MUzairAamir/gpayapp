package com.example.gpayapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class SendDataActivity extends AppCompatActivity {

    EditText etName, etCity;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data);

        etName = findViewById(R.id.etName);
        etCity = findViewById(R.id.etCity);
        btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(v -> {

            String name = etName.getText().toString();
            String city = etCity.getText().toString();

            Intent intent = new Intent(SendDataActivity.this, ReceiveDataActivity.class);
            intent.putExtra("username", name);
            intent.putExtra("usercity", city);

            startActivity(intent);
        });
    }
}
