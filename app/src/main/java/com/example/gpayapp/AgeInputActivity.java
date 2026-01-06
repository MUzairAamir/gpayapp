package com.example.gpayapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AgeInputActivity extends AppCompatActivity {

    EditText etUserName;
    TextView tvDOB;
    Button btnSelectDOB, btnShowDetails;

    int birthYear, birthMonth, birthDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_input);

        etUserName = findViewById(R.id.etUserName);
        tvDOB = findViewById(R.id.tvDOB);

        btnSelectDOB = findViewById(R.id.btnSelectDOB);
        btnShowDetails = findViewById(R.id.btnShowDetails);

        btnSelectDOB.setOnClickListener(v -> openDatePicker());

        btnShowDetails.setOnClickListener(v -> showDetails());
    }

    private void openDatePicker() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                this,
                (view, y, m, d) -> {
                    birthYear = y;
                    birthMonth = m + 1;
                    birthDay = d;

                    tvDOB.setText("DOB: " + d + "/" + (m + 1) + "/" + y);
                },
                year, month, day);

        dialog.show();
    }

    private void showDetails() {
        String name = etUserName.getText().toString();

        if (name.isEmpty() || birthYear == 0) return;

        int age = calculateAge(birthYear, birthMonth, birthDay);

        Intent intent = new Intent(AgeInputActivity.this, ShowDetailsActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("age", age);

        startActivity(intent);
    }

    private int calculateAge(int y, int m, int d) {
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - y;

        if (today.get(Calendar.MONTH) + 1 < m ||
                (today.get(Calendar.MONTH) + 1 == m && today.get(Calendar.DAY_OF_MONTH) < d)) {
            age--;
        }

        return age;
    }
}
