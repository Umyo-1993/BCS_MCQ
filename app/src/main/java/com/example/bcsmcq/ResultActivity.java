package com.example.bcsmcq;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
    TextView result_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result_view=findViewById(R.id.result_view);
        Intent intent=getIntent();
       String a= intent.getStringExtra("Result");
       result_view.setText("Your Score :"+a);
    }
}