package com.example.parkinglot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sign_Up extends AppCompatActivity
{

    Button btn;

    EditText username, password;

    Parole_Si_Conturi db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);
        db = new Parole_Si_Conturi(this);
        username = findViewById(R.id.editText2);
        password = findViewById(R.id.editText3);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                long val = db.addUser(user,pwd);
                if(val>0)
                {
                    startActivity(new Intent(Sign_Up.this,Log_In.class));
                }
                else
                {
                    Toast.makeText(Sign_Up.this,"Registration Error",Toast.LENGTH_SHORT);
                }
            }
        });

    }
}
