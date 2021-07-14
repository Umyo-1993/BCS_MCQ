package com.example.bcsmcq;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Selectingsub extends AppCompatActivity {
    Button Bangla,English,Math,gk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectingsub);
        Bangla=findViewById(R.id.Bangla_Btn);
        English=findViewById(R.id.English_Btn);
        Math=findViewById(R.id.Math_btn);
        gk=findViewById(R.id.Gk_btn);

        Bangla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Selectingsub.this,StartPageActivity2.class);
                startActivity(intent);
            }
        });


    }
}