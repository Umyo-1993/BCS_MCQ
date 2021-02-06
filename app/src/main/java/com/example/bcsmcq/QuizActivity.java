package com.example.bcsmcq;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuizActivity extends AppCompatActivity {
    TextView question,counttime;
    Button option1,option2,option3,option4;
    int total=0;
    int result=0;
    int correct=0;
    int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        question=findViewById(R.id.question);
        option1=findViewById(R.id.option1);
        option2=findViewById(R.id.option2);
        option3=findViewById(R.id.option3);
        option4=findViewById(R.id.option4);
        counttime=findViewById(R.id.count_time);
         timer(60,counttime);
        UpdateQuestion();
    //    Resultshow();


   /*     DatabaseReference mRef= FirebaseDatabase.getInstance().getReference().child("Questions").child(String.valueOf(total));
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                GetSet getSet=snapshot.getValue(GetSet.class);
                question.setText(getSet.getQuestionname());
                option1.setText(getSet.getOption1());
                if(question.getText().toString().equals(option1.getText().toString()))
                {
                    Toast.makeText(QuizActivity.this, "yes", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(QuizActivity.this, "False....", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


    }

    private void Resultshow() {
        Intent intent=new Intent(QuizActivity.this,ResultActivity.class);
        intent.putExtra("Result",String.valueOf(total));
        startActivity(intent);
    }

    private void UpdateQuestion() {

        total++;

        if(total>3)
        {
            Intent intent=new Intent(QuizActivity.this,ResultActivity.class);
            intent.putExtra("Result",String.valueOf(result));
            startActivity(intent);

        }
        else {
            DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("Questions").child(String.valueOf(total));
            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    GetSet getSet = snapshot.getValue(GetSet.class);
                    question.setText(getSet.getQuestionname());

                    option1.setText(getSet.getOption1());
                    option2.setText(getSet.getOption2());
                    option3.setText(getSet.getOption3());
                    option4.setText(getSet.getOption4());

                    option1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (option1.getText().toString().equals(getSet.getAnswer())) {
                                result++;

                                //        option1.setBackgroundColor(Color.GREEN);

                                UpdateQuestion();


                            } else {
                                //       option1.setBackgroundColor(Color.GREEN);
                                UpdateQuestion();

                            }
                        }
                    });

                    option2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (option2.getText().toString().equals(getSet.getAnswer())) {
                                result++;
                                //    option2.setBackgroundColor(Color.GREEN);
                                UpdateQuestion();

                            } else {
                                //   option2.setBackgroundColor(Color.GREEN);
                                UpdateQuestion();

                            }
                        }
                    });

                    option3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (option3.getText().toString().equals(getSet.getAnswer())) {
                                result++;
                                //  option3.setBackgroundColor(Color.GREEN);
                                UpdateQuestion();

                            } else {
                                //  option3.setBackgroundColor(Color.GREEN);
                                UpdateQuestion();

                            }
                        }
                    });

                    option4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (option4.getText().toString().equals(getSet.getAnswer())) {
                                result++;
                                //   option4.setBackgroundColor(Color.GREEN);
                                UpdateQuestion();

                            } else {
                                //  option4.setBackgroundColor(Color.GREEN);
                                UpdateQuestion();

                            }
                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

    }
    public  void timer(int second,final TextView tv)
    {
        new CountDownTimer(second*1000+1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int sec= (int) (millisUntilFinished/1000);
                int min=sec/60;
                sec=sec%60;
                counttime.setText(String.format("%02d",min)+ ":" +String.format("%02d",sec));

            }
            @Override
            public void onFinish() {
                counttime.setText("Finished");

            }
        }.start();
    }
}