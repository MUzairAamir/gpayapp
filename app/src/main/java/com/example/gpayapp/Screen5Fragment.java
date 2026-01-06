package com.example.gpayapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Screen5Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_screen5, container, false);

        Button btnSend = view.findViewById(R.id.btnSend);
        Button btnRequest = view.findViewById(R.id.btnRequest);

        btnSend.setOnClickListener(v ->
                Toast.makeText(getActivity(), "Send Money Clicked", Toast.LENGTH_SHORT).show()
        );

        btnRequest.setOnClickListener(v ->
                Toast.makeText(getActivity(), "Request Money Clicked", Toast.LENGTH_SHORT).show()
        );

        return view;
    }
}
