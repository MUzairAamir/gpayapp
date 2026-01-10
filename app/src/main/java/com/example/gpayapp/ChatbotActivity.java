package com.example.gpayapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChatbotActivity extends AppCompatActivity {

    EditText etMessage;
    Button btnSend;
    TextView tvChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);
        tvChat = findViewById(R.id.tvChat);

        showMenu();
        etMessage.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_DONE) {
                btnSend.performClick();
                return true;
            }
            return false;
        });
        btnSend.setOnClickListener(v -> {
            String userInput = etMessage.getText().toString().trim();
            etMessage.setText("");

            if (userInput.isEmpty()) return;

            tvChat.append("\n\nYou: " + userInput);
            handleUserInput(userInput);
        });
    }

    private void showMenu() {
        tvChat.append("\n\n🤖 GPay Assistant Menu:\n" +
                "1. How to Add Money\n" +
                "2. How to Send Money\n" +
                "3. How to Pay Utility Bills\n" +
                "4. Transaction Issues\n" +
                "5. Contact Support\n\n" +
                "👉 Please enter option number (1–5)");
    }

    private void handleUserInput(String input) {

        switch (input) {
            case "1":
                tvChat.append("\n\nBot: To add money, go to Dashboard → Add Money → Enter amount → Confirm.");
                showMenu();
                break;

            case "2":
                tvChat.append("\n\nBot: To send money, go to Dashboard → Send Money → Enter receiver details → Send.");
                showMenu();
                break;

            case "3":
                tvChat.append("\n\nBot: To pay bills, open Utility Bills → Select bill type → Enter PSID → Pay.");
                showMenu();
                break;

            case "4":
                tvChat.append("\n\nBot: If transaction fails, check balance, internet connection, or try again later.");
                showMenu();
                break;

            case "5":
                tvChat.append("\n\nBot: Please contact support at ph no 03077775955 or contact MUNEEB ALI MUZAFFAR OR support@gpayapp.com");
                showMenu();
                break;

            default:
                tvChat.append("\n\nBot: ❌ Invalid selection.\nI am here to help you.");
                showMenu();
                break;

        }
    }
}
