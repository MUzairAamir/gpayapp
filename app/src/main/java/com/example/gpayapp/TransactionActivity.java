package com.example.gpayapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TransactionActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> transactionList;
    ArrayAdapter<String> adapter;

    FirebaseAuth auth;
    DatabaseReference transactionRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_activity);
        listView = findViewById(R.id.listTransactions);
        transactionList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                transactionList);
        listView.setAdapter(adapter);
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() == null) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        transactionRef = FirebaseDatabase.getInstance()
                .getReference("users")
                .child(auth.getCurrentUser().getUid())
                .child("transactions");

        loadTransactions();
    }

    private void loadTransactions() {

        transactionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                transactionList.clear();

                if (!snapshot.exists()) {
                    transactionList.add("No transactions yet");
                } else {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String txn = ds.getValue(String.class);
                        transactionList.add(txn);
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TransactionActivity.this,
                        error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
