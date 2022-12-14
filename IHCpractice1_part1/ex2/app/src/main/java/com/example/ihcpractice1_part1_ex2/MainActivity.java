package com.example.ihcpractice1_part1_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText message;
    private Button send_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send_button = findViewById(R.id.send_button);
        message = findViewById(R.id.message_field);

        send_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        Intent intent = new Intent(this, ReplyActivity.class);

        switch (view.getId()) {
            case R.id.send_button:
                try {
                    intent.putExtra("message", message.getText().toString());
                    startActivity(intent);
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
        }
    }

}
