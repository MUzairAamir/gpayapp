package com.example.gpayapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddMoneyActivity extends AppCompatActivity {

    EditText etAmount;
    Button btnAdd;

    FirebaseAuth auth;
    DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_money_activity);

        etAmount = findViewById(R.id.etAmount);
        btnAdd = findViewById(R.id.btnAdd);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() == null) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        userRef = FirebaseDatabase.getInstance()
                .getReference("users")
                .child(auth.getCurrentUser().getUid());

        btnAdd.setOnClickListener(v -> addMoney());
    }

    private void addMoney() {

        String amountStr = etAmount.getText().toString().trim();

        if (amountStr.isEmpty()) {
            Toast.makeText(this, "Enter amount", Toast.LENGTH_SHORT).show();
            return;
        }

        int amount = Integer.parseInt(amountStr);

        if (amount <= 0) {
            Toast.makeText(this, "Invalid amount", Toast.LENGTH_SHORT).show();
            return;
        }

        userRef.child("balance")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Integer currentBalance = snapshot.getValue(Integer.class);
                        if (currentBalance == null) currentBalance = 0;

                        int newBalance = currentBalance + amount;
                        userRef.child("balance").setValue(newBalance);

                        // Save transaction
                        userRef.child("transactions").push()
                                .setValue("Added PKR " + amount);

                        Toast.makeText(AddMoneyActivity.this,
                                "Money added successfully",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddMoneyActivity.this,
                                "Failed: " + error.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
