package com.example.bcsmcq;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartPageActivity2 extends AppCompatActivity {
    Button prachin,moddho,rabindra,nazrul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page2);
        prachin=findViewById(R.id.prachin_Btn);
        moddho=findViewById(R.id.moddho_Btn);
        rabindra=findViewById(R.id.Rabindra_btn);
        nazrul=findViewById(R.id.Nazrul_btn);
        int Bengali1=1;
        int Bengali2=2;
        prachin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StartPageActivity2.this,HomeActivity.class);
                intent.putExtra("Bengali",Bengali1);

                startActivity(intent);
            }
        });
        moddho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StartPageActivity2.this,HomeActivity.class);
                intent.putExtra("Bengali",Bengali2);
                startActivity(intent);
            }
        });
    }
}