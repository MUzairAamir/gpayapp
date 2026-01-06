package com.example.gpayapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Students.db";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE students (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "roll TEXT UNIQUE," +
                        "name TEXT," +
                        "email TEXT," +
                        "password TEXT," +
                        "age INTEGER)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS students");
        onCreate(db);
    }

    // INSERT
    public boolean insertStudent(String roll, String name, String email, String password, int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("roll", roll);
        cv.put("name", name);
        cv.put("email", email);
        cv.put("password", password);
        cv.put("age", age);

        long result = db.insert("students", null, cv);
        return result != -1;
    }

    // VIEW
    public Cursor getAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT id, roll, name FROM students", null);
    }

    // UPDATE
    public boolean updateStudent(String roll, String name, int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("age", age);

        int result = db.update("students", cv, "roll=?", new String[]{roll});
        return result > 0;
    }

    // DELETE
    public boolean deleteStudent(String roll) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("students", "roll=?", new String[]{roll});
        return result > 0;
    }
}
