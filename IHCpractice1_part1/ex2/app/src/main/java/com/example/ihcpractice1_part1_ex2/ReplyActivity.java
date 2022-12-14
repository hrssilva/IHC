package com.example.ihcpractice1_part1_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ReplyActivity extends AppCompatActivity {

    private TextView target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
        target = findViewById(R.id.target_text);

        String message = getIntent().getStringExtra("message");
        target.setText(message);
    }
}