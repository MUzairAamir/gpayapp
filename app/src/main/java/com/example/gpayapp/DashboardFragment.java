package com.example.gpayapp;

import static com.example.gpayapp.R.id.btnChat;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashboardFragment extends Fragment {

    TextView tvBalance;
    Button btnAddMoney, btnSendMoney, btnTransactions,
            btnUtility, btnQRScan, btnChatbot,btnChat;
    AdView adView;

    FirebaseAuth auth;
    DatabaseReference balanceRef;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dashboard_fragment, container, false);

        // UI binding
        tvBalance = view.findViewById(R.id.tvBalance);
        btnAddMoney = view.findViewById(R.id.btnAddMoney);
        btnSendMoney = view.findViewById(R.id.btnSendMoney);
        btnTransactions = view.findViewById(R.id.btnTransactions);
        btnUtility = view.findViewById(R.id.btnUtility);
        btnChat = view.findViewById(R.id.btnChat);
        btnQRScan = view.findViewById(R.id.btnQRScan);
        btnChatbot = view.findViewById(R.id.btnChatbot);
        adView = view.findViewById(R.id.adView);


        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        // Firebase
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            balanceRef = FirebaseDatabase.getInstance()
                    .getReference("users")
                    .child(auth.getCurrentUser().getUid())
                    .child("balance");

            // Real-time balance listener
            balanceRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Integer balance = snapshot.getValue(Integer.class);
                    if (balance != null) {
                        tvBalance.setText("Current Balance: PKR " + balance);
                    } else {
                        tvBalance.setText("Current Balance: PKR 0");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    tvBalance.setText("Balance unavailable");
                }
            });
        }

        // Button clicks
        btnAddMoney.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), AddMoneyActivity.class))
        );

        btnSendMoney.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), SendMoneyActivity.class))
        );

        btnTransactions.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), TransactionActivity.class))
        );

        btnUtility.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), UtilityBillActivity.class))
        );

        btnQRScan.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), QRScanActivity.class))
        );

        // 🤖 AI Chatbot
        btnChatbot.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), ChatbotActivity.class))
        );
        btnChat.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ChatActivity.class);
            intent.putExtra("receiverId", "SUPPORT");
            startActivity(intent);
        });


        return view;
    }
}
