package com.example.gpayapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    EditText etName, etEmail, etPass, etPass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etName  = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPass  = findViewById(R.id.etPass);
        etPass2 = findViewById(R.id.etPass2);

        Button btn = findViewById(R.id.btnSignup);

        btn.setOnClickListener(v -> {

            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String pass = etPass.getText().toString();
            String pass2 = etPass2.getText().toString();

            // Validation
            if (name.isEmpty() || email.isEmpty() || pass.isEmpty() || pass2.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!pass.equals(pass2)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Move to login screen
            Intent i = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(i);
        });
    }
}
