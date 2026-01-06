package com.example.gpayapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {

            String email = etEmail.getText().toString();
            String pass = etPassword.getText().toString();

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Please enter both fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Dummy check (no database)
            if (email.equals("admin@gmail.com") && pass.equals("12345")) {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, ProductActivity.class));
            } else {
                Toast.makeText(this, "Invalid login details", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
