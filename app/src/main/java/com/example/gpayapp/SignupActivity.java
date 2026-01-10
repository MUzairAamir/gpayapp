package com.example.gpayapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnCreateAccount;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // ✅ Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // ✅ Bind views
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        // ✅ Button click
        btnCreateAccount.setOnClickListener(v -> {
            createAccount();
        });
    }

    private void createAccount() {

        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // ✅ Validation
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        // ✅ Firebase signup
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        Toast.makeText(this,
                                "Account created successfully",
                                Toast.LENGTH_SHORT).show();

                        // Go to Login screen
                        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                        finish();

                    } else {
                        Toast.makeText(this,
                                "Signup Failed: " + task.getException().getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
}
