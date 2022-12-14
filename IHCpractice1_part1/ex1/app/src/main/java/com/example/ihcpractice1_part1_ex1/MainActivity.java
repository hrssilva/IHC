package com.example.ihcpractice1_part1_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText num1, num2;
    private TextView result;
    private Button sum_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sum_button = findViewById(R.id.sum_button);
        result = findViewById(R.id.result_view);
        num1 = findViewById(R.id.first_num_field);
        num2 = findViewById(R.id.second_num_field);

        sum_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        Integer r;

        switch (view.getId()) {
            case R.id.sum_button:
                try {
                    r = Integer.parseInt(num1.getText().toString()) + Integer.parseInt(num2.getText().toString());
                    result.setText(r.toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Please fill two numbers!", Toast.LENGTH_SHORT).show();
                }
        }
    }

}
