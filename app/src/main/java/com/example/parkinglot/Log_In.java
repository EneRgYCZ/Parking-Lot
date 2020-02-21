package com.example.parkinglot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Log_In extends AppCompatActivity
{

    Button log_in,sign_up;

    EditText username,password;

    TextView txt;

    Parole_Si_Conturi db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log__in);
        db =  new Parole_Si_Conturi();
        username = findViewById(R.id.editText1);
        txt = findViewById(R.id.textView4);
        password = findViewById(R.id.editText4);
        sign_up = findViewById(R.id.button2);
        log_in = findViewById(R.id.button);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Log_In.this,Sign_Up.class));
            }
        });
        log_in.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
         //      String user = username.getText().toString().trim();
          //      String pwd = password.getText().toString().trim();
         //       Boolean res = db.checkUser(user,pwd);
         //       if(res==true)
        //        {
                txt.setText(""+db.parole[0]);
                if(db.checkInfo(password.getText().toString(),username.getText().toString())==true)
                {
                    Intent intent = new Intent(Log_In.this, Harta.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Log_In.this,"Login Error",Toast.LENGTH_SHORT);
                }

         //       }
         //       else
         //       {
         //           Toast.makeText(Log_In.this,"Login Error",Toast.LENGTH_SHORT);
         //       }
            }
        });
    }
}
