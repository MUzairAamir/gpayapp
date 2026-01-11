package com.example.gpayapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText etMessage;
    Button btnSend;

    List<ChatMessage> chatList;
    ChatAdapter adapter;

    FirebaseAuth auth;
    DatabaseReference chatRef;

    String receiverId;
    String chatId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        receiverId = getIntent().getStringExtra("receiverId");
        if (receiverId == null) {
            receiverId = "SUPPORT";
        }

        auth = FirebaseAuth.getInstance();
        String senderId = FirebaseAuth.getInstance().getUid();

// One fixed support chat per user
        chatId = senderId + "_SUPPORT";

        chatRef = FirebaseDatabase.getInstance()
                .getReference("chats")
                .child(chatId);

        recyclerView = findViewById(R.id.chatRecycler);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);

        chatList = new ArrayList<>();
        adapter = new ChatAdapter(chatList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        loadMessages();

        btnSend.setOnClickListener(v -> sendMessage());
    }

    private void sendMessage() {
        String msg = etMessage.getText().toString().trim();
        if (msg.isEmpty()) return;

        ChatMessage message = new ChatMessage(
                auth.getUid(),
                receiverId,
                msg,
                System.currentTimeMillis()
        );

        chatRef.push().setValue(message);
        etMessage.setText("");
    }

    private void loadMessages() {
        chatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chatList.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    ChatMessage msg = snap.getValue(ChatMessage.class);
                    chatList.add(msg);
                }
                adapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(chatList.size() - 1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}
