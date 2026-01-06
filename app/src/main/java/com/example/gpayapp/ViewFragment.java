package com.example.gpayapp;


import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ViewFragment extends Fragment {

    DBHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view, container, false);
        TextView tv = view.findViewById(R.id.txtStudents);

        db = new DBHelper(getActivity());
        Cursor c = db.getAllStudents();

        StringBuilder sb = new StringBuilder();
        while (c.moveToNext()) {
            sb.append("ID: ").append(c.getString(0)).append("\n");
            sb.append("Roll: ").append(c.getString(1)).append("\n");
            sb.append("Name: ").append(c.getString(2)).append("\n\n");
        }

        tv.setText(sb.toString());
        return view;
    }
}
