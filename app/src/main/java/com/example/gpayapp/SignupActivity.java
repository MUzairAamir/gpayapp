package com.example.gpayapp;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnSignup;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignup = findViewById(R.id.btnSignup);

        auth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(v -> {
            String email = etEmail.getText().toString();
            String pass = etPassword.getText().toString();

            auth.createUserWithEmailAndPassword(email, pass)
                    .addOnSuccessListener(result -> {
                        String uid = auth.getCurrentUser().getUid();

                        FirebaseDatabase.getInstance().getReference("users")

                                .child(uid)
                                .setValue(new com.example.gpayapp.User(email, 0));

                        Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show());
        });
    }
}
