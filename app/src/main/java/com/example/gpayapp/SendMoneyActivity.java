package com.example.gpayapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendMoneyActivity extends AppCompatActivity {

    EditText etSendAmount;
    Button btnSendSimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_money_activity);

        etSendAmount = findViewById(R.id.etSendAmount);
        btnSendSimple = findViewById(R.id.btnSendSimple);

        btnSendSimple.setOnClickListener(v -> {

            String amountStr = etSendAmount.getText().toString();

            if (amountStr.isEmpty()) {
                Toast.makeText(this, "Enter amount", Toast.LENGTH_SHORT).show();
                return;
            }

            int amount = Integer.parseInt(amountStr);
            int balance = SharedData.getBalance(this);

            if (amount > balance) {
                Toast.makeText(this, "Insufficient balance!", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedData.saveBalance(this, balance - amount);

            Toast.makeText(this, "Money Sent Successfully!", Toast.LENGTH_SHORT).show();

            finish();
        });
    }
}
