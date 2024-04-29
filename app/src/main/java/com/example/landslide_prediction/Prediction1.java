package com.example.landslide_prediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Prediction1 extends AppCompatActivity {
    Button button;
    EditText q10, q11, q12, q13, q14, q15, q16, q17, q18, q19;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction1);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        q10 = findViewById(R.id.editTextText11);
        q11 = findViewById(R.id.editTextText12);
        q12 = findViewById(R.id.editTextText13);
        q13 = findViewById(R.id.editTextText14);
        q14 = findViewById(R.id.editTextText15);
        q15 = findViewById(R.id.editTextText16);
        q16 = findViewById(R.id.editTextText17);
        q17 = findViewById(R.id.editTextText18);
        q18 = findViewById(R.id.editTextText19);
        q19 = findViewById(R.id.editTextText20);
        button = findViewById(R.id.button10);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a10 = q10.getText().toString();
                String a11 = q11.getText().toString();
                String a12 = q12.getText().toString();
                String a13 = q13.getText().toString();
                String a14 = q14.getText().toString();
                String a15 = q15.getText().toString();
                String a16 = q16.getText().toString();
                String a17 = q17.getText().toString();
                String a18 = q18.getText().toString();
                String a19 = q19.getText().toString();

                if (a10.isEmpty()){
                    q10.setError("*");
                }
                if (a11.isEmpty()){
                    q11.setError("*");
                }
                if (a12.isEmpty()){
                    q12.setError("*");
                }
                if (a13.isEmpty()){
                    q13.setError("*");
                }
                if (a14.isEmpty()){
                    q14.setError("*");
                }
                if (a15.isEmpty()){
                    q15.setError("*");
                }
                if (a16.isEmpty()){
                    q16.setError("*");
                }
                if (a17.isEmpty()){
                    q17.setError("*");
                }
                if (a18.isEmpty()){
                    q18.setError("*");
                }
                if (a19.isEmpty()){
                    q19.setError("*");
                }
                else {
                    SharedPreferences.Editor e = sh.edit();
                    e.putString("a10", a10);
                    e.putString("a11", a11);
                    e.putString("a12", a12);
                    e.putString("a13", a13);
                    e.putString("a14", a14);
                    e.putString("a15", a15);
                    e.putString("a16", a16);
                    e.putString("a17", a17);
                    e.putString("a18", a18);
                    e.putString("a19", a19);
                    e.commit();
                    startActivity(new Intent(getApplicationContext(), Prediction3.class));
                }
            }
        });
    }
}