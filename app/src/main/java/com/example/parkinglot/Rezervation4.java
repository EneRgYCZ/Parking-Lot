package com.example.parkinglot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Rezervation4 extends AppCompatActivity
{

    Button btn;
    TextView titlu;
    TextView detalii;
    EditText NrInmatriculare;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezervation);
        Intent intent = getIntent();
        String parcare1 = intent.getExtras().getString("numeParcare5");
        String detalii1 = intent.getExtras().getString("detalii5");
        NrInmatriculare = (EditText)findViewById(R.id.editText);
        btn = (Button)findViewById(R.id.button3);
        titlu = (TextView)findViewById(R.id.textView2);
        detalii = (TextView)findViewById(R.id.textView);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String NR_Inmatriculare = NrInmatriculare.getText().toString();
                Intent intent = new Intent(Rezervation4.this, Harta.class);
                Toast.makeText(Rezervation4.this, "Rezervarea a fost facuta!\n"  + "Numar de inmatriculare:" + NR_Inmatriculare, Toast.LENGTH_LONG).show();
                Toast.makeText(Rezervation4.this, "Aveti timp la dispozitie 1h", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        titlu.setText(parcare1);
        detalii.setText(detalii1);
    }
}
