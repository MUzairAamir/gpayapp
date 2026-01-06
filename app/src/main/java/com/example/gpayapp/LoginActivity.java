package com.example.gpayapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gpayapp.MainDrawerActivity;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(
                            etEmail.getText().toString(),
                            etPassword.getText().toString()
                    )
                    .addOnSuccessListener(authResult -> {
                        startActivity(new Intent(this, MainDrawerActivity.class));
                        finish();
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show());
        });
    }
}
