package com.example.bcsmcq;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
    TextView result_view;
    TextView time_view;
    Button gohome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result_view=findViewById(R.id.result_view);
        time_view=findViewById(R.id.time_view);
        gohome=findViewById(R.id.gohome);
        Intent intent=getIntent();
        String a= intent.getStringExtra("Result");
        String hr=intent.getStringExtra("Hour");
        String min =intent.getStringExtra("Minute");

       result_view.setText("Your Score :"+a);
       time_view.setText("HR"+ hr +" "+ "MN"+" "+ min);
       gohome.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(ResultActivity.this,StartPageActivity1.class);
               startActivity(intent);

           }
       });
    }
    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(ResultActivity.this, StartPageActivity1.class);
        startActivity(intent);
    }

}
