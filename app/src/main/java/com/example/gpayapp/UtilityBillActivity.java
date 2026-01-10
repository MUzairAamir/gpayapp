package com.example.gpayapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UtilityBillActivity extends AppCompatActivity {

    Spinner spinnerBill;
    EditText etPSID, etAmount;
    Button btnPay;

    FirebaseAuth auth;
    DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility_bill);

        spinnerBill = findViewById(R.id.spinnerType);
        etPSID = findViewById(R.id.etPsid);
        etAmount = findViewById(R.id.etAmount);
        btnPay = findViewById(R.id.btnPay);

        // Spinner data
        String[] bills = {"Electric", "Water", "Gas", "Internet"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                bills
        );
        spinnerBill.setAdapter(adapter);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() == null) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        userRef = FirebaseDatabase.getInstance()
                .getReference("users")
                .child(auth.getCurrentUser().getUid());

        btnPay.setOnClickListener(v -> payBill());
    }

    private void payBill() {

        String billType = spinnerBill.getSelectedItem().toString();
        String psid = etPSID.getText().toString().trim();
        String amountStr = etAmount.getText().toString().trim();

        if (psid.length() != 11 || amountStr.isEmpty()) {
            Toast.makeText(this, "Invalid PSID or amount", Toast.LENGTH_SHORT).show();
            return;
        }

        int amount = Integer.parseInt(amountStr);

        userRef.child("balance")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Integer balance = snapshot.getValue(Integer.class);
                        if (balance == null) balance = 0;

                        if (balance < amount) {
                            Toast.makeText(UtilityBillActivity.this,
                                    "Not enough balance",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        int newBalance = balance - amount;
                        userRef.child("balance").setValue(newBalance);

                        String txn = billType + " Bill Paid PKR " + amount +
                                " (PSID: " + psid + ")";

                        userRef.child("transactions").push().setValue(txn);

                        Toast.makeText(UtilityBillActivity.this,
                                "Bill Paid Successfully",
                                Toast.LENGTH_SHORT).show();

                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(UtilityBillActivity.this,
                                error.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
