package com.example.gpayapp;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class DashboardFragment extends Fragment {

    TextView tvBalance;
    Button btnAddMoney, btnSendMoney, btnTransactions;

    public DashboardFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dashboard_fragment, container, false);

        tvBalance = view.findViewById(R.id.tvBalance);
        btnAddMoney = view.findViewById(R.id.btnAddMoney);
        btnSendMoney = view.findViewById(R.id.btnSendMoney);
        btnTransactions = view.findViewById(R.id.btnTransactions);

        // Load saved balance
        int balance = SharedData.getBalance(requireContext());
        tvBalance.setText("Current Balance: PKR " + balance);

        btnAddMoney.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), AddMoneyActivity.class));
        });

        btnSendMoney.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), SendMoneyActivity.class));
        });

        btnTransactions.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), TransactionActivity.class));
        });

        return view;
    }
}
