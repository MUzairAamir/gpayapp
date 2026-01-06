package com.example.gpayapp;


import android.os.Bundle;
import android.app.AlertDialog;
import android.database.Cursor;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    DBHelper db;
    Button insert, view, update, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);

        insert = findViewById(R.id.btnInsert);
        view = findViewById(R.id.btnView);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);

        insert.setOnClickListener(v -> {
            boolean res = db.insertStudent(
                    "L22FBSCS0110",
                    "Ali",
                    "ali@gmail.com",
                    "1234",
                    21
            );
            showMessage("Insert", res ? "Student record is saved" : "Insert failed");
        });

        view.setOnClickListener(v -> {
            ViewFragment fragment = new ViewFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentContainer, fragment);
            ft.commit();
        });

        update.setOnClickListener(v -> {
            boolean res = db.updateStudent("L22FBSCS0110", "Ali Updated", 22);
            showMessage("Update", res ? "Student Updated" : "Student not found");
        });

        delete.setOnClickListener(v -> {
            boolean res = db.deleteStudent("L22FBSCS0110");
            showMessage("Delete", res ? "User is deleted" : "Student not found");
        });
    }

    void showMessage(String title, String msg) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(msg)
                .setCancelable(true)
                .show();
    }
}
