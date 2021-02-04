package com.example.bcsmcq;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent=getIntent();
       String a= intent.getStringExtra("Result");
        Toast.makeText(this, ""+a, Toast.LENGTH_SHORT).show();
    }
}