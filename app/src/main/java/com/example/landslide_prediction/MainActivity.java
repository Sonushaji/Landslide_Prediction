package com.example.landslide_prediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText e1;
    Button b1;
    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1 = (EditText)findViewById(R.id.ipid);
        b1 = (Button)findViewById(R.id.button);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip = sh.getString("ip","");
        e1.setText(ip);

        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String s1;
        s1 = e1.getText().toString();
        if (s1.length()==0){
            e1.setError("Missing");
        } else{
            SharedPreferences.Editor ed = sh.edit();
            ed.putString("ip",s1);
            ed.putString("url", "http://" + s1 + ":7000");
            ed.commit();

            Intent i = new Intent(getApplicationContext(), login.class);
            startActivity(i);
        }
    }
}