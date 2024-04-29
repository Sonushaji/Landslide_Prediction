package com.example.landslide_prediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class userhome extends AppCompatActivity {
    Button b_profile, change, sugg, pred;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhome);
        b_profile=findViewById(R.id.button3);
        change = findViewById(R.id.button5);
        sugg = findViewById(R.id.button6);
        pred = findViewById(R.id.button8);
        b_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ij=new Intent(getApplicationContext(), profile.class);
                startActivity(ij);
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ChangePassword.class));
            }
        });
        sugg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SendSuggestion.class));
            }
        });
        pred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Prediction.class));
            }
        });
    }
}