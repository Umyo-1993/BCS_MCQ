package com.example.bcsmcq;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;


public class HomeActivity extends AppCompatActivity {

    private RecyclerView Recyclercartlist;
    private DatabaseReference UsersRef;
    private ArrayList<GetSet>arrayList;
    DatabaseReference scoreRef;
    TextView total_price;
    int correct=0;
    Button confirembtn;
    RadioGroup R1;
    RadioButton RB1,RB2,RB3,RB4;
    TextView questions;

    private FirebaseAuth mauth = FirebaseAuth.getInstance();
    public FirebaseRecyclerAdapter<GetSet, ShowViewHolder_up> adapter;


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        R1=findViewById(R.id.radioGroup);
        RB1=findViewById(R.id.radioButton);
        RB2=findViewById(R.id.radioButton2);
        RB3=findViewById(R.id.radioButton3);
        RB4=findViewById(R.id.radioButton4);
        questions=findViewById(R.id.questionname);
        Button btn=findViewById(R.id.btnsubmit);





        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        GetSet getSet=new GetSet();

       // DatabaseReference  userdata=FirebaseDatabase.getInstance().getReference().child(uid).child("Users");
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        int starthour = calendar.get(Calendar.HOUR_OF_DAY);
        int startminute = calendar.get(Calendar.MINUTE);

        UsersRef = FirebaseDatabase.getInstance().getReference().child("Questions");
        scoreRef=FirebaseDatabase.getInstance().getReference().child(uid);
        DatabaseReference UsersRef1 = FirebaseDatabase.getInstance().getReference().child("Questions").child("1");
        Recyclercartlist = findViewById(R.id.cartrecycler);

        Recyclercartlist.setLayoutManager(new LinearLayoutManager(this));
        Recyclercartlist.setHasFixedSize(true);
        UsersRef.keepSynced(true);





        FirebaseRecyclerOptions<GetSet> options = new FirebaseRecyclerOptions.Builder<GetSet>().setQuery(UsersRef, GetSet.class).build();

        adapter = new FirebaseRecyclerAdapter<GetSet, ShowViewHolder_up>(options) {
            @Override
            protected void onBindViewHolder(ShowViewHolder_up showViewHolder, int i, GetSet gs) {
                String questionst= String.valueOf(gs.questionname);
                String oprion1st = String.valueOf(gs.option1);
                String option2st=String.valueOf(gs.option2);
                String option3st=String.valueOf(gs.option3);
                String option4st=String.valueOf(gs.option4);
                String answerst=String.valueOf(gs.answer);


                showViewHolder.questions.setText(questionst);
                showViewHolder.r1.setText(oprion1st);
                showViewHolder.r2.setText(option2st);
                showViewHolder.r3.setText(option3st);
                showViewHolder.r4.setText(option4st);


                showViewHolder.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId)
                    {
                        switch(checkedId)
                        {
                            case R.id.radioButton:
                                // TODO Something
                                showViewHolder.rg.getChildAt(1).setEnabled(false);
                                showViewHolder.rg.getChildAt(2).setEnabled(false);
                                showViewHolder.rg.getChildAt(3).setEnabled(false);
                              
                                if(answerst.equals(oprion1st))
                                {
                                    correct++;
                              //      Toast.makeText(HomeActivity.this, "work done....."+correct, Toast.LENGTH_SHORT).show();

                                }

                                break;
                            case R.id.radioButton2:
                                // TODO Something
                                showViewHolder.rg.getChildAt(0).setEnabled(false);
                                showViewHolder.rg.getChildAt(2).setEnabled(false);
                                showViewHolder.rg.getChildAt(3).setEnabled(false);
                                if(answerst.equals(option2st))
                                {
                                    correct++;

                                }
                                break;
                            case R.id.radioButton3:
                                // TODO Something
                                showViewHolder.rg.getChildAt(0).setEnabled(false);
                                showViewHolder.rg.getChildAt(1).setEnabled(false);
                                showViewHolder.rg.getChildAt(3).setEnabled(false);
                                if(answerst.equals(option3st))
                                {
                                    correct++;

                                }
                                break;
                            case R.id.radioButton4:
                                // TODO Something
                                showViewHolder.rg.getChildAt(0).setEnabled(false);
                                showViewHolder.rg.getChildAt(1).setEnabled(false);
                                showViewHolder.rg.getChildAt(2).setEnabled(false);
                                if(answerst.equals(option4st))
                                {
                                    correct++;

                                }
                                break;


                        }

                    }

                });


          //      Toast.makeText(HomeActivity.this, ""+adapter.getItem(0).getOption1(), Toast.LENGTH_SHORT).show();



           //     total_price.setText(totalstring);


    /*       showViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CharSequence options[]=new CharSequence[]
                                {
                                        "Edit",
                                        "Remove"
                                };
                        AlertDialog.Builder builder=new AlertDialog.Builder(CartActivity.this);
                        builder.setTitle("Cart Options: ");
                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(which==0)
                                {
                                    Intent intent=new Intent(CartActivity.this,ShowActivity.class);
                                    startActivity(intent);
                                }
                                if(which==1)
                                {
                                    adapter.getRef(i).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(CartActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    Intent intent=new Intent(CartActivity.this,ShowActivity.class);
                                    startActivity(intent);
                                }

                            }
                        });
                        builder.show();
                    }
                });*/

            }

            @NonNull
            @Override
            public ShowViewHolder_up onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(HomeActivity.this).inflate(R.layout.view_cart, parent, false);
                // ShowViewHolder showViewHolder = new ShowViewHolder(view);
                ShowViewHolder_up showViewHolder=new ShowViewHolder_up(view);
                return showViewHolder;
            }
        };

        Recyclercartlist.setAdapter(adapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, ""+correct, Toast.LENGTH_SHORT).show();
                Calendar calendar = Calendar.getInstance(Locale.getDefault());
                int finishhour = calendar.get(Calendar.HOUR_OF_DAY);
                int finishminute = calendar.get(Calendar.MINUTE);
                int hr=finishhour-starthour;
                int min=finishminute-startminute;
                Intent intent=new Intent(HomeActivity.this,ResultActivity.class);
                intent.putExtra("Result",String.valueOf(correct));
                intent.putExtra("Hour",String.valueOf(hr));
                intent.putExtra("Minute",String.valueOf(min));
                startActivity(intent);
            }
        });



    }

}

class ShowViewHolder_up extends RecyclerView.ViewHolder
{
   // TextView productnamecart,Quantitycart,pricecart;
    RadioButton r1,r2,r3,r4;
    RadioGroup rg;
    TextView questions;

    public ShowViewHolder_up(@NonNull View itemView) {
        super(itemView);
       //    productnamecart=itemView.findViewById(R.id.productnamecart);
      //  Quantitycart=itemView.findViewById(R.id.quantitycart);
       // pricecart=itemView.findViewById(R.id.price_cart);
        questions=itemView.findViewById(R.id.questionname);
        r1=itemView.findViewById(R.id.radioButton);
        r2=itemView.findViewById(R.id.radioButton2);
        r3=itemView.findViewById(R.id.radioButton3);
        r4=itemView.findViewById(R.id.radioButton4);
        rg=itemView.findViewById(R.id.radioGroup);


    }
}