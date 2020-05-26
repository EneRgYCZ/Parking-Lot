package com.example.parkinglot;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricManager;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Log_In extends AppCompatActivity
{

    Button log_in,sign_up,fringerprint;

    EditText username,password;

    Parole_Si_Conturi db;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log__in);
        db =  new Parole_Si_Conturi();
        final Executor executor1 = Executors.newSingleThreadExecutor();
        fringerprint = findViewById(R.id.button5);
        username = findViewById(R.id.editText1);
        password = findViewById(R.id.editText4);
        sign_up = findViewById(R.id.button2);
        log_in = findViewById(R.id.button);
        sign_up.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Log_In.this,Sign_Up.class));
            }
        });
        log_in.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(db.checkInfo(password.getText().toString(),username.getText().toString())==true)
                {
                    Intent intent = new Intent(Log_In.this, Harta.class);
                    startActivity(intent);
                    Toast.makeText(Log_In.this, "Log in successfully",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Log_In.this,"Login Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
        {
            final BiometricPrompt biometricPrompt = new BiometricPrompt.Builder(this)
                    .setTitle("Biometric Authentication")
                    .setNegativeButton("Cancel", executor1, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {

                        }
                    }).build();

            fringerprint.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    biometricPrompt.authenticate(new CancellationSignal(), executor1, new BiometricPrompt.AuthenticationCallback()
                    {
                        @Override
                        public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result)
                        {
                            Intent intent = new Intent(Log_In.this, Harta.class);
                            startActivity(intent);
                        }
                    });
                }
            });
        }
    }
}
