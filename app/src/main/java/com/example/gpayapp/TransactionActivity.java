package com.example.gpayapp;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TransactionActivity extends AppCompatActivity {

    EditText etReceiverName, etReceiverAcc, etAmount;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_activity);

        etReceiverName = findViewById(R.id.etReceiverName);
        etReceiverAcc = findViewById(R.id.etReceiverAcc);
        etAmount = findViewById(R.id.etAmount);
        btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(v -> {

            String name = etReceiverName.getText().toString();
            String acc = etReceiverAcc.getText().toString();
            String amtStr = etAmount.getText().toString();

            if (name.isEmpty() || acc.isEmpty() || amtStr.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int sendAmount = Integer.parseInt(amtStr);
            int balance = SharedData.getBalance(this);

            if (sendAmount > balance) {
                Toast.makeText(this, "Not enough balance!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Deduct balance
            SharedData.saveBalance(this, balance - sendAmount);

            Toast.makeText(this,
                    "Sent PKR " + sendAmount + " to " + name,
                    Toast.LENGTH_LONG).show();

            finish();
        });
    }
}
