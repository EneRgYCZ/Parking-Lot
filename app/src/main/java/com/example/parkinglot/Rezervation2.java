package com.example.parkinglot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Rezervation2 extends AppCompatActivity
{

    Button btn;
    TextView titlu;
    TextView detalii;
    EditText NrInmatriculare;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezervation);
        Intent intent = getIntent();
        String parcare1 = intent.getExtras().getString("numeParcare3");
        String detalii1 = intent.getExtras().getString("detalii3");
        NrInmatriculare = (EditText)findViewById(R.id.editText);
        btn = (Button)findViewById(R.id.button3);
        titlu = (TextView)findViewById(R.id.textView2);
        detalii = (TextView)findViewById(R.id.textView);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Rezervari");
                String id = reference.push().getKey();
                String NR_Inmatriculare = NrInmatriculare.getText().toString();
                Rezervari rezervari = new Rezervari(NR_Inmatriculare, parcare1);
                reference.child(id).setValue(rezervari);
                Intent intent = new Intent(Rezervation2.this, Harta.class);
                Toast.makeText(Rezervation2.this, "Rezervarea a fost facuta!\n"  + "Numar de inmatriculare:" + NR_Inmatriculare, Toast.LENGTH_LONG).show();
                Toast.makeText(Rezervation2.this, "Aveti timp la dispozitie 1h", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        titlu.setText(parcare1);
        detalii.setText(detalii1);
    }
}
