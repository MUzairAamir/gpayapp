package com.example.gpayapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMoneyActivity extends AppCompatActivity {

    EditText etAddAmount;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_money_activity);

        etAddAmount = findViewById(R.id.etAddAmount);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(v -> {

            String amountStr = etAddAmount.getText().toString();

            if (amountStr.isEmpty()) {
                Toast.makeText(this, "Please enter amount", Toast.LENGTH_SHORT).show();
                return;
            }

            int addAmount = Integer.parseInt(amountStr);
            int balance = SharedData.getBalance(this);

            SharedData.saveBalance(this, balance + addAmount);

            Toast.makeText(this, "Money Added!", Toast.LENGTH_SHORT).show();

            finish();
        });
    }
}
