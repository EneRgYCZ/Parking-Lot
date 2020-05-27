package com.example.parkinglot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Sign_Up extends AppCompatActivity
{

    Button btn;

    EditText username, password;

    Parole_Si_Conturi db;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);
        db = new Parole_Si_Conturi();
        mAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.editText2);
        password = findViewById(R.id.editText3);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               String email = username.getText().toString();
               String pass = password.getText().toString();
                mAuth.createUserWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(Sign_Up.this,"Registration successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Sign_Up.this, Log_In.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(Sign_Up.this,"Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
