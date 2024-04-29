package com.example.landslide_prediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Prediction extends AppCompatActivity {
    EditText q1, q2, q3, q4, q5, q6, q7, q8, q9;
    Button btn;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction);
        btn = findViewById(R.id.button9);
        q1 = findViewById(R.id.editTextText2);
        q2 = findViewById(R.id.editTextText3);
        q3 = findViewById(R.id.editTextText4);
        q4 = findViewById(R.id.editTextText5);
        q5 = findViewById(R.id.editTextText6);
        q6 = findViewById(R.id.editTextText7);
        q7 = findViewById(R.id.editTextText8);
        q8 = findViewById(R.id.editTextText9);
        q9 = findViewById(R.id.editTextText10);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a1 = q1.getText().toString();
                String a2 = q2.getText().toString();
                String a3 = q3.getText().toString();
                String a4 = q4.getText().toString();
                String a5 = q5.getText().toString();
                String a6 = q6.getText().toString();
                String a7 = q7.getText().toString();
                String a8 = q8.getText().toString();
                String a9 = q9.getText().toString();
                if (a1.isEmpty()){
                    q1.setError("*");
                }
                if (a2.isEmpty()){
                    q2.setError("*");
                }
                if (a3.isEmpty()){
                    q3.setError("*");
                }
                if (a4.isEmpty()){
                    q4.setError("*");
                }
                if (a5.isEmpty()){
                    q5.setError("*");
                }
                if (a6.isEmpty()){
                    q6.setError("*");
                }
                if (a7.isEmpty()){
                    q7.setError("*");
                }
                if (a8.isEmpty()){
                    q8.setError("*");
                }
                if (a9.isEmpty()){
                    q9.setError("*");
                }
                else {
                    SharedPreferences.Editor e = sh.edit();
                    e.putString("a1", a1);
                    e.putString("a2", a2);
                    e.putString("a3", a3);
                    e.putString("a4", a4);
                    e.putString("a5", a5);
                    e.putString("a6", a6);
                    e.putString("a7", a7);
                    e.putString("a8", a8);
                    e.putString("a9", a9);
                    e.commit();
                    startActivity(new Intent(getApplicationContext(), Prediction1.class));
                }
            }
        });
    }
}