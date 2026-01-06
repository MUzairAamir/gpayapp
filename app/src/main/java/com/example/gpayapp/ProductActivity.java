package com.example.gpayapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ProductActivity extends AppCompatActivity {

    ListView listView;

    String[] services = {
            "Add Money to Wallet",
            "Send Money",
            "Pay Bills",
            "Bank Transfer",
            "Mobile Recharge",
            "Electricity Bill",
            "Internet Bill",
            "Transaction History",
            "Account Details",
            "Help & Support"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        listView = findViewById(R.id.listServices);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                services
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {

            String selected = services[position];

            Toast.makeText(this, "Selected: " + selected, Toast.LENGTH_SHORT).show();

            // Example action — you can assign different screens
            if (selected.equals("Add Money to Wallet")) {
                startActivity(new Intent(ProductActivity.this, SendDataActivity.class));
            }
            else if (selected.equals("Account Details")) {
                startActivity(new Intent(ProductActivity.this, ReceiveDataActivity.class));
            }
        });
    }
}
