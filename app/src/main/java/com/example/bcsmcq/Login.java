package com.example.bcsmcq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText emailed,passworded;
    TextView returnsignup;
    Button login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        // initialising all views through id defined above
        emailed = findViewById(R.id.selected);
        passworded = findViewById(R.id.selectedpw);
        login = findViewById(R.id.signin);
        returnsignup=findViewById(R.id.textbottom2);
        String email, password;
        email = emailed.getText().toString();
        password = passworded.getText().toString();
        //return to Registeractivity
        returnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
        // validations for input email and password
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                loginUserAccount();
            }
        });
    }

    private void loginUserAccount()
    {

        // show the visibility of progress bar to show loading


        // Take the value of two edit texts in Strings
        String email, password;
        email = emailed.getText().toString();
        password = passworded.getText().toString();

        // validations for input email and password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter email!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter password!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // signin existing user
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                    "Login successful!!",
                                    Toast.LENGTH_LONG)
                                    .show();

                            // hide the progress bar
                            Intent intent=new Intent(Login.this,QuizActivity.class);
                            startActivity(intent);


                            // if sign-in is successful
                            // intent to home activity

                        }

                        else {

                            // sign-in failed
                            Toast.makeText(getApplicationContext(),
                                    "Login failed!!",
                                    Toast.LENGTH_LONG)
                                    .show();

                            // hide the progress bar

                        }
                    }



                });



    }
}